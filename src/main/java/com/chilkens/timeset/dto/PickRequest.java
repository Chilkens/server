package com.chilkens.timeset.dto;

import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ByeongChan on 2017. 8. 2..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PickRequest {

    private Pick pick;

    private Map<String, int[]> pickDetailList;

    public static PickRequest build(Pick pick, Map<String, int[]> pickDetailList) {
        return PickRequest.builder()
                .pick(pick)
                .pickDetailList(pickDetailList)
                .build();
    }
}
