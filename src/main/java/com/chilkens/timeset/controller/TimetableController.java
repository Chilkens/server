package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "OpenAndHistory API", description = "OpenAndHistory API", basePath = "/api/v1/test")
@RestController
@RequestMapping("/api/v1/openHistory")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @ApiOperation(value = "save", notes = "save Timetable")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ApiParam("keyUrl") @RequestParam String keyUrl, @ApiParam("title") @RequestParam String title,
                       @ApiParam("time") @RequestParam Integer time, @ApiParam("start") @RequestParam Date start,
                       @ApiParam("end") @RequestParam Date end, @ApiParam("max") @RequestParam Integer max,
                       @ApiParam("current") @RequestParam Integer current,
                       @ApiParam("createdBy") @RequestParam String createdBy) {
        try {
            timetableService.save(Timetable.build(keyUrl, title, time, start, end, max, current, createdBy));
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "findAll", notes = "find all Timetable")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<Timetable> findAll() {
        return timetableService.findAll();
    }

    @ApiOperation(value = "findByCreatedBy", notes = "find by createdBy Timetable")
    @RequestMapping(value = "findByCreatedBy/{createdBy}", method = RequestMethod.GET)
    public Timetable findByName(@PathVariable String createdBy) {
        return timetableService.findByCreatedBy(createdBy);
    }

}
