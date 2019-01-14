package org.mql.auth.resources.exceptions;

import javax.inject.Qualifier;

/**
 * @author chermehdi
 */
public class AuthenticationException extends RuntimeException {

  public AuthenticationException() {
    super("problem occured during authentication");
  }
}
