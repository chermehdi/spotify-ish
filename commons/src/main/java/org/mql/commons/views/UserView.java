package org.mql.commons.views;

/**
 * DTO for the user object (kept lightweight)
 *
 * @author chermehdi
 */
public class UserView {

  private String email;

  private String password;

  public UserView() {
  }

  public UserView(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
