package org.mql.registry.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author chermehdi
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RegistryResource {

  static Map<String, JsonObject> apps = new ConcurrentHashMap<>();

  @GET
  public Response applications() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    apps.values()
        .stream()
        .forEach(object -> arrayBuilder.add(object));
    return Response.ok(arrayBuilder.build()).build();
  }

  private JsonObject createAppsObject(String name) {
    return apps.get(name);
  }

  @POST
  @Path("/register")
  public Response register(JsonObject object) {
    String appName = object.getString("name");
    System.out.println("Registering application " + appName);
    apps.put(appName, object);
    return Response.ok().build();
  }

  @GET
  @Path("/resolve/{serviceName}")
  public Response resolve(@PathParam("serviceName") String serviceName) {
    JsonObject serviceDesc = apps.get(serviceName);
    return Response.ok(serviceDesc).build();
  }
}
