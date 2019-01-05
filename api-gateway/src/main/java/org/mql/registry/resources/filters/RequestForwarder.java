package org.mql.registry.resources.filters;

import java.io.IOException;
import java.net.URI;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import org.mql.registry.resources.resolver.ServiceDescription;

/**
 * @author chermehdi
 */
@Provider
@PreMatching
public class RequestForwarder implements ContainerRequestFilter {

  private ServiceResolver resolver;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    UriInfo uriInfo = requestContext.getUriInfo();
    resolver = new ServiceResolver(uriInfo);
    ServiceDescription description = resolver.getServiceDescription();
    String newServiceUrl = resolver.getNewServiceUrl();
    URI requestUri = URI.create(description.getUrl() + "/" + newServiceUrl);
    System.out.println("forwarding request to " + requestUri);
    requestContext.setRequestUri(requestUri);
  }
}
