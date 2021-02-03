package com.whybread.tutorial.security.service;

import com.whybread.tutorial.security.dto.UserDto;
import com.whybread.tutorial.security.entity.UserEntity;
import com.whybread.tutorial.security.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * `UserService` is a custom implementation of `UserDetailsService` that is needed to use Spring Security's user authentication.
 * 
 * This class must implement `loadUserByUsername(String username)` method, which returns a `UserDetails` object.
 * In my implementation, it returns a `UserEntity` (which extends `UserDetails`) object.
 * 
 * I made a few more methods to serve a role for user accounts management.
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findByUsername(username);

    return userEntity;
  }

  /*
   * This method makes a new `UserEntity` object with a `UserDto` parameter, and saves it with a `UserRepository` bean.
   * 
   * @Returns `true` if the registration is successful, `false` otherwise.
   */
  public boolean registerUser(UserDto userDto) {
    if (isInvalidRegistration(userDto)) {
      return false;
    }

    UserEntity userEntity = UserEntity.builder()
      .username(userDto.getUsername())
      .email(userDto.getEmail())
      .passwordEncrypted(passwordEncoder.encode(userDto.getPassword()))
      .role("ROLE_USER")
      .build();

    userRepository.save(userEntity);

    return true;
  }
  
  /*
   * This method chekcs if the `UserDto` type parameter has a valid information to be registered.
   * 
   * @Returns `true` if the information is **invalid**, `false` otherwise.
   */
  public boolean isInvalidRegistration(UserDto userDto) {
    if (!userDto.getPassword().equals(userDto.getPasswordRepeated())) {
      log.info("[Invalid Registration]Password not matched: '" + userDto.getPassword() + "' and '" + userDto.getPasswordRepeated() + "'.");
      return true;
    }
    
    if( userRepository.findByUsername(userDto.getUsername()) != null ){
      log.info("[Invalid Registration]Username already exists: '" + userDto.getUsername() + "'.");
      return true;
    }

    if( userRepository.findByEmail(userDto.getEmail()) != null ){
      log.info("[Invalid Registration]Email already exists: '" + userDto.getEmail() + "'.");
      return true;
    }

    return false;
  }

  /*
   * This method find a `UserEntity` object with information in the `UserDto` parameter, and **Remove User** (set `isDeleted` to 1) it with a `UserRepository` bean if valid.
   * 
   * @Returns `true` if the registration is successful, `false` otherwise.
   */
  public boolean removeUser(UserDto userDto) {
    UserEntity userEntity = userRepository.findByUsername(userDto.getUsername());

    if( userEntity == null ){
      log.info("[Invalid RemoveUser]Invalid username or password: Accounts not found.");
      return false;
    }else if( !passwordEncoder.matches(userDto.getPassword(), userEntity.getPasswordEncrypted()) ) {
      log.info("[Invalid RemoveUser]Invalid username or password: Accounts not found.");
      return false;
    }else if( userEntity.isDeleted() ) {
      log.error("[Invalid RemoveUser]Invalid User Account: Exists but already removed.");
      log.error("Invalid user " + userEntity.getId() + " with username '" + userEntity.getUsername() + "'.");
      return false;
    }
    
    String description = "Removed user: Username(" + userEntity.getUsername() + "), Email(" + userEntity.getEmail() + ") with Role(" + userEntity.getRole() + ").";
    userEntity.setUsername(null);
    userEntity.setEmail(null);
    userEntity.setPasswordEncrypted(null);
    userEntity.setRole(null);
    userEntity.setDescription(description);
    userEntity.setDeleted(true);

    userRepository.save(userEntity);

    return true;
  }

}
