package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.DateInfo;
import com.chilkens.timeset.domain.PickDetail;
import com.chilkens.timeset.domain.PickInfo;
import com.chilkens.timeset.domain.PickJoin;
import com.chilkens.timeset.service.IntersectionService;
import com.chilkens.timeset.service.TimepickService;
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

    @ApiOperation(value = "findByKey", notes = "time table에 대한 unique key 값을 받아 해당하는 타임테이블의 시간 교집합을 return 해주는 API")
    @RequestMapping(value = "{keyUrl}", method = RequestMethod.GET)
    public List<DateInfo> findByKey(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl) {
        Long tableId = timetableService.findByKeyUrl(keyUrl).getTableId();

        return intersectionService.getIntersection(tableId);
    }
}
