package com.chilkens.timeset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * Created by hoody on 2017-08-02.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DateInfo implements java.lang.Comparable<DateInfo>{
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer time;

    public static DateInfo build(Date date, Integer time) {
        return DateInfo.builder()
                .date(date)
                .time(time)
                .build();
    }
    //Date, time 기준 오름차순 정렬
    @Override
    public int compareTo(DateInfo o) {
        if(this.date.compareTo(o.date) == 0){
            if (this.time > o.time) {
                return 1;
            }
            return -1;
        }
        return this.date.compareTo(o.date);
    }
}
