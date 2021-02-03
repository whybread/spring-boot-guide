package com.whybread.tutorial.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity(name = "USER_TB")
public class UserEntity implements UserDetails {

  /**
   * Auto-generated serial version ID by VS-Code.
   */
  private static final long serialVersionUID = -4032738557497554698L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;
  @Column(name = "password_encrypted")
  private String passwordEncrypted;
  @Column(name = "role")
  private String role;
  @Column(name = "is_deleted")
  private boolean isDeleted;
  @Column(name = "created_datetime")
  private String created_datetime;
  @Column(name = "updated_datetime")
  private String updated_datetime;
  @Column(name = "description")
  private String description;

  @Builder
  public UserEntity(String username, String email, String passwordEncrypted, String role) {
    this.username = username;
    this.email = email;
    this.passwordEncrypted = passwordEncrypted;
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
    return passwordEncrypted;
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
