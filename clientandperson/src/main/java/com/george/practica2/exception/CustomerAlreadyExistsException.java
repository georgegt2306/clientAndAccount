package com.george.practica2.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
  public CustomerAlreadyExistsException(String message) {
    super(message);
  }
}