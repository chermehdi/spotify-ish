package org.mql.registry;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * @author chermehdi
 */
@ApplicationScoped
public class ApplicationStore {

  private final Map<String, JsonObject> apps = new ConcurrentHashMap<>();

  private final Map<String, LocalDateTime> lastRegistered = new ConcurrentHashMap<>();

  public JsonArray getAvailableApplications() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    apps.values()
        .forEach(arrayBuilder::add);
    return arrayBuilder.build();
  }

  public void add(JsonObject appDescription) {
    String appName = appDescription.getString("name");
    apps.put(appName, appDescription);
    lastRegistered.put(appName, LocalDateTime.now());
  }

  public Optional<JsonObject> getApplication(String appName) {
    return Optional.ofNullable(apps.get(appName));
  }

  public void removeApplication(String appName) {
    apps.remove(appName);
    lastRegistered.remove(appName);
  }

  public Map<String, JsonObject> getApps() {
    return apps;
  }

  public Map<String, LocalDateTime> getLastRegistered() {
    return lastRegistered;
  }
}
