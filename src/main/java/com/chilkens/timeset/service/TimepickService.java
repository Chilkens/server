package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickDetailRepository;
import com.chilkens.timeset.dao.PickRepository;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import com.chilkens.timeset.dto.PickRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Service
public class TimepickService {

    @Autowired
    PickRepository pickRepository;

    @Autowired
    PickDetailRepository pickDetailRepository;

    public void savePick(PickRequest pickRequest){

        // 트랜잭션 삽입

        Pick pick = pickRequest.getPick();
        List<PickDetail> pickDetailList = pickRequest.getPickDetailList();

        Pick savedPick = pickRepository.save(pick);

        for (int i = 0; i < pickDetailList.size(); i++) {
            PickDetail pickDetail = pickDetailList.get(i);
            pickDetail.setPickId(savedPick.getPickId());

            pickDetailRepository.save(pickDetail);
        }
    }

    // public void savePickDetail(PickDetail pickDetail){ pickDetailRepository.save(pickDetail); }
}
