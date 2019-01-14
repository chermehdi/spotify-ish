package org.mql.auth.resources;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.mql.auth.dto.LoginRequest;
import org.mql.auth.resources.exceptions.AuthenticationException;
import org.mql.auth.service.AuthenticationService;
import org.mql.commons.views.TokenResponse;

/**
 * @author chermehdi
 */
@Path("/auth")
@ApplicationScoped
public class AuthenticationResource {

  @Inject
  AuthenticationService service;

  @POST
  @Path("/login")
  public Response login(@Valid LoginRequest request) {
    Optional<TokenResponse> optionalToken = service.login(request);
    return optionalToken.map(token -> Response.ok(token).build())
        .orElseThrow(() -> new AuthenticationException());
  }
}
