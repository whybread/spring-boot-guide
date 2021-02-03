package com.whybread.tutorial.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class AdminController {

  /*
   *`@PreAuthorize` is an annotation for specifying a method access-control expression
   * which will be evaluated to decide whether a method invocation is allowed or not.
   * 
   * For usage, refer to the official document linked below.
   * 
   * Official document for `@Pre` and `@Post` annotations (about authorization)
   *    [15. Expression-Based Access Control]
   *    (https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html)
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @RequestMapping(path = "/admin")
  public String index() {
    return "admin";
  }
}
