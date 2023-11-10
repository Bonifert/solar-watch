package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.dto.CreateUserDTO;
import com.codecool.solarwatch.model.dto.JwtDTO;
import com.codecool.solarwatch.model.dto.SignInDTO;
import com.codecool.solarwatch.model.entity.UserEntity;
import com.codecool.solarwatch.security.jwt.JwtUtils;
import com.codecool.solarwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
    return ResponseEntity.ok().body(userService.getUserById(id));
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtDTO> signIn(@RequestBody SignInDTO signInDTO) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.username(),
                                                                                                               signInDTO.password()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    User user = (User) authentication.getPrincipal();
    String jwt = jwtUtils.generateJwtToken(authentication);
    List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    return ResponseEntity.ok().body(new JwtDTO(jwt, user.getUsername(), roles));
  }

  @PostMapping("/register")
  public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
    userService.createUser(createUserDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/admin/{userId}")
  public ResponseEntity<Void> addAdminToUser(@PathVariable long userId) {
    userService.addAdminTo(userId);
    return ResponseEntity.ok().build();
  }
}
