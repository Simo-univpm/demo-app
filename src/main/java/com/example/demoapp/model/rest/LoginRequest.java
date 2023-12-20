package com.example.demoapp.model.rest;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * LoginRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-20T13:15:26.245188900+01:00[Europe/Rome]")
public class LoginRequest {

  private String username;

  private String passwd;

  /**
   * Default constructor
   * @deprecated Use {@link LoginRequest#LoginRequest(String, String)}
   */
  @Deprecated
  public LoginRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LoginRequest(String username, String passwd) {
    this.username = username;
    this.passwd = passwd;
  }

  public LoginRequest username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @NotNull @Size(min = 1, max = 20) 
  @Schema(name = "username", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LoginRequest passwd(String passwd) {
    this.passwd = passwd;
    return this;
  }

  /**
   * Get passwd
   * @return passwd
  */
  @NotNull @Size(min = 8, max = 20) 
  @Schema(name = "passwd", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("passwd")
  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginRequest loginRequest = (LoginRequest) o;
    return Objects.equals(this.username, loginRequest.username) &&
        Objects.equals(this.passwd, loginRequest.passwd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, passwd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginRequest {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    passwd: ").append(toIndentedString(passwd)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

