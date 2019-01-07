package org.mql.registry.resources.filters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;
import org.mql.registry.resources.resolver.RegistryService;
import org.mql.registry.resources.resolver.ServiceDescription;

/**
 * Resolves a {@link ServiceDescription} based on the given {@link UriInfo}
 *
 * @author HoudaOul
 * @author chermehdi
 */
public class ServiceResolver {

  final UriInfo uriInfo;

  final RegistryService registryService;

  public ServiceResolver(UriInfo uriInfo) {
    this.uriInfo = uriInfo;
    registryService = new RegistryService();
  }

  public ServiceDescription getServiceDescription() {
    List<PathSegment> pathSegments = uriInfo.getPathSegments();
    String serviceName = pathSegments.get(0).getPath();
    Optional<ServiceDescription> serviceDescription = registryService
        .getServiceDescription(serviceName);
    // TODO: should add some kind of error handling
    return serviceDescription.get();
  }

  public String getNewServiceUrl() {
    return uriInfo.getPathSegments()
        .stream()
        .skip(1)
        .map(PathSegment::getPath)
        .collect(Collectors.joining("/"));
  }
}
