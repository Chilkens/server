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
@Api(value = "HistoryFinResponse API", description = "시간 선택 완료된 타임테이블 찾기 API", basePath = "/api/v1/historyFin")
@RestController
@RequestMapping("/api/v1/historyFin")
public class HistoryFinController {
    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "findFinTimetable", notes = "모든사람이 시간 선택을 완료한 타임테이블을 반환해주는 API 입니다. 프런트에서 오는 kakaoId와 Timetable에 createdBy와 비교하여 같은 모든 테이블 중, 시간 선택이 완료된 타임테이블만 반환됩니다.")
    @RequestMapping(method = RequestMethod.GET)
    public List<HistoryResponse> findById(@RequestParam String kakaoId) {
        List<HistoryResponse> historyFin = new ArrayList<>();

        List<Timetable> finTimetable = historyService.findFinInTimetable(kakaoId);
        List<String> pickName = new ArrayList<>();

        return historyService.findHistory(finTimetable, pickName, historyFin);
    }
}
