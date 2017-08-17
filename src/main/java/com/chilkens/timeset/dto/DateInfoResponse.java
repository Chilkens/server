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
public class DateInfoResponse {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date date;

    private List<Integer> time;

    public static DateInfoResponse build(Date date, List<Integer> time) {
        return DateInfoResponse.builder()
                .date(date)
                .time(time)
                .build();
    }
}
