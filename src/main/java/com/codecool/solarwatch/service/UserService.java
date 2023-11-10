package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.NotFoundException;
import com.codecool.solarwatch.model.dto.CreateUserDTO;
import com.codecool.solarwatch.model.entity.Role;
import com.codecool.solarwatch.model.entity.UserEntity;
import com.codecool.solarwatch.service.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void deleteUserById(long id) {
    userRepository.deleteById(id);
  }

  public void createUser(CreateUserDTO createUserDTO) {
    UserEntity user = UserEntity.builder()
                                .role(Role.ROLE_USER)
                                .username(createUserDTO.getUsername())
                                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                                .build();
    userRepository.save(user);
    System.out.println(user.getRole());

  }

  public UserEntity getUserById(long id) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    }
    throw new NotFoundException("User", id);
  }

  public void addAdminTo(long id){
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()){
      UserEntity presentUser = user.get();
      presentUser.setRole(Role.ROLE_ADMIN);
      userRepository.save(presentUser);
    } else {
      throw new NotFoundException("User", id);
    }
  }
}
