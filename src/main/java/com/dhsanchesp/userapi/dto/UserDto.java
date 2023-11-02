package com.dhsanchesp.userapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class UserDto {
    final String id;
    final String firstName;
    final String lastName;

    public UserDto(
        @JsonProperty(value = "id") final String id,
        @JsonProperty(value = "first_name") final String firstName,
        @JsonProperty(value = "last_name") final String lastName
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
