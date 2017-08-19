package com.chilkens.timeset.domain;

import lombok.*;
import org.hibernate.annotations.OrderBy;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {
    Timetable timetable;

    List<String> pickName;

    public static HistoryResponse build(Timetable timetable, List<String> pickName) {
        return HistoryResponse.builder()
                .timetable(timetable)
                .pickName(pickName)
                .build();
    }
}
