package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
