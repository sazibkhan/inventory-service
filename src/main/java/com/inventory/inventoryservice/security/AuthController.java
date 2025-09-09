package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.AuthenticationRequest;
import com.inventory.inventoryservice.security.model.AuthenticationResponse;
import com.inventory.inventoryservice.security.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthController{

  private final AuthenticationManager authenticationManager;
  private final AppUserDetailService appUserDetailService;
  private final UserValidatorService userValidatorService;
  private final JwtUtil jwtUtil;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest req) {
    try {
      // 1️⃣ Authenticate username & password
          authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
          );
    }  catch (DisabledException e) {
      // যদি user disabled থাকে
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User account is disabled");
    } catch (BadCredentialsException e) {
      // If password ভুল হয়
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    } catch (Exception e) {
      // অন্য কোনো unexpected exception
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }

    try {
      // 2️⃣ [Load user details] &&  3️⃣ [Generate JWT token] &&  4️⃣ [Return JWT token with 200 OK]
    final UserDetails userDetails = appUserDetailService.loadUserByUsername(req.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    UserEntity user = userValidatorService.ifFoundByUsernameReturnElseThrow(req.getUsername());

    return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getAuthority()));
  }catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
  }

}
