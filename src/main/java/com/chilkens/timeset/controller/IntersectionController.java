package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.DateInfo;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.service.IntersectionService;
import com.chilkens.timeset.service.TimetableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hoody on 2017-07-30.
 */
@Api(value = "Intersection API", description = "공통 시간 찾기 API", basePath = "/api/v1/intersection")
@RestController
@RequestMapping("/api/v1/intersection")
public class IntersectionController {
    @Autowired
    TimetableService timetableService;

    @Autowired
    IntersectionService intersectionService;

    @ApiOperation(value = "find", notes = "해당하는 타임테이블의 모임시간보다 큰 시간 교집합을 return 해주는 api (date는 yyyy-MM-dd format으로 리턴됩니다)")
    @RequestMapping(value = "find/{keyUrl}", method = RequestMethod.GET)
    public List<DateInfo> find(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl) {

        Timetable table = timetableService.findByKeyUrl(keyUrl);

        return intersectionService.getIntersection(table.getTableId(), table.getTime());
    }

    @ApiOperation(value = "findAll", notes = "해당 time table의 모든 시간 교집합을 return 해주는 api (date는 yyyy-MM-dd format으로 리턴됩니다)")
    @RequestMapping(value = "findAll/{keyUrl}", method = RequestMethod.GET)
    public List<DateInfo> findAll(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl) {

        return intersectionService.getAllIntersection(
                timetableService.findByKeyUrl(keyUrl).getTableId()
        );
    }

//    @ApiOperation(value = "AlterTest", notes = "test")
//    @RequestMapping(value = "test/{keyUrl}", method = RequestMethod.GET)
//    public List<ResultPick> findAlter(@ApiParam("keyUrl") @PathVariable String keyUrl) {
//        Timetable table = timetableService.findByKeyUrl(keyUrl);
//        return intersectionService.getAlternative(table.getTableId());
//        // return intersectionService.getPickInfo(table.getTableId());
//    }
}
