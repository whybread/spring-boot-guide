package com.whybread.tutorial.jdbc.data.dao.domesticuser;

import java.util.List;

import com.whybread.tutorial.jdbc.data.dto.DomesticUserDto;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DomesticUserDao {
  List<DomesticUserDto> selectUsers(DomesticUserDto param) throws Exception;
}