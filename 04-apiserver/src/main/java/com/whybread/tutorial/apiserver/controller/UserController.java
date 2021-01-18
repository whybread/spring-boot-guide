package com.whybread.tutorial.apiserver.controller;

import java.util.Map;

import com.whybread.tutorial.apiserver.model.ApiResult;
import com.whybread.tutorial.apiserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@RestController
/* 
 * We prefer 'users' as the resource name rather then 'user'.
 * It's one of the recommended to design RESTful API.
 * 
 * More about RESTful API design.
 *      Korean: https://hankkuu.tistory.com/21
 *      English: https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/
 * 
 * It doesn't support many things, but it would be enough to solve your technical issues.
 * 
*/
@Slf4j
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  UserService userService;

  // Only this method logs a header information.
  // Various ways to access HTTP headers from the reference (English): https://www.baeldung.com/spring-rest-http-headers
  @GetMapping(value = "")
  public ApiResult getUsers(@RequestHeader HttpHeaders headers) {
    log.info("header: " + headers);

    return userService.getUsersApi();
  }

  @GetMapping(value = "/{userId}")
  public ApiResult getUser(@PathVariable int userId) {
    return userService.getUserApi(userId);
  }

  @PostMapping(value = "")
  public String postUsers() {
    return "postUsers() called.";
  }

}
