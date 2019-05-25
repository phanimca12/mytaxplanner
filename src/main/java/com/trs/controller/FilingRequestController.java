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
import com.trs.model.ResponseModel;
import com.trs.model.ReturnFilingRequest;
import com.trs.service.IReturnFilingRequest;
import com.trs.service.ReturnFilingService;

@Path( "/returnfilingrequest" )
public class FilingRequestController
{
  @POST
  @Consumes( { MediaType.APPLICATION_XML, MediaType.TEXT_XML } )
  @Produces( { MediaType.APPLICATION_XML, MediaType.TEXT_XML } )
  public Response createNewRequest( @HeaderParam( MyTaxReturnConstants.ACCEPT_HEADER ) final String acceptHeader,
                                    final ReturnFilingRequest returnFilingRequest ) throws Exception
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
