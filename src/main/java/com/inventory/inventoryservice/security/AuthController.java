package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.AuthenticationRequest;
import com.inventory.inventoryservice.security.model.AuthenticationResponse;
import com.inventory.inventoryservice.security.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
    );

    final UserDetails userDetails = appUserDetailService.loadUserByUsername(req.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    UserEntity user = userValidatorService.ifFoundByUsernameReturnElseThrow(req.getUsername());


    return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getAuthority()));

  }

}
