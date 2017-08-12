package com.chilkens.timeset.domain;

import com.chilkens.timeset.dto.PickRequest;
import lombok.*;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;

import java.util.List;

/**
 * Created by ByeongChan on 2017. 8. 11..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CountInfo {
    private int count;

    private List<Long> pickId;

    public static CountInfo build(int count, List<Long> pickId) {
        return CountInfo.builder()
                .count(count)
                .pickId(pickId)
                .build();
    }
}
