package com.george.countandmovement.exception;

public class AccountAlreadyExistsException extends RuntimeException {
  public AccountAlreadyExistsException(String message) {
    super(message);
  }
}