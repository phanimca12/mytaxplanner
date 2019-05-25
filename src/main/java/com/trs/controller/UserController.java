package com.trs.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.exception.UserCreationException;
import com.trs.model.ResponseModel;
import com.trs.model.User;
import com.trs.model.Users;
import com.trs.service.IUserService;
import com.trs.service.UserService;

@Path( "/user" )
public class UserController
{

  @GET
  @Produces( { MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.TEXT_HTML } )
  public Response getResponse()
  {

    final IUserService userService = new UserService();

    final Users users = new Users();
    users.setUserList( userService.getAllUsers() );

    return Response.ok().entity( users ).build();

  }

  @POST
  @Consumes( { MediaType.APPLICATION_XML, MediaType.TEXT_XML } )
  @Produces( { MediaType.APPLICATION_XML, MediaType.TEXT_XML } )
  public Response createUser( @HeaderParam( MyTaxReturnConstants.ACCEPT_HEADER ) final String acceptHeader,
                              final User user ) throws Exception
  {

    final IUserService service = new UserService();

    final ResponseModel model = service.createNewUser( user );
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
