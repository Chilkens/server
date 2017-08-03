package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.HistoryService;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Timetable API", description = "새로운 약속 시간 생성하기 api", basePath = "/api/v1/timetable")
@RestController
@RequestMapping("/api/v1/timetable")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @ApiOperation(value = "save", notes = "약속시간 생성을 위해 필요한 모든 정보를 받아서 데이터베이스에 저장해주는 api")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody Timetable timetable) {
        try {
            timetable.setKeyUrl("nooooooo"); // random key 예정

            timetableService.save(timetable);
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    /*@ApiOperation(value = "findByTableId", notes = "find by tableId Timetable")
    @RequestMapping(value = "/findByTableId", method = RequestMethod.GET)
    public @ResponseBody List<Timetable> findByTableId(@RequestBody Long tableId, Model model) {
        List<Timetable> timetable = HistoryService.findByTableId(tableId);
        model.addAttribute("timetable", timetable);

        return null;
    }

    @ApiOperation(value = "findById", notes = "find by pickId Pick")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public @ResponseBody List<Pick> findById(@RequestBody Long pickId, Model model) {
        List<Pick> pick = HistoryService.findById(pickId);
        model.addAttribute("pickId",pickId);

        return null;
    }*/
}
