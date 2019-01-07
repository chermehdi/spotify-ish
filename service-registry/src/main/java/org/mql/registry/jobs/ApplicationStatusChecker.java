package org.mql.registry.jobs;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.json.JsonObject;
import org.mql.registry.ApplicationStore;

/**
 * @author chermehdi
 */
public class ApplicationStatusChecker implements Runnable {

  private final ApplicationStore store;

  public static final long MINUTES = 2;

  public ApplicationStatusChecker(ApplicationStore store) {
    this.store = store;
  }

  @Override
  public void run() {
    Map<String, JsonObject> applications = store.getApps();
    LocalDateTime now = LocalDateTime.now();
    List<String> shouldRemove = new ArrayList<>();
    for (String applicationName : applications.keySet()) {
      LocalDateTime lastTime = store.getLastRegistered().get(applicationName);
      long minutesInBetween = ChronoUnit.MINUTES.between(lastTime, now);
      System.out.println(applicationName + " duration since last " + minutesInBetween);
      if (minutesInBetween >= MINUTES) {
        shouldRemove.add(applicationName);
      }
    }
    shouldRemove.forEach(store::removeApplication);
  }
}
