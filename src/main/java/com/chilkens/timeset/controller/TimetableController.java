package com.chilkens.timeset.controller;

import com.chilkens.timeset.common.NotFoundException;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Timetable API", description = "방 관련 API", basePath = "/api/v1/timetable")
@RestController
@RequestMapping("/api/v1/timetable")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @ApiOperation(value = "save", notes = "새로운 약속시간 방 개설 API")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ApiParam("current는 현재 시간 입력한 사람 수(초기값 : 0)") @RequestBody Timetable timetable) {
        Timetable t = Timetable.build(timetable);
        timetableService.save(t);

        return t.getKeyUrl();
    }

    @ApiOperation(value = "findByKeyUrl", notes = "KEY URL을 이용해 해당 Timetable 방 정보를 가져오는 API")
    @RequestMapping(value = "/select/{keyUrl}", method = RequestMethod.GET)
    public Timetable findByKeyUrl(@PathVariable String keyUrl) {
        Timetable timetable = timetableService.findByKeyUrl(keyUrl);

        if(timetable == null){
            throw new NotFoundException();
        }

        return timetable;
    }

    /*
    @ApiOperation(value = "findById", notes = "find by pickId Pick")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public @ResponseBody List<Pick> findById(@RequestBody Long pickId, Model model) {
        List<Pick> pick = HistoryService.findById(pickId);
        model.addAttribute("pickId",pickId);

        return null;
    }*/
}
