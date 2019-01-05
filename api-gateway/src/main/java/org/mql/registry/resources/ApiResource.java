package org.mql.registry.resources;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author chermehdi
 */
@Path("/*")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ApiResource {

  @GET
  public Response get(@Context UriInfo uri) {
    return Response.ok(uri.getAbsolutePath().getPath()).build();
  }
}
