package org.mql.commons.views;

/**
 * @author chermehdi
 */
public class ServiceInfo {

  private String host;

  private int port;

  private String name;

  public ServiceInfo() {
  }

  public ServiceInfo(String host, int port, String name) {
    this.host = host;
    this.port = port;
    this.name = name;
  }

  public String getUrl() {
    return String.format("http://%s:%d", host, port);
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
