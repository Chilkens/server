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

import java.sql.Date;
import java.util.List;

@Api(value = "OpenAndHistory API", description = "OpenAndHistory API", basePath = "/api/v1/test")
@RestController
@RequestMapping("/api/v1/openHistory")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "save", notes = "save Timetable")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@PathVariable String title, @PathVariable Integer time, @PathVariable Date start, @PathVariable Date end, @PathVariable Integer max, @PathVariable Integer current, @PathVariable String createdBy) {
        try {
            timetableService.save(Timetable.build(title, time, start, end, max, current, createdBy));
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "findByTableId", notes = "find by tableId Timetable")
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
    }
}
