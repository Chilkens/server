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
    @ApiOperation(value = "save", notes = "사용자가 입력한 시간을 저장하는 API \n(회원가입을 안하는데 나중에 수정 등등 할 일 생길수도있어서 일단 저장성공하면 pickId(pick 한 사람마다 생기는 PK) 리턴하게 해두었습니다)")
    @RequestMapping(value = "save/{keyUrl}", method = RequestMethod.POST)
    public Long save(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl,
                       @RequestBody PickRequest pickRequest) {
        try {
            Timetable table = timetableService.findByKeyUrl(keyUrl);

            if(table == null){
                // 예외처리
            }

            pickRequest.getPick().setTableId(table.getTableId());

            return timepickService.savePick(pickRequest).getPickId(); //save and return pk

            // return pickRequest.getPick().getPickId(); // 사용자명 돌려줄 경우
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

