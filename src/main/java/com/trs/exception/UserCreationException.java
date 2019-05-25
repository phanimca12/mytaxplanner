package com.trs.exception;

import javax.ws.rs.core.Response;

public class UserCreationException extends RuntimeException implements IBaseException
{

  private static final long serialVersionUID = 7718828512143293558L;

  private String            errorCode;
  private String[]          params;
  private Throwable         throwable        = null;

  public UserCreationException( final String message, final Response.Status status, final String... parameters )
  {
    super( message );
    errorCode = status.getReasonPhrase();
    params = parameters;
  }

  public UserCreationException( final String message )
  {
    super( message );
    errorCode = Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase();
  }

  public UserCreationException( final Throwable throwable )
  {
    super( throwable.getMessage() );
    this.throwable = throwable;
  }

  public Throwable getException()
  {
    return throwable;
  }

  public String getErrorCode()
  {
    // TODO Auto-generated method stub
    return errorCode;
  }

  public String getDocUri()
  {
    // TODO Auto-generated method stub
    return null;
  }

  public String[] getParams()
  {
    // TODO Auto-generated method stub
    return params;
  }

}
