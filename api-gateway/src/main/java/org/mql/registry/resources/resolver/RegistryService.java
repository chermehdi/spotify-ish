package org.mql.registry.resources.resolver;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author chermehdi
 */
@RequestScoped
public class RegistryService {

  public static String REGISTRY_URL = "http://localhost:8081/";

  private Client client = ClientBuilder.newClient();

  public Optional<ServiceDescription> getServiceDescription(String serviceName) {
    try {
      ServiceDescription serviceDescription = client.target(REGISTRY_URL + "resolve/" + serviceName)
          .request().get(ServiceDescription.class);
      return Optional.of(serviceDescription);
    } catch (Exception e) {
      System.out.println("an error occured when trying to resolve the service name " + e.getMessage());
      return Optional.empty();
    }
  }
}
