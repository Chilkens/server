package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.HistoryService;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Timetable API", description = "방 관련 API", basePath = "/api/v1/timetable")
@RestController
@RequestMapping("/api/v1/timetable")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @ApiOperation(value = "save", notes = "새로운 약속시간 방 개설 API")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody Timetable timetable) {
        try {
            timetable.setKeyUrl("TEMP KEY"); // random key 예정

            timetableService.save(timetable);

            return "save success"; // 리턴 값 수정해줘야 함. 방 URL 돌려줘야 함.
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "findByKeyUrl", notes = "KEY URL을 이용해 해당 Timetable 방 정보를 가져오는 API")
    @RequestMapping(value = "/select/{keyUrl}", method = RequestMethod.GET)
    public Timetable findByKeyUrl(@PathVariable String keyUrl) {
        Timetable timetable = timetableService.findByKeyUrl(keyUrl);

        if(timetable == null){
            return null;
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
