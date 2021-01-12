package com.whybread.tutorial.jdbc.service;

import java.util.List;

import com.whybread.tutorial.jdbc.data.dao.domesticuser.DomesticUserDao;
import com.whybread.tutorial.jdbc.data.dto.DomesticUserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomesticUserService {

  @Autowired
  public DomesticUserDao domesticUserDao;

  public List<DomesticUserDto> selectUsersByCountry(String country) throws Exception {
    final DomesticUserDto param = new DomesticUserDto(0, null, country);

    List<DomesticUserDto> userList = domesticUserDao.selectUsers(param);

    return userList;
  }

}
