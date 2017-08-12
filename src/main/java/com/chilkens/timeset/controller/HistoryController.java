package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.HistoryResponse;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "HistoryResponse API", description = "마이페이지 API", basePath = "/api/v1/history")
@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "findInPickAndTimetable", notes = "Pick과 Timetable에서 마이페이지에 관련된 정보들을 넘겨주는 api")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<HistoryResponse> findById(@RequestParam String kakaoId) {
        List<HistoryResponse> historyList = new ArrayList<>();
        List<Timetable> timetable = historyService.findInTimetable(kakaoId);
        List<String> pickName = new ArrayList<>();

        for(int i=0; i<timetable.size(); i++){
            Long tableId = timetable.get(i).getTableId();
            List<Pick> pick = historyService.findInPick(tableId);
            for(int j=0; j<pick.size(); j++){
                String names = pick.get(j).getCreatedBy();
                pickName.add(names);
            }
            historyList.add(HistoryResponse.build(timetable.get(i), pickName));
        }

        return historyList;
    }
}
