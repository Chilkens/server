package com.chilkens.timeset.dto;

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
public class IntersectionResponse {
    Boolean hasIntersection; // 교집합이 있는지 여부 (없을 경우 차선책 return - false)
    List<String> possible; // 해당시간에 모임에 참석 가능한 사람 name list
    List<String> impossible; // 해당시간에 모임에 참석 불가능한 사람 name list
    List<DateInfoResponse> intersection;

    public static IntersectionResponse build(Boolean hasIntersection, List<String> possible, List<String> impossible, List<DateInfoResponse> Intersection) {
        return IntersectionResponse.builder()
                .hasIntersection(hasIntersection)
                .possible(possible)
                .impossible(impossible)
                .intersection(Intersection)
                .build();
    }
}
