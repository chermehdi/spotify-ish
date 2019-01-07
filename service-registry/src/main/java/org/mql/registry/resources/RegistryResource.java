package org.mql.registry.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.mql.registry.ApplicationStore;
import org.mql.registry.exceptions.ApplicationNotFoundException;

/**
 * @author chermehdi
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RegistryResource {

  @Inject
  ApplicationStore applicationStore;

  @GET
  public Response applications() {
    JsonArray jsonArray = applicationStore.getAvailableApplications();
    return Response.ok(jsonArray).build();
  }

  @POST
  @Path("/register")
  public Response register(JsonObject object) {
    applicationStore.add(object);
    return Response.ok().build();
  }

  @GET
  @Path("/resolve/{serviceName}")
  public Response resolve(@PathParam("serviceName") String serviceName) {
    JsonObject serviceDesc = applicationStore.getApplication(serviceName)
        .orElseThrow(() -> new ApplicationNotFoundException(serviceName));
    return Response.ok(serviceDesc).build();
  }
}
