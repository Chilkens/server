package com.chilkens.timeset.controller;

import com.chilkens.timeset.dto.HistoryResponse;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "HistoryProgResponse API", description = "시간 선택 진행중인 타임테이블 찾기 API", basePath = "/api/v1/historyProg")
@RestController
@RequestMapping("/api/v1/historyProg")
public class HistoryProgController {
    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "findProgTimetable", notes = "시간 선택을 아직 진행중인 타임테이블을 반환해주는 API 입니다. 프런트에서 오는 kakaoId와 Timetable에 createdBy와 비교하여 같은 모든 테이블 중, 시간 선택이 진행중인 타임테이블만 반환됩니다.")
    @RequestMapping(method = RequestMethod.GET)
    public List<HistoryResponse> findById(@RequestParam String kakaoId) {
        List<HistoryResponse> historyProg = new ArrayList<>();

        List<Timetable> progTimetable = historyService.findProgInTimetable(kakaoId);
        List<String> pickName = new ArrayList<>();

        return historyService.findHistory(progTimetable, pickName, historyProg);
    }
}