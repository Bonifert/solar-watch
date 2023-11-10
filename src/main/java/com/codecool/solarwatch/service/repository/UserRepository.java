package com.codecool.solarwatch.service.repository;

import com.codecool.solarwatch.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  //  Optional<UserEntity> findByUsername(String username);
//  Optional<UserEntity> findByUsername(String username);
  UserEntity findByUsername(String username);
}
