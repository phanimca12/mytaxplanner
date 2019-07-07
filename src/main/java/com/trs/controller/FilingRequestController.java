package com.trs.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.dao.AgentService;
import com.trs.dao.IReturnFilingRequest;
import com.trs.dao.ReturnFilingService;
import com.trs.dao.UserService;
import com.trs.exception.UserCreationException;
import com.trs.model.ResponseModel;
import com.trs.model.ReturnFiling;
import com.trs.model.ReturnFilingList;

@Path( "/returnfilingrequest" )
public class FilingRequestController
{
  @DELETE
  @Produces( { MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON } )
  @Path( "/request/{param}" )
  public String deleteRequest( @PathParam( "param" ) final long ReqID )
  {

    final IReturnFilingRequest requestService = new ReturnFilingService();

    if ( requestService.deleteRequest( ReqID ) )
    {

      return "Record Deleted Successfully !";
    }
    else
    {

      return "Error occured";
    }
  }

  @GET
  @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
  @Path( "/requestdetails/{param}" )
  public List<ReturnFiling> getResponse( @PathParam( "param" ) final String uid )
  {

    final IReturnFilingRequest requestService = new ReturnFilingService();

    final ReturnFilingList filingList = new ReturnFilingList();
    final UserService service = new UserService();
    final int UserID = service.getUserID( uid );

    filingList.setReturnFilingList( requestService.getAllRequest( UserID ) );
    return filingList.getReturnFilingList();

  }

  @GET
  @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
  @Path( "/requestdetails/{param}/{status}" )
  public List<ReturnFiling> getResponse( @PathParam( "param" ) final String uid,
                                         @PathParam( "status" ) final String status )
  {

    final IReturnFilingRequest requestService = new ReturnFilingService();

    final ReturnFilingList filingList = new ReturnFilingList();
    final AgentService service = new AgentService();
    final String agentID = service.getAgentID( uid );

    filingList.setReturnFilingList( requestService.getAgentProcessingRequest( agentID, status ) );
    return filingList.getReturnFilingList();

  }

  @POST
  @Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
  @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
  public Response createNewRequest( @HeaderParam( MyTaxReturnConstants.ACCEPT_HEADER ) final String acceptHeader,
                                    final ReturnFiling returnFilingRequest ) throws Exception
  {

    final IReturnFilingRequest service = new ReturnFilingService();

    final ResponseModel model = service.createNewReturnRequest( returnFilingRequest );
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
