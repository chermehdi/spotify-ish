package org.mql.auth.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.mql.commons.views.ServiceInfo;

/**
 * Base service that handles creation of the http {@link Client}, and do the service identification
 * via a request to the registry service {@link NetworkService#getServiceInfo(String)}, since it's a
 * demo no error handling is done
 *
 * @author chermehdi
 */
public abstract class NetworkService {

  protected String REGISTRY_URL = "http://localhost:8081/resolve";

  protected Client client = ClientBuilder.newClient();

  // TODO: add error handling
  protected ServiceInfo getServiceInfo(String serviceName) {
    return client.target(REGISTRY_URL + "/" + serviceName)
        .request()
        .get(ServiceInfo.class);
  }
}
