package org.mql.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
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

  byte[] secretBytes = "32498712349871ojadhflasdfhqoeryuwqkjehroiqerhwjkdfhlsakdfhiqehridhflkasjdhflakdhflaskdjfhlaskdjhflkasdjfhlaskdjhflkasdjhflkasdhflkasjhdfoieuyroiweryiweuyriowehrshflkasjhdfasdfh"
      .getBytes();

  Key signingKey = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());

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
