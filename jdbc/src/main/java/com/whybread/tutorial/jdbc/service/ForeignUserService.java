package com.whybread.tutorial.jdbc.service;

import java.util.List;

import com.whybread.tutorial.jdbc.data.dao.foreignuser.ForeignUserDao;
import com.whybread.tutorial.jdbc.data.dto.ForeignUserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForeignUserService {

  @Autowired
  public ForeignUserDao foreignUserDao;

  public List<ForeignUserDto> selectUsersByCountry(String country) throws Exception {
    final ForeignUserDto param = new ForeignUserDto(0, null, country);

    List<ForeignUserDto> userList = foreignUserDao.selectUsers(param);

    return userList;

  }

}
