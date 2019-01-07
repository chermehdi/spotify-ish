package org.mql.registry.exceptions;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * map {@link ApplicationNotFoundMapper} into a Json response that has a success flag set to false
 *
 * @author chermehdi
 */
public class ApplicationNotFoundMapper implements ExceptionMapper<ApplicationNotFoundException> {

  @Override
  public Response toResponse(ApplicationNotFoundException exception) {
    JsonObject object = Json.createObjectBuilder()
        .add("success", false)
        .add("applicationName", exception.getApplicationName())
        .build();
    return Response.ok(object)
        .entity(object)
        .build();
  }
}
