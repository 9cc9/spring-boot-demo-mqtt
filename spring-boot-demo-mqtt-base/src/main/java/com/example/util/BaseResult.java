package com.example.util;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @param <T>
 */
public class BaseResult<T> {
  private boolean success;
  private String message;
  private T data;

  public BaseResult() {
  }

  public BaseResult(boolean success, String message, T data) {
    this.success = success;
    this.message = message;
    this.data = data;
  }

  public static <T> BaseResult<T> success(T data) {
    return new BaseResult<>(true, "Operation successful", data);
  }

  public static <T> BaseResult<T> fail(String message) {
    return new BaseResult<>(false, message, null);
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
