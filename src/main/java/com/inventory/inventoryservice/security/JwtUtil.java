package com.inventory.inventoryservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.inventory.inventoryservice.security.model.UserEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtUtil{

  private final Environment environment;
  private final UserValidatorService userValidatorService;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(environment.getProperty("SECRET_KEY"))
      .parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(UserDetails userDetails) {
    String username = userDetails.getUsername();

    UserEntity user = userValidatorService.ifFoundByUsernameReturnElseThrow(username);

    Map<String,Object> claims = new HashMap<>();
    claims.put("fullName", user.getFullName());
    claims.put("roles", convertRole(user));
    claims.put("authority", user.getAuthority());
    return createToken(claims, username);
  }

  private List<String> convertRole(UserEntity user) {
    return user.getRoles().stream()
      .map(role-> role.getRoleName().substring(5).toLowerCase())
      .collect(Collectors.toList());
  }

  private String createToken(Map<String,Object> claims, String subject) {
    return Jwts.builder().setClaims(claims).setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
      .signWith(SignatureAlgorithm.HS256, environment.getProperty("SECRET_KEY")).compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()))
      && !isTokenExpired(token);
  }

}
