package com.example.demoapp.model.rest;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * RegistrationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-20T13:15:26.245188900+01:00[Europe/Rome]")
public class RegistrationRequest {

  private String username;

  private String passwd;

  private String firstName;

  private String lastName;

  /**
   * Default constructor
   * @deprecated Use {@link RegistrationRequest#RegistrationRequest(String, String, String, String)}
   */
  @Deprecated
  public RegistrationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RegistrationRequest(String username, String passwd, String firstName, String lastName) {
    this.username = username;
    this.passwd = passwd;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public RegistrationRequest username(String username) {
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

  public RegistrationRequest passwd(String passwd) {
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

  public RegistrationRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  */
  @NotNull @Size(min = 1, max = 20) 
  @Schema(name = "first_name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public RegistrationRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  */
  @NotNull @Size(min = 1, max = 20) 
  @Schema(name = "last_name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationRequest registrationRequest = (RegistrationRequest) o;
    return Objects.equals(this.username, registrationRequest.username) &&
        Objects.equals(this.passwd, registrationRequest.passwd) &&
        Objects.equals(this.firstName, registrationRequest.firstName) &&
        Objects.equals(this.lastName, registrationRequest.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, passwd, firstName, lastName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrationRequest {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    passwd: ").append(toIndentedString(passwd)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
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

