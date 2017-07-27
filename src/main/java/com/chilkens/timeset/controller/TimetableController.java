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

@Api(value = "OpenAndHistory API", description = "OpenAndHistory API", basePath = "/api/v1/test")
@RestController
@RequestMapping("/api/v1/openHistory")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @ApiOperation(value = "save", notes = "save Timetable")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ApiParam("key") @PathVariable String key, @ApiParam("key") @PathVariable String title, @ApiParam("key") @PathVariable Integer time, @ApiParam("key") @PathVariable Date start, @ApiParam("key") @PathVariable Date end, @ApiParam("key") @PathVariable Integer max, @ApiParam("key") @PathVariable Integer current, @ApiParam("key") @PathVariable Timestamp createdAt, @ApiParam("key") @PathVariable String createdBy, @ApiParam("key") @PathVariable Short deleated) {
        try {
            timetableService.save(Timetable.build(key, title, time, start, end, max, current, createdAt, createdBy, deleated));
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
