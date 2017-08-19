package com.chilkens.timeset.controller;

/**
 * Created by ByeongChan on 2017. 7. 21..
 */

import com.chilkens.timeset.common.NotFoundException;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.TimepickService;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(value = "Timepick API", description = "시간 선택 API", basePath = "/api/v1/timepick")
@RestController
@RequestMapping("/api/v1/timepick")
public class TimepickController {

    @Autowired
    TimepickService timepickService;

    @Autowired
    TimetableService timetableService;

    // 시간입력 POST
    @ApiOperation(value = "save", notes = "사용자가 입력한 시간을 저장하는 API \n(회원가입을 안하는데 나중에 수정 등등 할 일 생길수도있어서 일단 저장성공하면 pickId(pick 한 사람마다 생기는 PK) 리턴하게 해두었습니다)")
    @RequestMapping(value = "save/{keyUrl}", method = RequestMethod.POST)
    /*
    public Long save(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl,
                       @RequestBody PickRequest pickRequest) throws Exception {
    */
    public Long save(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl,
                     @RequestBody Pick pick, @RequestParam String pickDetailList) throws Exception {

        Timetable table = timetableService.findByKeyUrl(keyUrl);

        if (table == null) {
            throw new NotFoundException();
        }

        /**************SAMPLE*********************
        String pickDetailList = "{" +
                "\"2017-08-16\":[15, 16, 17]," +
                "\"2017-08-17\":[15, 16, 17]" + "}";
        ******************************************/

        /* RequestBody로 한번에 받는 방법
        pickRequest.getPick().setTableId(table.getTableId());

        Pick resultPick = timepickService.savePick(pickRequest);
        */

        pick.setTableId(table.getTableId());

        Pick resultPick = timepickService.savePick(pick, pickDetailList);

        return resultPick.getPickId();
    }
}

