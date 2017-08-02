package com.chilkens.timeset.controller;

/**
 * Created by ByeongChan on 2017. 7. 21..
 */

import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.dto.PickRequest;
import com.chilkens.timeset.service.TimepickService;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "select/{keyUrl}", method = RequestMethod.GET)
    public Timetable findByKeyUrl(@PathVariable String keyUrl) {
        Timetable timetable = timetableService.findByKeyUrl(keyUrl);

        if(timetable == null){
            return null;
        }

        return timetable;
    }

    // 시간입력 POST
    @ApiOperation(value = "save", notes = "Save Time")
    @RequestMapping(value = "save/{keyUrl}", method = RequestMethod.POST)
    public String save(@PathVariable String keyUrl, @RequestBody PickRequest pickRequest) {

        try {
            Timetable table = timetableService.findByKeyUrl(keyUrl);

            if(table == null){
                // 예외처리
            }

            pickRequest.getPick().setTableId(table.getTableId());

            timepickService.savePick(pickRequest); // build?

            // PickDetail pickDetailTemp = pickDetail.get(0);
            // pickDetailTemp.setPickId(pickTemp.getPickId());

            // pickId를 알아와야한다.
            // Pick 전체 레코드 말고 ID만 리턴하는  쿼리를 작성못하나???
            // int pickId = timepickService.findLastId();

            /*
            for (int i = 0; i < pickDetail.size(); i++) {
                PickDetail pickDetailTemp = pickDetail.get(i);
                pickDetailTemp.setPickId(pickTemp.getPickId());

                timepickService.savePickDetail(pickDetailTemp);
            }
            */

            return "save success";
        } catch (Exception e) {
            e.printStackTrace();

            return "save fail";
        }
    }
}

