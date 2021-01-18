package com.whybread.tutorial.apiserver.service;

import com.whybread.tutorial.apiserver.dao.UserDao;
import com.whybread.tutorial.apiserver.dto.User;
import com.whybread.tutorial.apiserver.model.ApiResult;
import com.whybread.tutorial.apiserver.model.ApiResult.ApiResultType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * UserService implements a number of useful methods such as `getUsersApi` with a UserDao Bean.
 * 
 * Actually, as this chapter argues about API, thus the implementation is very short and clear.
 * However, this plays a good role as a @Service.
 */
@Service
public class UserService {

  @Autowired
  UserDao userDao;

  // Returns an ApiResult object with all users' data.
  public ApiResult getUsersApi() {
    return ApiResult.create(userDao.selectAllUsers());
  }

  // Returns an ApiResult object with a certain user's data found by userId argument.
  public ApiResult getUserApi(int userId) {
    User user = userDao.selectUserById(userId);

    if (user == null)
      return new ApiResult(ApiResultType.FAIL);
    else
      return ApiResult.create(user);
  }

}
