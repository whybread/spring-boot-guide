package com.whybread.tutorial.security.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO class for a `User`, which is used to transfer the data from the HTTP post.
 * 
 * When a user try to register from `/register`, the four fields will all be used.
 * When a user try to removeUser from `/removeUser`, only two fields (i.e. `username` and `password`) will be used (and rest are `null`).
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

  String username;
  String email;
  String password;
  String passwordRepeated;

}
