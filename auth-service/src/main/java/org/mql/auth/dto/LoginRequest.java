package org.mql.auth.dto;

import javax.validation.constraints.NotNull;

/**
 * Simple Dto object that ensures the validity of it's fields
 *
 * @author chermehdi
 */
public class LoginRequest {

  @NotNull
  private String email;

  @NotNull
  private String password;

  public LoginRequest() {
  }

  public LoginRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
