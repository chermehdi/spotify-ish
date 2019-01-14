package org.mql.auth.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.mql.commons.views.ServiceInfo;

/**
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
