package com.chilkens.timeset.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Created by hoody on 2017-08-12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class IntersectionResponse {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private List<Integer> time;

    public static IntersectionResponse build(Date date, List<Integer> time) {
        return IntersectionResponse.builder()
                .date(date)
                .time(time)
                .build();
    }
}
