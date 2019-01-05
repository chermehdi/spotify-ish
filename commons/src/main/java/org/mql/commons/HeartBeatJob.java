package org.mql.commons;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author chermehdi
 */
public class HeartBeatJob implements Job {

  private Client client;

  public static final String REGISTRY_URL = "http://localhost:8081";

  public HeartBeatJob() {
    client = ClientBuilder.newClient();
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    JsonObject payload = createRegistryPayload();
    try {
      client.target(REGISTRY_URL + "/register").request()
          .post(Entity.entity(payload, MediaType.APPLICATION_JSON));
      System.out.println("Sending info to the service registry ...");
    } catch (Exception e) {
      System.out.println("Could not contact the service registry " + e.getMessage());
    }
  }

  private JsonObject createRegistryPayload() {
    return Json.createObjectBuilder()
        .add("host", "127.0.0.1")
        .add("port", 8080)
        .add("name", "gateway")
        .build();
  }
}
