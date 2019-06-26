package com.trs.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.dao.AgentService;
import com.trs.dao.IAgentService;
import com.trs.exception.UserCreationException;
import com.trs.model.Agent;
import com.trs.model.Agents;
import com.trs.model.ResponseModel;

@Path( "/agent" )
public class AgentController
{

  @GET
  @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
  @Path( "/agentdetails" )
  public List<Agent> getResponse()
  {

    final IAgentService agentrService = new AgentService();

    final Agents agents = new Agents();
    agents.setAgentList( agentrService.getAllAgents() );

    return agents.getAgentList();

  }

  @POST
  @Consumes( { MediaType.APPLICATION_XML, MediaType.TEXT_XML } )
  @Produces( { MediaType.APPLICATION_XML, MediaType.TEXT_XML } )
  public Response createAgent( @HeaderParam( MyTaxReturnConstants.ACCEPT_HEADER ) final String acceptHeader,
                               final Agent agent ) throws Exception
  {

    final IAgentService service = new AgentService();

    final ResponseModel model = service.createNewAgent( agent );
    return buildResponse( model, acceptHeader );

  }

  private static Response buildResponse( final ResponseModel model, final String contentType )
  {
    if ( model != null )
      return Response.ok( model )
                     .header( "content-disposition", "attachment;" )
                     .header( "Content-Type", contentType )
                     .build();
    throw new UserCreationException( MyTaxReturnConstants.RESPONSE_GENERATION_ERROR_MSG );

  }
}
