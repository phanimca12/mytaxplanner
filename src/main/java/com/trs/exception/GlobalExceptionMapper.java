package com.trs.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.logger.ConsoleLogger;
import com.trs.model.ErrorModel;

public class GlobalExceptionMapper extends BaseExceptionBaseMapper<IBaseException> implements ExceptionMapper<Exception>
{

  private final Logger myLogger = ConsoleLogger.getInstance();

  private Response toResponse( final Exception exception, final Status status, final String message )
  {
    final String uri = uriInfo.getRequestUriBuilder().host( null ).scheme( null ).build().toString();
    return Response.status( status )
                   .entity( new ErrorModel( getLocalizedMessage( message ), uri ) )
                   .type( MediaType.TEXT_PLAIN_TYPE.equals( httpHeader.getMediaType() ) ? MediaType.APPLICATION_XML_TYPE
                                                                                        : httpHeader.getMediaType() )
                   .build();
  }

  private static int getStatus( final Throwable ex )
  {
    if ( ex instanceof WebApplicationException )
      return ( (WebApplicationException)ex ).getResponse().getStatus();
    return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
  }

  public Response toResponse( final Exception exception )
  {
    final int errorCode = getStatus( exception );

    final StringBuilder message = new StringBuilder();
    message.append( "\n " );
    message.append( exception.getMessage() );
    message.append( "\n " );
    message.append( exception.getClass().getName() );
    final StackTraceElement[] trace = exception.getStackTrace();
    for ( final StackTraceElement traceElement : trace )
      message.append( "\n " + traceElement );

    if ( myLogger.isLoggable( Level.SEVERE ) )
      myLogger.log( Level.SEVERE, message.toString() );

    if ( errorCode == 404 )
      return toResponse( exception, Response.Status.NOT_FOUND, MyTaxReturnConstants.NOT_FOUND_MSG );
    if ( errorCode == 500 )
      return toResponse( exception, Response.Status.INTERNAL_SERVER_ERROR, MyTaxReturnConstants.INTERNAL_ERROR_MSG );
    if ( errorCode == 400 )
      return toResponse( exception, Response.Status.BAD_REQUEST, MyTaxReturnConstants.BAD_REQUEST_MSG );
    return toResponse( exception, Response.Status.INTERNAL_SERVER_ERROR, MyTaxReturnConstants.INTERNAL_ERROR_MSG );
  }
}
