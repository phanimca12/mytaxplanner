package com.trs.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.exception.UserCreationException;
import com.trs.model.Agent;
import com.trs.model.ResponseModel;
import com.trs.service.AgentService;
import com.trs.service.IAgentService;

@Path( "/agent" )
public class AgentController
{
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
