package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickDetailRepository;
import com.chilkens.timeset.dao.PickRepository;
import com.chilkens.timeset.dao.TimetableRepository;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

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
    public Pick savePick(Pick pick, String pickDetailList) throws Exception{

        // Pick pick = pickRequest.getPick();
        // List<PickDetail> pickDetailList = pickRequest.getPickDetailList();

        // pick 정보 저장
        Pick savedPick = pickRepository.save(pick);

        //JSONArray jsonArray = new JSONArray(pickRequest.getPickDetailParam());

        // String --> JSON Object
        JSONObject jsonObject = new JSONObject(pickDetailList);
        // KEY 값 추출
        Iterator iterator = jsonObject.keys();
        // DATE 포맷 설정
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        while (iterator.hasNext()){
            // KEY 값 순서대로 추출
            String key = iterator.next().toString();
            // String --> Date
            Date pickDate = format.parse(key);
            // const, set
            PickDetail pickDetail = new PickDetail();

            pickDetail.setPickId(savedPick.getPickId());
            pickDetail.setPickDate(pickDate);
            // 시간 리스트 추출
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            // 시간 리스트 --> String
            String pickTime = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                if(i == (jsonArray.length() - 1)){
                    pickTime = pickTime + jsonArray.get(i);
                } else {
                    pickTime = pickTime + jsonArray.get(i) + "|";
                }
            }
            // set
            pickDetail.setPickTime(pickTime);

            pickDetailRepository.save(pickDetail);
        }

//        // pick_detail 저장
//        for (int i = 0; i < pickDetailList.size(); i++) {
//            PickDetail pickDetail = pickDetailList.get(i);
//            pickDetail.setPickId(savedPick.getPickId());
//
//            pickDetailRepository.save(pickDetail);
//        }

        // time_table current +1
        timetableRepository.updateCurrrent(pick.getTableId());

        return savedPick;
    }

    // public void savePickDetail(PickDetail pickDetail){ pickDetailRepository.save(pickDetail); }
}
