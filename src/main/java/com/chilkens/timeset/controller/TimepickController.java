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
    public Pick save(@PathVariable String keyUrl, @RequestBody Pick pick) {
        //, @RequestBody List<PickDetail> pickDetail 를 추가하면 에러발생
        // Pick이랑 PickDetail을 합쳐서 Request 파라미터로 받고
        // 서비스에 받는 방법

        // 트랜잭션 삽입 ?
        try {
            Timetable table = timetableService.findByKeyUrl(keyUrl);

            if(table == null){
                // 예외처리
            }

            pick.setTableId(table.getTableId());

            Pick pickTemp = timepickService.savePick(pick); // build?

            // PickDetail pickDetailTemp = pickDetail.get(0);
            // pickDetailTemp.setPickId(pickTemp.getPickId());

            return pickTemp;
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

            // return "save success";
        } catch (Exception e) {
            e.printStackTrace();

            // return "save fail";
            return null;
        }
    }
}

