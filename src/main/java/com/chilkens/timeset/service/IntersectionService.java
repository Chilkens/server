package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickJoinRepository;
import com.chilkens.timeset.dao.TimetableRepository;
import com.chilkens.timeset.domain.*;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hoody on 2017-08-02.
 */
@Service
public class IntersectionService {
    @Autowired
    PickJoinRepository pickJoinRepository;

    @Autowired
    TimetableRepository timetableRepository;

    //pick, pick_detail 테이블에서 Join된 데이터를 계산에 필요한 데이터로 바꿔줌.
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

//    해당하는 테이블의 모든 시간 교집합 찾아서 return
//    Guava 교집합 method 이용
//    TODO
    public List<DateInfo> getAllIntersection(Long tableId) {
        List<PickInfo> picks = getPickInfo(tableId);
        List<DateInfo> intersections;
        Set<DateInfo> preSet = new HashSet<>(); //intersectionSet
        Set<DateInfo> curSet;
        int i = 0;

        //첫번째 시간 정보 preSet에 저장
        for(DateInfo dateInfo : picks.get(0).getDateInfos()) {
            preSet.add(dateInfo);
        }

        for(PickInfo pick : picks) { //picks.subList(1, picks.size())
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

//   해당하는 타임테이블의 모임 시간에 맞춘 시간 교집합 찾아서 return
    public List<DateInfo> getIntersection(Long tableId, int minSize) {
        List<DateInfo> intersections = getAllIntersection(tableId);
        List<DateInfo> intersectionsByTime = new ArrayList<>();

        int preTime = intersections.get(0).getTime();
        int size = 0;
        int p = 0;

        // 맨 처음에 최소시간 1이면 바로 return
        if(minSize == 1) {
            return intersections;
        }

        for(int i=1; i<intersections.size(); i++) {
            if(preTime+1 == intersections.get(i).getTime()) {
                size++;
            }
            else {
                if(size+1 >= minSize) {
                    intersectionsByTime.addAll(intersections.subList(p, p+size+1));
                }
                size = 0;
                p = i;
            }
            //last index
            if(i == intersections.size()-1 && size+1 >= minSize) {
                intersectionsByTime.addAll(intersections.subList(p, p+size+1));
            }
            preTime = intersections.get(i).getTime();
        }
        return intersectionsByTime;
    }

//    public List<PickInfo> getPickInfo(Long tableId) {
//        List<PickInfo> pickInfoList = new ArrayList<>();
//        List<Long> dateInfoList;
//
//        List<PickJoin> picks = pickJoinRepository.findByTableId(tableId);
//
//        for(PickJoin pick : picks) {
//            dateInfoList = new ArrayList<>();
//            for(PickDetail detail : pick.getPickDetailList()) {
//                //time split
//                for(String time : Arrays.asList(detail.getPickTime().split("\\|"))) {
//                    DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
//                    Date transDate = detail.getPickDate();
//                    String tempDate = sdFormat.format(transDate);
//
//                    String tempDateInfo = tempDate + time;
//
//                    dateInfoList.add(Long.parseLong(tempDateInfo));
//                }
//            }
//            pickInfoList.add(PickInfo.build(pick.getPickId(), dateInfoList));
//        }
//
//        return pickInfoList;
//    }
//
//    public List<ResultPick> getAlternative(Long tableId){
//        List<PickInfo> pickInfoList = getPickInfo(tableId);
//        Timetable timetable = timetableRepository.findByTableId(tableId);
//
//        // 날짜 차이 계산
//        long calDate = timetable.getEnd().getTime() - timetable.getStart().getTime();
//        // Date.getTime()은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다.
//        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
//        long calDateDays = calDate / (24*60*60*1000);
//        calDateDays = Math.abs(calDateDays);
//
//        // int lineSize = (int)((calDateDays + 1) * 18);
//        int lineSize = (int)((calDateDays + 1) * 10);
//        int rowSize = pickInfoList.size();
//
//        boolean[][] matrix = new boolean[lineSize][rowSize];
//
//        // Timetable 범위 안에 있는 날짜 시간들을 yyyyMMdd + Time (Long) 타입으로 변환해주기 위한 과정
//        Long[] tableDateList = new Long[lineSize];
//
//        DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
//        Date transDate = timetable.getStart();
//        // Date --> String
//        String tempDate = sdFormat.format(transDate);;
//
//        int time = 0;
//        for (int i = 0; i < tableDateList.length; i++) {
//
//            if(time == 10){
//                time = 0;
//
//                // 날짜 하루 증가 시켜주기 위해
//                // String --> Long
//                Long tempLongDate = Long.parseLong(tempDate);
//                tempLongDate++;
//
//                // 다시 문자열로 변환
//                // Long --> String
//                // transDate = new Date(tempLongDate);
//                tempDate = tempLongDate.toString();
//            }
//
//            time++;
//
//            // tempDate = sdFormat.format(transDate);
//            // String + Integer
//            String tempDateInfo = tempDate + time;
//            tableDateList[i] = Long.parseLong(tempDateInfo);
//        }
//
//        // 모든 범위와 사용자가 픽한 시간 비교
//        for (int i = 0; i < pickInfoList.size(); i++) {
//            // 사용자가 픽한 시간을 순차적으로 가져온다.
//            for(Long dateInfo : pickInfoList.get(i).getDateInfoList()){
//                // 가져온 시간을 비교
//                for (int j = 0; j < tableDateList.length; j++) {
//                    if(tableDateList[j].equals(dateInfo)){
//                        matrix[j][i] = true;
//                    }
//                }
//            }
//        }
//
//        // true 개수 count
//        ArrayList<CountInfo> countInfo = new ArrayList<>();
//        int cnt = 0;
//        ArrayList<Long> pickIdList;
//
//        // int[] countList = new int[lineSize];
//
//        for (int i = 0; i < matrix.length; i++) {
//            cnt = 0;
//            pickIdList = new ArrayList<>();
//            for (int j = 0; j < matrix[i].length; j++) {
//                if(matrix[i][j]){
//                    cnt++;
//                    pickIdList.add(pickInfoList.get(j).getPickId());
//                }
//            }
//            // countList[i] = count;
//            countInfo.add(new CountInfo(cnt, pickIdList));
//        }
//
//        ArrayList<ResultPick> resultDate = new ArrayList<>();
//        // 과반수 이상 선택한 시간을 탐색
//
//        int timeSize = 0;
//
//
//        for (int i = 0; i < countInfo.size(); i++) {
//            if(countInfo.get(i).getCount() >= pickInfoList.size() / 2){
//                // minSize 이상이 되어야 하고 또한 전에 선택한 사용자들과 똑같은 사람들인지 확인해야한다.
//
//                resultDate.add(new ResultPick(countInfo.get(i).getPickId(), tableDateList[i]));
//                // resultTime.add(tableDateList[i]);
//            }
//        }
//
//        return resultDate;
//    }
//
//    public List<ResultPick> getAlterFinal(Long tableId){
//        ArrayList<ResultPick> initResultPick = new ArrayList<>(getAlternative(tableId));
//        // minSize 이상이 되어야 하고 전에 선택한 사용자들과 똑같은 사람들인지 확인해야함
//
//        return null;
//    }
}
