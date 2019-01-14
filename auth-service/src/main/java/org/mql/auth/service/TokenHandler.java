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
 * @author chermehdi
 */
@ApplicationScoped
public class TokenHandler {

  byte[] jwtSecret = null;

  Key signingKey;

  @PostConstruct
  public void init() {
    Config configuration = Config.withSources(
        ConfigSources.environmentVariables()
    ).build();

    jwtSecret = configuration.get("JWT_SECRET").asOptionalString()
        .orElseThrow(
            () -> new IllegalStateException("Secret Key should be available in env-variables")
        ).getBytes();

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
