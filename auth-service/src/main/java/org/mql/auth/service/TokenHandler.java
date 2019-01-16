package org.mql.auth.service;

import io.helidon.config.Config;
import io.helidon.config.ConfigSources;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.DatatypeConverter;
import org.mql.commons.views.TokenResponse;
import org.mql.commons.views.UserView;

/**
 * JWT token generation service, generates the tokens based on the JWT_SECRET env variables and the
 * provided user data, this is a CDI managed bean so a manual creation will not work, unless an
 * explicit invocation of the {@link TokenHandler#init()} method is assured
 *
 * @author chermehdi
 */
@ApplicationScoped
public class TokenHandler {

  byte[] jwtSecret = null;

  Key signingKey;

  // 4 hours
  static final long DEFAULT_DURATION = 4L * 60L * 60L * 1000L;

  long duration;

  @PostConstruct
  public void init() {
    Config configuration = Config.withSources(
        ConfigSources.environmentVariables()
    ).build();

    jwtSecret = configuration.get("JWT_SECRET").asOptionalString()
        .orElseThrow(
            () -> new IllegalStateException("Secret Key should be available in env-variables")
        ).getBytes();

    duration = configuration.get("JWT_DURATION").asOptionalLong().orElse(DEFAULT_DURATION);

    signingKey = new SecretKeySpec(jwtSecret, SignatureAlgorithm.HS256.getJcaName());
  }

  public TokenResponse generateToken(UserView userView) {
    JwtBuilder builder = Jwts.builder()
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setSubject(userView.getEmail())
        .setIssuer("AUTH")
        .signWith(signingKey)
        .setExpiration(new Date(System.currentTimeMillis() + 4 * 60 * 60 * 1000L));
    return new TokenResponse(builder.compact());
  }

  public Claims decodeJwt(String token) {
    return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("mehdi"))
        .parseClaimsJws(token)
        .getBody();
  }

  public String getSubject(String token) {
    return decodeJwt(token).getSubject();
  }
}
