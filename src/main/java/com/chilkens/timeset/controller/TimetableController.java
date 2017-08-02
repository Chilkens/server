package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Api(value = "Timetable API", description = "Timetable API", basePath = "/api/v1/timetable")
@RestController
@RequestMapping("/api/v1/timetable")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @ApiOperation(value = "save", notes = "save Timetable")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody Timetable timetable) {
        try {
            timetable.setKeyUrl("TEMP KEY"); // random key 예정

            timetableService.save(timetable);
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
