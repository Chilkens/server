package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.TimeTable;
import com.chilkens.timeset.service.HistoryService;
import com.chilkens.timeset.service.OpenTableService;
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
public class TimeTableController {
    @Autowired
    OpenTableService openTableService;
    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "save", notes = "save TimeTable")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ApiParam("key") @PathVariable String key, @ApiParam("key") @PathVariable String title, @ApiParam("key") @PathVariable Integer time, @ApiParam("key") @PathVariable Date start, @ApiParam("key") @PathVariable Date end, @ApiParam("key") @PathVariable Integer max, @ApiParam("key") @PathVariable Integer current, @ApiParam("key") @PathVariable Timestamp createdAt, @ApiParam("key") @PathVariable String createdBy, @ApiParam("key") @PathVariable Short deleated) {
        try {
            openTableService.save(TimeTable.build(key, title, time, start, end, max, current, createdAt, createdBy, deleated));
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "findAll", notes = "find all TimeTable")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<TimeTable> findAll() {
        return historyService.findAll();
    }

    @ApiOperation(value = "findByCreatedBy", notes = "find by createdBy TimeTable")
    @RequestMapping(value = "findByCreatedBy/{createdBy}", method = RequestMethod.GET)
    public TimeTable findByName(@PathVariable String createdBy) {
        return historyService.findByCreatedBy(createdBy);
    }

}
