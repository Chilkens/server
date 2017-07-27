package com.chilkens.timeset.controller;

/**
 * Created by ByeongChan on 2017. 7. 21..
 */

import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.TimepickService;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "timepick API", description = "timepick API", basePath = "/api/v1/timepick")
@RestController
@RequestMapping("/api/v1/timepick")
public class TimepickController {

    @Autowired
    TimepickService timepickService;

    @Autowired
    TimetableService timetableService;

    // 시간입력 GET
    @ApiOperation(value = "findByKey", notes = "Find Timetable Information By Key")
    @RequestMapping(value = "select/{key}", method = RequestMethod.GET)
    public Timetable findByKey(@PathVariable String key) {
        Timetable timetable = timetableService.findByKey(key);

        if(timetable == null){
            return null;
        }

        return timetable;
    }

    // 시간입력 POST
    @ApiOperation(value = "save", notes = "Save Time")
    @RequestMapping(value = "save/{key}", method = RequestMethod.POST)
    public String save(@PathVariable String key, @RequestBody Pick pick, @RequestBody List<PickDetail> pickDetail) {

        // 트랜잭션 삽입 ?
        try {
            Timetable table = timetableService.findByKey(key);
            pick.setTableId(table.getTableId());
            
            timepickService.savePickInfo(pick); // build?

            for (int i = 0; i < pickDetail.size(); i++) {
                timepickService.savePickDetail(pickDetail.get(i));
            }

            return "save success";
        } catch (Exception e) {
            e.printStackTrace();

            return "save fail";
        }
    }
}

