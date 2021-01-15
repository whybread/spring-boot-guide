package com.whybread.tutorial.api.api.dao;

import java.util.ArrayList;
import java.util.List;

import com.whybread.tutorial.api.api.dto.User;

import org.springframework.stereotype.Component;

/*
 * A simple DAO class that handles accessing User.
 * 
 * However, it doesn't actually deal with a real data.
 * It is a pseudo-DAO as this project focuses on the API, not persistence.
 */
@Component
public class UserDao {

  public User selectUserById(int id) {
    if (id > 0)
      return new User(id, "User[" + id + "]", "User[" + id + "]@gmail.com");
    else
      return null;
  }

  public List<User> selectAllUsers() {
    List<User> userList = new ArrayList<User>();

    for (int i = 1; i <= 10; i++) {
      userList.add(selectUserById(i));
    }

    return userList;
  }

}
