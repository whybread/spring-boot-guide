package com.whybread.tutorial.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  /*
   * I want all URLs following `/*` to be mapped to this method except for few system words.
   * As `@PreAuthorize` annotation supports SpEL (i.e. Spring Expression Language), we can implement it very easily.
   *
   * Two useful websites are provided below.
   * > Note that they are about Regex, not SpEL.
   * 
   * - An expression that finds all except a specific word.
   *        [5.4. Find All Except a Specific Word - Regular Expressions Cookbook, 2nd Edition [Book]]
   *        (https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch05s04.html)
   * 
   * - A regular expression tester
   *      [all except word - Regex Tester/Debugger]
   *      (https://www.regextester.com/94017)
   */
  @ResponseBody
  @RequestMapping(path = "/{usernameOnUrl:\\b(?!public|admin\\b)\\w+}")
  @PreAuthorize("permitAll")
  public String userPage(@PathVariable final String usernameOnUrl) {
    return "User page for " + usernameOnUrl;
  }


  /*
   * With Spring Security, it's even possible to ensure that a user can only see one's own page.
   * 
   * Implementation reference
   *    [java - Ensure a particular user can only see their own user details - Using Spring - Stack Overflow]
   *    (https://stackoverflow.com/questions/55893083/ensure-a-particular-user-can-only-see-their-own-user-details-using-spring/55893438#55893438)
   */
  @ResponseBody
  @RequestMapping(path = "/{usernameOnUrl:\\b(?!public|admin\\b)\\w+}/settings")
  @PreAuthorize("@authenticationService.hasAccess(#usernameOnUrl)")
  public String userCPage(@PathVariable final String usernameOnUrl) {
    return "User content page for " + usernameOnUrl;
  }

}
