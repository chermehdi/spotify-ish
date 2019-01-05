package org.mql.registry.resources.resolver;

/**
 * @author chermehdi
 */
public class ServiceDescription {

  private String host;

  private int port;

  private String name;

  public ServiceDescription() {
  }

  public ServiceDescription(String host, int port, String name) {
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

  @Override
  public String toString() {
    return "ServiceDescription{" +
        "host='" + host + '\'' +
        ", port=" + port +
        ", name='" + name + '\'' +
        '}';
  }
}
