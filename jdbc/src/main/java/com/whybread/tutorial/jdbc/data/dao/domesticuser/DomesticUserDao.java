package com.whybread.tutorial.jdbc.data.dao.domesticuser;

import java.util.List;

import com.whybread.tutorial.jdbc.data.dto.DomesticUserDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DomesticUserDao {
  /*
   * This method returns a List object which contains users selected by their country from db-local-1.
   * If the country is an empty String "", select all users.
   * Implementation by MyBatis, refered to classpath:mapper/local1/user-mapper.xml
   */
  List<DomesticUserDto> selectUsers(DomesticUserDto param) throws Exception;
}