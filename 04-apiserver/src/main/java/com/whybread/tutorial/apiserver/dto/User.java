package com.whybread.tutorial.apiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * A simple DTO class representing user.
 */
@AllArgsConstructor
@Getter
@Setter
public class User {
  private final int userId;
  private final String userName;
  private final String userEmail;
}
