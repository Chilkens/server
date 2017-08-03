package com.chilkens.timeset.domain;

import lombok.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by hoody on 2017-08-02.
 */

/*
    pick 테이블과 pick_detail 테이블을 JOIN 해서 intersection 계산에 필요한 컬럼들을 저장한 DTO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PickInfo {
    private Long pickId;
    private List<DateInfo> dateInfos;

    public static PickInfo build(Long pickId, List<DateInfo> dateInfos) {
        return PickInfo.builder()
                .pickId(pickId)
                .dateInfos(dateInfos)
                .build();
    }
}
