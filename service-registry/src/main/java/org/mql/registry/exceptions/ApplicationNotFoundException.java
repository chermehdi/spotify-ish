package org.mql.registry.exceptions;

/**
 * @author chermehdi
 */
public class ApplicationNotFoundException extends RuntimeException {

  private String applicationName;

  public ApplicationNotFoundException(String applicationName) {
    super("Application not found in the registry");
    this.applicationName = applicationName;
  }

  public String getApplicationName() {
    return applicationName;
  }
}
