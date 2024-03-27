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
public class PagingResponse {

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("total_page")
    private Integer totalPage;

    private Integer size;
}
