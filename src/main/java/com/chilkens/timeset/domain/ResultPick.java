package com.chilkens.timeset.domain;

import lombok.*;

import java.util.List;

/**
 * Created by ByeongChan on 2017. 8. 11..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResultPick {
    private List<Long> pickId;

    private Long resultTime;

    public static ResultPick build(List<Long> pickId, Long resultTime) {
        return ResultPick.builder()
                .pickId(pickId)
                .resultTime(resultTime)
                .build();
    }
}
