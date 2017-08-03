package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickJoinRepository;
import com.chilkens.timeset.dao.PickRepository;
import com.chilkens.timeset.domain.*;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hoody on 2017-08-02.
 */
@Service
public class IntersectionService {
    @Autowired
    PickRepository pickRepository;

    @Autowired
    PickJoinRepository pickJoinRepository;

    //입력된 tableId에 해당하는 pickId들 찾아서 return
    public List<Long> findPickIdByTableId(Long tableId) {
        List<Long> pickIds = new ArrayList<>();
        List<Pick> picks = pickRepository.findByTableId(tableId);
        for(Pick pick : picks) {
            pickIds.add(pick.getPickId());
        }
        return pickIds;
    }

    //tableId에 해당하는 pick과 pick_detail을 조인하여 테이블 리스트를 return
    public List<PickJoin> findPickByTableId(Long tableId) {
        return pickJoinRepository.findByTableId(tableId);
    }

    //pick, pick_detail Join된 데이터를 계산에 필요한 데이터로 바꿔줌.
    public List<PickInfo> getPickInfo(Long tableId) {
        List<PickInfo> pickInfoList = new ArrayList<>();
        List<DateInfo> dateInfoList;

        List<PickJoin> picks = pickJoinRepository.findByTableId(tableId);
        for(PickJoin pick : picks) {
            dateInfoList = new ArrayList<>();
            for(PickDetail detail : pick.getPickDetailList()) {
                //time split
                for(String time : Arrays.asList(detail.getPickTime().split("\\|"))) {
                    dateInfoList.add(DateInfo.build(detail.getPickDate(), Integer.valueOf(time)));
                }
            }
            pickInfoList.add(PickInfo.build(pick.getPickId(), dateInfoList));
        }
        return pickInfoList;
    }

    //시간 교집합 찾아서 return
    //Guava 교집합 method 이용
    //TODO
    public List<DateInfo> getIntersection(Long tableId) {
        List<PickInfo> picks = getPickInfo(tableId);
        List<DateInfo> intersections;
        Set<DateInfo> preSet = new HashSet<>(); //intersectionSet
        Set<DateInfo> curSet;
        int i = 0;

        //첫번째 시간 정보 preSet에 저장
        for(DateInfo dateInfo : picks.get(0).getDateInfos()) {
            preSet.add(dateInfo);
        }

        for(PickInfo pick : picks) {
            if(i++ == 0) { //첫번째정보 위에서 저장했기때문에 바로 빠져나옴
                continue;
            }
            curSet = new HashSet<>();
            for(DateInfo dateInfo : pick.getDateInfos()) {
                curSet.add(dateInfo);
            }
            preSet = Sets.intersection(preSet, curSet); // intersectionSet 구해서 preSet에 계속 저장

            if(preSet == null) { //교집합이 없으면 바로 return
                return null;
            }
        }

        // Set을 을 정렬하고 List로 변환
        intersections = new ArrayList<>(preSet);
        Collections.sort(intersections);
        return intersections;
    }
}
