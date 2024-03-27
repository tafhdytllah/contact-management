package com.tafh.contactmanagement.restful.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateContactRequest {

    @JsonProperty("first_name")
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @JsonProperty("last_name")
    @Size(max = 100)
    private String lastname;

    @Size(max = 100)
    @Email
    private String email;

    //    @Pattern() use it for regex
    @Size(max = 100)
    private String phone;
}
