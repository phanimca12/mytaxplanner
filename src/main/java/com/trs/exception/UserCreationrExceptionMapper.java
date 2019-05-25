package com.trs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class UserCreationrExceptionMapper extends BaseExceptionBaseMapper<IBaseException>
    implements ExceptionMapper<UserCreationException>
{

  public Response toResponse( final UserCreationException userexception )
  {
    // TODO Auto-generated method stub
    return super.toResponse( userexception );
  }

}
