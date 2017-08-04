package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickJoinRepository;
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
    PickJoinRepository pickJoinRepository;

    //pick, pick_detail 테이블에서 Join된 데이터를 계산에 필요한 데이터로 바꿔줌.
    public List<PickInfo> getPickInfo(Long tableId) {
        List<PickInfo> pickInfoList = new ArrayList<>();
        List<DateInfo> dateInfoList;

        List<PickJoin> picks = pickJoinRepository.findByTableId(tableId);
        for (PickJoin pick : picks) {
            dateInfoList = new ArrayList<>();
            for (PickDetail detail : pick.getPickDetailList()) {
                //time split
                for (String time : Arrays.asList(detail.getPickTime().split("\\|"))) {
                    dateInfoList.add(DateInfo.build(detail.getPickDate(), Integer.valueOf(time)));
                }
            }
            pickInfoList.add(PickInfo.build(pick.getPickId(), dateInfoList));
        }
        return pickInfoList;
    }

    //해당하는 테이블의 모든 시간 교집합 찾아서 return
    //Guava 교집합 method 이용
    //TODO
    public List<DateInfo> getAllIntersection(Long tableId) {
        List<PickInfo> picks = getPickInfo(tableId);
        List<DateInfo> intersections;
        Set<DateInfo> preSet = new HashSet<>(); //intersectionSet
        Set<DateInfo> curSet;
        int i = 0;

        //첫번째 시간 정보 preSet에 저장
        for (DateInfo dateInfo : picks.get(0).getDateInfos()) {
            preSet.add(dateInfo);
        }

        for (PickInfo pick : picks) { //picks.subList(1, picks.size())
            if (i++ == 0) { //첫번째정보 위에서 저장했기때문에 바로 빠져나옴
                continue;
            }

            curSet = new HashSet<>();
            for (DateInfo dateInfo : pick.getDateInfos()) {
                curSet.add(dateInfo);
            }

            preSet = Sets.intersection(preSet, curSet); // intersectionSet 구해서 preSet에 계속 저장

            if (preSet == null) { //교집합이 없으면 바로 return
                return null;
            }
        }

        // Set을 을 정렬하고 List로 변환
        intersections = new ArrayList<>(preSet);
        Collections.sort(intersections);

        return intersections;
    }

    //해당하는 타임테이블의 모임 시간에 맞춘 시간 교집합 찾아서 return
    public List<DateInfo> getIntersection(Long tableId, int minSize) {
        List<DateInfo> intersections = getAllIntersection(tableId);
        List<DateInfo> intersectionsByTime = new ArrayList<>();

        // 맨 처음에 최소시간 1이면 바로 return
        if (minSize == 1) {
            return intersections;
        }

        Date preDate = intersections.get(0).getDate();
        int preTime = intersections.get(0).getTime();
        int size = 0;
        int p = 0;

        for (int i = 1; i < intersections.size(); i++) {
            if (preTime + 1 == intersections.get(i).getTime() && preDate.equals(intersections.get(i).getDate())) {
                size++;
            } else {
                if (size + 1 >= minSize) {
                    intersectionsByTime.addAll(intersections.subList(p, p + size + 1));
                }
                size = 0;
                p = i;
            }
            //last index
            if (i == intersections.size() - 1 && size + 1 >= minSize) {
                intersectionsByTime.addAll(intersections.subList(p, p + size + 1));
            }
            preTime = intersections.get(i).getTime();
            preDate = intersections.get(i).getDate();
        }
        return intersectionsByTime;
    }
}
