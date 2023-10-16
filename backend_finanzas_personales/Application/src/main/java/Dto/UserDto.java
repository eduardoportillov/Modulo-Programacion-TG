package Dto;

import java.util.UUID;

import Entities.User;

public class UserDto {
  public UUID key;

  public String email;
  public String password;

  public UserDto() {

  }

  public UserDto(User user) {
    this.key = user.key;
    this.email = user.email;
    this.password = user.password;
  }

  public UserDto(UUID key, String email, String password) {
    this.key = key;
    this.email = email;
    this.password = password;
  }

  public UUID getKey() {
    return key;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

}
