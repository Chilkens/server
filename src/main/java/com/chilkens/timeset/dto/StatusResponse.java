package com.chilkens.timeset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hoody on 2017-08-25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponse {
    private String message;

    public static StatusResponse build(String message) {
        return StatusResponse.builder()
                .message(message).build();
    }
}
