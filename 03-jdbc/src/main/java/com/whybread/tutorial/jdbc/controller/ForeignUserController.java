package com.whybread.tutorial.jdbc.controller;

import java.util.List;

import com.whybread.tutorial.jdbc.data.dto.ForeignUserDto;
import com.whybread.tutorial.jdbc.service.ForeignUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foreign-users")
public class ForeignUserController {

  @Autowired
  private ForeignUserService foreignUserService;

  @RequestMapping("")
  public List<ForeignUserDto> users(@RequestParam(value = "country", defaultValue = "") String country)
      throws Exception {
    List<ForeignUserDto> userList = foreignUserService.selectUsersByCountry(country);

    return userList;
  }
}
