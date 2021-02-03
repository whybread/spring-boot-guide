package com.whybread.tutorial.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whybread.tutorial.security.dto.UserDto;
import com.whybread.tutorial.security.service.AuthenticationService;
import com.whybread.tutorial.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * `HomeController` is a controller for basic common URLs.
 */

 /*  
 * I got a problem with `@PreAuthorize` annotation on the `@Controller` beans, and I solved that moving the annotations to the methods.
 * Please refer the link below for more information.
 *    [An Authentication object was not found in the SecurityContext - Spring 3.2.2 - Stack Overflow]
 *    (https://stackoverflow.com/questions/15500519/an-authentication-object-was-not-found-in-the-securitycontext-spring-3-2-2/49258960#49258960)
 * 
 */

// @PreAuthorize("permitAll")
@Controller
public class HomeController {

  @Autowired
  UserService userService;

  @Autowired
  AuthenticationService authenticationService;

  @PreAuthorize("permitAll")
  @RequestMapping(path = "/index")
  public String index_forward() {
    return "redirect:/";
  }

  @PreAuthorize("permitAll")
  @RequestMapping(path = "/")
  public String index() {
    return "index";
  }

  @PreAuthorize("permitAll")
  @RequestMapping(path = "/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    // Implementation reference
    //    [Spring Security로 로그인/회원가입 프로젝트]
    //    (https://shinsunyoung.tistory.com/78)
    new SecurityContextLogoutHandler().logout(request, response,
        SecurityContextHolder.getContext().getAuthentication());

    // It seems to be never reached. Maybe the above logout method does something.
    return "redirect:/";
  }

  @PreAuthorize("permitAll")
  @RequestMapping(path = "/login")
  public String login() {
    if(authenticationService.isAnonymousUser()) {
      return "login";
    } else {
      return "redirect:/";
    }
  }

  @PreAuthorize("permitAll")
  @GetMapping(path = "/register")
  public String register() {
    return "register";
  }

  @PreAuthorize("permitAll")
  @ResponseBody
  @PostMapping(path = "/register")
  public String register(UserDto userDto) {
    boolean registerIsSuccessful = userService.registerUser(userDto);

    if (registerIsSuccessful) {
      return "Registration has successed.";
    } else {
      return "Registration has failed.";
    }
  }

  @PreAuthorize("permitAll")
  @GetMapping(path = "/removeUser")
  public String removeUser() {
    return "removeUser";
  }

  @PreAuthorize("permitAll")
  @ResponseBody
  @PostMapping(path = "/removeUser")
  public String removeUser(UserDto userDto) {
    boolean removeUserIsSuccessful = userService.removeUser(userDto);

    if (removeUserIsSuccessful) {
      return "User remove has successed.";
    } else {
      return "User remove has failed.";
    }

  }

}
