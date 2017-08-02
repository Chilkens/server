package com.chilkens.timeset.dto;

import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import lombok.*;

import java.util.List;

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

    private List<PickDetail> pickDetailList;

    public static PickRequest build(Pick pick, List<PickDetail> pickDetailList) {
        return PickRequest.builder()
                .pick(pick)
                .pickDetailList(pickDetailList)
                .build();
    }
}
