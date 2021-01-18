package com.whybread.tutorial.apiserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * This class is used as a response object for every api call.
 * 
 * The only field is apiResultType that is ApiResultType enum, which is either SUCCESS or FAIL.
 * 
 * It has a method-overloaded static helper method `create` that creates and returns a ApiResult object with a data argument.
 */
@Getter
public class ApiResult {
  @AllArgsConstructor
  public enum ApiResultType {
    SUCCESS(0, "success"), FAIL(-1, "fail");

    private int statusCode;
    private String resultMessage;
  }

  ApiResultType apiResultType;
  private Object data;

  public ApiResult(ApiResultType apiResultType) {
    this.apiResultType = apiResultType;
    this.data = null;
  }

  public ApiResult(ApiResultType apiResultType, Object data) {
    this.apiResultType = apiResultType;
    this.data = data;
  }

  public static ApiResult create(Object data) {
    ApiResult result = new ApiResult(ApiResultType.SUCCESS, data);
    return result;
  }

}
