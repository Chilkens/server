package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickJoinRepository;
import com.chilkens.timeset.domain.*;
import com.chilkens.timeset.dto.IntersectionResponse;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hoody on 2017-08-02.
 */
/*
    시간 교집합 찾기
 */
@Service
public class IntersectionService {
    @Autowired
    PickJoinRepository pickJoinRepository;

    //pick, pick_detail 테이블에서 Join된 데이터를 계산에 필요한 데이터로 바꿔줌.
    public List<PickInfo> getPickInfo(Long tableId) {
        List<PickInfo> pickInfos = new ArrayList<>();
        List<DateInfo> dateInfos;

        List<PickJoin> picks = pickJoinRepository.findByTableId(tableId);
        for (PickJoin pick : picks) {
            dateInfos = new ArrayList<>();
            for (PickDetail detail : pick.getPickDetailList()) {
                //time split
                for (String time : Arrays.asList(detail.getPickTime().split("\\|"))) {
                    dateInfos.add(DateInfo.build(detail.getPickDate(), Integer.valueOf(time)));
                }
            }
            pickInfos.add(PickInfo.build(pick.getPickId(), dateInfos));
        }
        return pickInfos;
    }

    //해당하는 테이블의 모든 시간 교집합 찾아서 return
    //Guava 교집합 method 이용
    //TODO
    public List<DateInfo> getAllIntersection(Long tableId) {
        List<PickInfo> picks = getPickInfo(tableId);
        List<DateInfo> intersections = new ArrayList<>();
        Set<DateInfo> preSet = new HashSet<>(); //intersectionSet
        Set<DateInfo> curSet;
        List<Date> date;
        List<Integer> time;
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

            if (preSet == null) { //교집합이 없으면 바로 빈 배열 return
                return intersections;
            }
        }

        // Set을 을 정렬하고 List로 변환
        intersections = new ArrayList<>(preSet);
        Collections.sort(intersections);

        return intersections;
    }

    //해당하는 타임테이블의 모임 시간에 맞춘 시간 교집합 찾아서 return
    public List<IntersectionResponse> getIntersection(Long tableId, int minSize) {
        List<DateInfo> allIntersections = getAllIntersection(tableId);
        List<IntersectionResponse> intersections = new ArrayList<>();
        List<Integer> time = new ArrayList<>();

        if(allIntersections == null || allIntersections.size() == 0) {
            return null;
        }

        Date preDate = allIntersections.get(0).getDate();
        int preTime = allIntersections.get(0).getTime();
        int size = 0;
        int p = 0;
        time.add(allIntersections.get(p).getTime());

        for (int i = 1; i < allIntersections.size(); i++) {
            if (preTime + 1 == allIntersections.get(i).getTime() && preDate.equals(allIntersections.get(i).getDate())) {
                size++;
                time.add(allIntersections.get(i).getTime());
            } else {
                if (size + 1 >= minSize) {
                    intersections.add(new IntersectionResponse(allIntersections.get(p).getDate(), time));
                    time = new ArrayList<>();
                }
                size = 0;
                p = i;
                time.add(allIntersections.get(p).getTime());
            }
            //last index
            if (i == allIntersections.size() - 1 && size + 1 >= minSize) {
                intersections.add(new IntersectionResponse(allIntersections.get(p).getDate(), time));
                time = new ArrayList<>();
            }
            preTime = allIntersections.get(i).getTime();
            preDate = allIntersections.get(i).getDate();
        }
        return intersections;
    }

    //코드좀 정리하기
    public IntersectionResponse getOneIntersection(Long tableId, int minSize) {
        List<DateInfo> intersections = getAllIntersection(tableId);
        IntersectionResponse response = null;
        Date date;
        List<Integer> times = new ArrayList<>();

        if(intersections == null || intersections.size() == 0) {
            return null;
        }

        // 맨 처음에 최소시간 1이면 바로 return
        int randomIdx = new Random().nextInt(intersections.size());
        if (minSize == 1) {
            times.add(intersections.get(randomIdx).getTime());
            return IntersectionResponse.build(intersections.get(randomIdx).getDate(), times);
        }

        Date preDate = intersections.get(0).getDate();
        int preTime = intersections.get(0).getTime();
        int size = 0;
        int p = 0;
        times.add(intersections.get(p).getTime());

        for (int i = 1; i < intersections.size(); i++) {
            if (preTime + 1 == intersections.get(i).getTime() && preDate.equals(intersections.get(i).getDate())) {
                size++;
                times.add(intersections.get(i).getTime());
            } else {
                if (size + 1 >= minSize) {
                    date = intersections.get(p).getDate();
                    response = IntersectionResponse.build(date, times);
                }
                break;
            }
            //last index
            if (i == intersections.size() - 1 && size + 1 >= minSize) {
                date = intersections.get(p).getDate();
                response = IntersectionResponse.build(date, times);
            }
            preTime = intersections.get(i).getTime();
            preDate = intersections.get(i).getDate();
        }
        return response;
    }
}