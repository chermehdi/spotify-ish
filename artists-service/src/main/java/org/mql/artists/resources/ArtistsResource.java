package org.mql.artists.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.mql.artists.service.ArtistService;

/**
 * @author chermehdi
 */
@Path("/artists")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArtistsResource {

  @Context
  UriInfo uriInfo;

  @Inject
  ArtistService artistService;

  static Map<String, String> artists = new ConcurrentHashMap<>();

  @GET
  public Response getArtists() {
    return Response.ok(artistService.getAllArtists()).build();
  }


  private JsonArray computeArtistsArray() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    arrayBuilder.add(Json.createObjectBuilder().add("name", "mehdi"));
    artists.values()
        .stream()
        .map(value -> Json.createObjectBuilder().add("name", value).build())
        .forEach(arrayBuilder::add);
    return arrayBuilder.build();
  }

  @POST
  public Response createArtist(JsonObject artist) {
    artists.put("name", artist.getString("name"));
    return Response.created(uriInfo.getRequestUri()).build();
  }
}
