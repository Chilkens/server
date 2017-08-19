package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.HistoryResponse;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "HistoryResponse API", description = "마이페이지 API", basePath = "/api/v1/history")
@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    /*
    @ApiOperation(value = "findInPickAndTimetable", notes = "Pick과 Timetable에서 마이페이지에 관련된 정보들을 넘겨주는 api")
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody void findById(@RequestParam String kakaoId, Model model) {
        List<HistoryResponse> historyFin = new ArrayList<>();
        List<HistoryResponse> historyProg = new ArrayList<>();

        List<Timetable> finTimetable = historyService.findFinInTimetable(kakaoId);
        List<Timetable> progTimetable = historyService.findProgInTimetable(kakaoId);
        List<String> pickName = new ArrayList<>();

        //if(historyList.size() >= )

        for(int i=0; i<finTimetable.size(); i++){
            Long tableId = finTimetable.get(i).getTableId();
            List<Pick> pick = historyService.findInPick(tableId);
            for(int j=0; j<pick.size(); j++){
                String names = pick.get(j).getCreatedBy();
                pickName.add(names);
            }
            historyFin.add(HistoryResponse.build(finTimetable.get(i), pickName));
            model.addAttribute("finTimetable", historyFin);
            System.out.println(finTimetable.get(i).getTableId());

        }

        for(int i=0; i<progTimetable.size(); i++) {
            Long tableId = progTimetable.get(i).getTableId();
            List<Pick> pick = historyService.findInPick(tableId);
            for (int j = 0; j < pick.size(); j++) {
                String names = pick.get(j).getCreatedBy();
                pickName.add(names);
            }
            historyProg.add(HistoryResponse.build(progTimetable.get(i), pickName));
            model.addAttribute("progressTimetable", historyProg);
            System.out.println(progTimetable.get(i).getTableId());
        }
    }
    */

    @ApiOperation(value = "Timatable History Return", notes = "Pick과 Timetable에서 마이페이지에 관련된 정보들을 넘겨주는 api")
    @RequestMapping(method = RequestMethod.GET)
    public List<HistoryResponse> findById(@RequestParam String name){

        List<HistoryResponse> historyTimetable = historyService.findHistory(name);

        return historyTimetable;
    }
}
