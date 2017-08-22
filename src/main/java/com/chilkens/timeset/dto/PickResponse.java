package com.chilkens.timeset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hoody on 2017-08-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickResponse {
    Long pickId;

    public static PickResponse build(Long pickId) {
        return PickResponse.builder()
                .pickId(pickId)
                .build();
    }
}
