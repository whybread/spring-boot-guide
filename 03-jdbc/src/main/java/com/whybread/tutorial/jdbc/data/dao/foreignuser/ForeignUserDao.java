package com.whybread.tutorial.jdbc.data.dao.foreignuser;

import java.util.List;

import com.whybread.tutorial.jdbc.data.dto.ForeignUserDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ForeignUserDao {
  List<ForeignUserDto> selectUsers(ForeignUserDto param) throws Exception;
}