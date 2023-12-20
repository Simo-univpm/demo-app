package com.example.demoapp.model.entities;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * LastAccessEntity
 */
@Entity
@Table(name = "last_access")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-20T13:15:26.245188900+01:00[Europe/Rome]")
public class LastAccessEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "last_access")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private java.sql.Timestamp lastAccess;

  public LastAccessEntity id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LastAccessEntity userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userId")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LastAccessEntity lastAccess(java.sql.Timestamp lastAccess) {
    this.lastAccess = lastAccess;
    return this;
  }

  /**
   * Get lastAccess
   * @return lastAccess
  */
  @Valid 
  @Schema(name = "last_access", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last_access")
  public java.sql.Timestamp getLastAccess() {
    return lastAccess;
  }

  public void setLastAccess(java.sql.Timestamp lastAccess) {
    this.lastAccess = lastAccess;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LastAccessEntity lastAccessEntity = (LastAccessEntity) o;
    return Objects.equals(this.id, lastAccessEntity.id) &&
        Objects.equals(this.userId, lastAccessEntity.userId) &&
        Objects.equals(this.lastAccess, lastAccessEntity.lastAccess);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, lastAccess);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LastAccessEntity {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    lastAccess: ").append(toIndentedString(lastAccess)).append("\n");
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

