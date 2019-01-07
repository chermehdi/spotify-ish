package org.mql.registry.resources.filters;

import java.io.IOException;
import java.net.URI;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import org.mql.registry.resources.resolver.ServiceDescription;

/**
 * This filter will never match any resource (because the service provides none), and will forward
 * each request to a computed uri using the standard rest {@link Client}
 *
 * @author HoudaOul
 * @author chermehdi
 */
@Provider
public class RequestForwarder implements ContainerResponseFilter {

  private ServiceResolver resolver;

  @Override
  public void filter(ContainerRequestContext requestContext,
      ContainerResponseContext responseContext) throws IOException {
    UriInfo uriInfo = requestContext.getUriInfo();
    resolver = new ServiceResolver(uriInfo);
    ServiceDescription description = resolver.getServiceDescription();
    String newServiceUrl = resolver.getNewServiceUrl();
    URI requestUri = URI.create(description.getUrl() + "/" + newServiceUrl);
    System.out.println("forwarding request to " + requestUri);
    Client client = ClientBuilder.newClient();
    Builder requestBuilder = client.target(requestUri).request();
    if (requestContext.hasEntity()) {
      JsonObject sendObject = Json.createReader(requestContext.getEntityStream()).readObject();
      Response response = requestBuilder.method(requestContext.getMethod(),
          Entity.entity(sendObject, requestContext.getMediaType()));
      computeResponse(responseContext, response);
    } else {
      Response response = requestBuilder.method(requestContext.getMethod());
      computeResponse(responseContext, response);
    }
  }

  private void computeResponse(ContainerResponseContext responseContext, Response response) {
    responseContext.setEntity(response.getEntity());
    responseContext.setStatus(response.getStatus());
    MultivaluedMap<String, Object> headers = response.getHeaders();
    headers.forEach(
        (headerName, headerValue) -> responseContext.getHeaders().add(headerName, headerValue));
  }
}
