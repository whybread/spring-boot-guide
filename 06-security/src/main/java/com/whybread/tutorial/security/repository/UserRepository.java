package com.whybread.tutorial.security.repository;

import com.whybread.tutorial.security.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A Repository class for `UserEntity`.
 * 
 * Almost a basic Entity class with JPA. If you need, see the provided materials below.
 * English: [Getting Started | Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
 * Korean: [갓대희의 작은공간 :: [스프링부트 (7)] Spring Boot JPA(1) - 시작 및 기본 설정](https://goddaehee.tistory.com/209)
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByUsername(String username);

  UserEntity findByEmail(String email);
}