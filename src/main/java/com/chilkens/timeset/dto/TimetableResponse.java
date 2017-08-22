package com.chilkens.timeset.dto;

import lombok.*;

/**
 * Created by hoody on 2017-08-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimetableResponse {
    String keyUrl;

    public static TimetableResponse build(String keyUrl) {
        return TimetableResponse.builder()
                .keyUrl(keyUrl)
                .build();
    }
}
