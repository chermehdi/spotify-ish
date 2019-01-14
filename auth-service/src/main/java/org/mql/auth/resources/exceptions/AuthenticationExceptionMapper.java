package org.mql.auth.resources.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author chermehdi
 */
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

  @Override
  public Response toResponse(AuthenticationException exception) {
    return Response.status(Status.UNAUTHORIZED).build();
  }
}
