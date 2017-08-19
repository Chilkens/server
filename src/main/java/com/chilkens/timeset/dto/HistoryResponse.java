package com.chilkens.timeset.dto;

import com.chilkens.timeset.domain.Timetable;
import lombok.*;

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
