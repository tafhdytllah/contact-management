package com.tafh.contactmanagement.restful.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {

    private String id;

    private String street;

    private String city;

    private String country;

    private String province;

    @JsonProperty("postal_code")
    private String postalCode;
}
