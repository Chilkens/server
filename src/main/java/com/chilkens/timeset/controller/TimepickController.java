package com.chilkens.timeset.controller;

/**
 * Created by ByeongChan on 2017. 7. 21..
 */

import com.chilkens.timeset.common.NotFoundException;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.dto.PickRequest;
import com.chilkens.timeset.dto.PickResponse;
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
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public PickResponse save(@ApiParam("unique한 key값 입력") @RequestParam String keyUrl,
                       @RequestBody PickRequest pickRequest) throws Exception {
        /**************SAMPLE*********************
         {
             "pick": {
                "createdBy": "itenerr"
             },
             "pickDetailList": {
                 "2017-08-03": [15, 16, 17],
                 "2017-08-04": [15, 16, 17]
             }
         }
         ******************************************/

        Timetable table = timetableService.findByKeyUrl(keyUrl);

        if(table == null) {
            throw new NotFoundException();
        }

        if(table.getMax().equals(table.getCurrent())){
            return PickResponse.build(new Long(-1));
        } else {
            pickRequest.getPick().setTableId(table.getTableId());

            Pick resultPick = timepickService.savePick(pickRequest);

            return PickResponse.build(resultPick.getPickId());
        }
    }
}

