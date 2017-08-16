package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickRepository;
import com.chilkens.timeset.domain.*;
import com.chilkens.timeset.dto.IntersectionResponse;
import com.chilkens.timeset.dto.SubIntersectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hoody on 2017-08-11.
 */
/*
    교집합이 없을 경우 차선책 찾기
 */
@Service
public class SubIntersectionService {
    @Autowired
    IntersectionService intersectionService;

    @Autowired
    TimetableService timetableService;

    @Autowired
    PickRepository pickRepository;

    //팀원 과반수 이상 인원 중 가장 많은 인원이 모일 수 있는 모임 시간 찾기
    public List<SubIntersectionResponse> getSubIntersection(String keyUrl) {
        Timetable table = timetableService.findByKeyUrl(keyUrl);
        List<Long>[][] m = getDateMatrix(table);
        List<Long> pickIds;
        List<SubIntersectionResponse> subIntersections = new ArrayList<>(); // 차선책 result

        List<String> possible, impossible; // 해당시간에 모임에 참석 가능/불가능 한 사람 name list
        List<IntersectionResponse> intersections = new ArrayList<>(); // 교집합 날짜, 시간 정보

        int min = (table.getMax() % 2 == 0) ? table.getMax() / 2 : table.getMax() / 2+1; // 교집합 찾을 때 넘어야 하는 최소 인원 (과반수 이상)
        int i, j;
        int size; // 연속된 시간 size count
        int p; // 연속된 시간 시작 인덱스 가리키는 역할
        int max = 0; //해당 시간에 모일 수 있는 최대 멤버 수
        Calendar cal;

        for (i = 0; i < m.length; i++) {
            pickIds = new ArrayList<>();
            size = 0;
            p = 0;
            for (j = 0; j < m[i].length; j++) {
                // last column index check and null check
                // 열의 마지막 인덱스가 아니면서 해당 시간을 선택한 사람이 과반수 이상일 때만 계속 진행
                if (j != m[i].length - 1 && (m[i][j] == null || m[i][j].size() < min)) continue;

                if (pickIds.size() == 0) {
                    pickIds = m[i][j];
                    p = j;
                }

                if (m[i][j] != null && m[i][j].equals(pickIds)) {
                    size++;
                } else {
                    // 최소 모임시간보다 같거나 큰 시간 && 가능 멤버수가 가장 많은 경우 저장함,
                    if (size >= table.getTime() && pickIds.size() >= max) {
                        if(pickIds.size() > max)
                            subIntersections.clear();

                        max = pickIds.size();
                        possible = new ArrayList<>();
                        impossible = new ArrayList<>();
                        intersections = new ArrayList<>();

                        // input possible
                        for (Long id : pickIds) {
                            possible.add(pickRepository.getOne(id).getCreatedBy());
                        }
                        // input impossible
                        impossible.addAll(getImpossibleList(table.getTableId(), possible));
                        // input DateInfo
                        // matrix를 해당 날짜, 시간으로 변경해서 add
                        cal = new GregorianCalendar();
                        cal.setTime(table.getStart());
                        cal.add(Calendar.DATE, i);
                        List<Integer> time = new ArrayList<>();
                        for (int k = p; k < p + size; k++) {
                            time.add(6 + k);
                        }
                        intersections.add(IntersectionResponse.build(cal.getTime(), time));

                        subIntersections.add(SubIntersectionResponse.build(possible, impossible, intersections));
                    }
                    size = 1;
                    p = j;
                    pickIds = m[i][j];
                }
            }
        }
        return subIntersections;
    }

    // 전체 팀원중에서 모임시간에 가능한 팀원들 뺀 나머지 불가능한 팀원 List 형태로 만들어서 return
    List<String> getImpossibleList(Long tableId, List<String> possibleList) {
        List<String> l = pickRepository.findNameByTableId(tableId);
        l.removeAll(possibleList);
        return l;
    }

    // 해당 날짜, 시간에 가능한 사람을 pickId 리스트 형태로 Matrix에 추가
    private List<Long>[][] getDateMatrix(Timetable table) {
        Date startDate = table.getStart();
        Date endDate = table.getEnd();
        int dayMs = (24 * 60 * 60 * 1000);
        int dif = (int) ((endDate.getTime() - startDate.getTime()) / dayMs + 1);
        int i, j;

        // 날짜, 시간 matrix
        // 하루에 가능하다고 체크할 수 있는 시간 6~23시까지 최대 18
        List<Long>[][] m = new ArrayList[dif][18];

        List<PickInfo> picks = intersectionService.getPickInfo(table.getTableId());

        for (PickInfo pick : picks) {
            for (DateInfo date : pick.getDateInfos()) {
                i = (int) ((date.getDate().getTime() - startDate.getTime()) / dayMs); // 행(날짜)
                j = date.getTime() - 6; // 열(시간)

                if (m[i][j] == null) {
                    m[i][j] = new ArrayList<>();
                }

                m[i][j].add(pick.getPickId());
            }
        }
        return m;
    }

    // 원래의 모임시간보다 1시간 적게 모인다고 가정했을 때 가능한 교집합 찾기
    // 일단은 기획에서 이 기능 빠졌음.
    //public void getSubIntersectionByTime(String keyUrl) {
    //}

}
