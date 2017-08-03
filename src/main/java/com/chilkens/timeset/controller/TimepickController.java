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
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Timepick API", description = "시간 선택 API", basePath = "/api/v1/timepick")
@RestController
@RequestMapping("/api/v1/timepick")
public class TimepickController {

    @Autowired
    TimepickService timepickService;

    @Autowired
    TimetableService timetableService;

    // Timetable 정보를 Timetable API에서 불러올껀지 Timepick API에서 불러올껀지
    /*
    @ApiOperation(value = "findByKey", notes = "Find Timetable Information By Key")
    @RequestMapping(value = "select/{keyUrl}", method = RequestMethod.GET)
    public Timetable findByKeyUrl(@PathVariable String keyUrl) {
        Timetable timetable = timetableService.findByKeyUrl(keyUrl);

        if(timetable == null){
            return null;
        }

        return timetable;
    }
    */

    // 시간입력 POST
    @ApiOperation(value = "save", notes = "사용자가 입력한 시간을 저장하는 API")
    @RequestMapping(value = "save/{keyUrl}", method = RequestMethod.POST)
    public String save(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl,
                       @RequestBody PickRequest pickRequest) {

        try {
            Timetable table = timetableService.findByKeyUrl(keyUrl);

            if(table == null){
                // 예외처리
            }

            pickRequest.getPick().setTableId(table.getTableId());

            timepickService.savePick(pickRequest);

            return "save success"; // 리턴 값 수정해줘야 함. 어떤 걸로?
            // return pickRequest.getPick().getPickId(); // 사용자명 돌려줄 경우
        } catch (Exception e) {
            e.printStackTrace();

            return "save fail";
        }
    }
}

