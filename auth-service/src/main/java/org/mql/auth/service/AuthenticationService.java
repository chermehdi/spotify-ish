package org.mql.auth.service;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.mql.auth.dto.LoginRequest;
import org.mql.commons.views.ServiceInfo;
import org.mql.commons.views.TokenResponse;
import org.mql.commons.views.UserView;

/**
 * Service checking the validity of the user information provided by communicating with the
 * user-service
 *
 * @author chermehdi
 */
@ApplicationScoped
public class AuthenticationService extends NetworkService {

  @Inject
  TokenHandler tokenHandler;

  public Optional<TokenResponse> login(LoginRequest request) {
    ServiceInfo serviceInfo = getServiceInfo("user-service");
    try {
      UserView userView = client.target(serviceInfo.getUrl() + "/users/" + request.getEmail())
          .request()
          .get(UserView.class);
      // TODO: password encoding, it's a demo so who cares
      if (request.getPassword().equals(userView.getPassword())) {
        return Optional.ofNullable(tokenHandler.generateToken(userView));
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }
}
