package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickDetailRepository;
import com.chilkens.timeset.dao.PickRepository;
import com.chilkens.timeset.dao.TimetableRepository;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.dto.PickRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    TimetableRepository timetableRepository;

    @Transactional
    public Pick savePick(PickRequest pickRequest){

        // 트랜잭션 삽입
        Pick pick = pickRequest.getPick();
        List<PickDetail> pickDetailList = pickRequest.getPickDetailList();

        // pick 정보 저장
        Pick savedPick = pickRepository.save(pick);

        // pick_detail 저장
        for (int i = 0; i < pickDetailList.size(); i++) {
            PickDetail pickDetail = pickDetailList.get(i);
            pickDetail.setPickId(savedPick.getPickId());

            pickDetailRepository.save(pickDetail);
        }

        // time_table current +1
        timetableRepository.updateCurrrent(pick.getTableId());

        return savedPick;
    }

    // public void savePickDetail(PickDetail pickDetail){ pickDetailRepository.save(pickDetail); }
}
