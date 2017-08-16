package com.chilkens.timeset.dto;

import com.chilkens.timeset.domain.DateInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by hoody on 2017-08-12.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubIntersectionResponse {
    List<String> possible; //해당시간에 모임에 참석 가능한 사람 name list
    List<String> impossible; //해당시간에 모임에 참석 불가능한 사람 name list
    List<IntersectionResponse> intersection;

    public static SubIntersectionResponse build(List<String> possible, List<String> impossible, List<IntersectionResponse> Intersection) {
        return SubIntersectionResponse.builder()
                .possible(possible)
                .impossible(impossible)
                .intersection(Intersection)
                .build();
    }
}
