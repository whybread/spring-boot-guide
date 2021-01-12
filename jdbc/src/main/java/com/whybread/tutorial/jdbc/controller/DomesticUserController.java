package com.whybread.tutorial.jdbc.controller;

import java.util.List;

import com.whybread.tutorial.jdbc.data.dto.DomesticUserDto;
import com.whybread.tutorial.jdbc.service.DomesticUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domestic-users")
public class DomesticUserController {

  @Autowired
  private DomesticUserService domesticUserService;

  @RequestMapping("")
  public List<DomesticUserDto> users(@RequestParam(value = "country", defaultValue = "") String country)
      throws Exception {
    List<DomesticUserDto> userList = domesticUserService.selectUsersByCountry(country);

    return userList;
  }
}
