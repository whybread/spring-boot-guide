package com.whybread.tutorial.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An Entity class for a `User` in `USER_TB` table, which has all information about users.
 * 
 * Almost a basic Entity class with JPA. If you need, see the provided materials below.
 * English: [Getting Started | Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
 * Korean: [갓대희의 작은공간 :: [스프링부트 (7)] Spring Boot JPA(1) - 시작 및 기본 설정](https://goddaehee.tistory.com/209)
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
/*
 * `@DynamicInsert`, `@DynamicUpdate` annotations are used to exclude `null` fields to save to DB.
 * 
 * Reference
 *    [jpa insert 시 default 값 적용]
 *    (https://dotoridev.tistory.com/6)
 */
@DynamicInsert
@DynamicUpdate
@Entity(name = "USER_TB")
public class UserEntity implements UserDetails {

  /**
   * Auto-generated serial version ID by VS-Code.
   */
  private static final long serialVersionUID = -4032738557497554698L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "USERNAME")
  private String username;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PASSWORD_HASHED")
  private String passwordHashed;
  @Column(name = "ROLE")
  private String role;
  @Column(name = "IS_DELETED")
  private boolean isDeleted;
  @Column(name = "CREATED_DATETIME")
  private String created_datetime;
  @Column(name = "UPDATED_DATETIME")
  private String updated_datetime;
  @Column(name = "DESCRIPTION")
  private String description;

  @Builder
  public UserEntity(String username, String email, String passwordHashed, String role) {
    this.username = username;
    this.email = email;
    this.passwordHashed = passwordHashed;
    this.role = role;
  }

  /*
   * This implementation refers to the link below.
   * 
   * Reference 1: [Granted Authority Versus Role in Spring Security | Baeldung](https://www.baeldung.com/spring-security-granted-authority-vs-role)
   * Reference 2: [How do I copy a tab's title in Chrome? - Super User](https://debugdaldal.tistory.com/89)
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    // System.out.println("authorities of " + username + " is: " + authorities);
    return authorities;
  }

  @Override
  public String getPassword() {
    return passwordHashed;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return !isDeleted;
  }

}
