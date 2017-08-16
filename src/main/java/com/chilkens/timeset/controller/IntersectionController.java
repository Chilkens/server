package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.DateInfo;
import com.chilkens.timeset.domain.Timetable;
import com.chilkens.timeset.dto.IntersectionResponse;
import com.chilkens.timeset.dto.SubIntersectionResponse;
import com.chilkens.timeset.service.IntersectionService;
import com.chilkens.timeset.service.SubIntersectionService;
import com.chilkens.timeset.service.TimetableService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hoody on 2017-07-30.
 */

@CrossOrigin(origins = "*")
@Api(value = "Intersection API", description = "공통 시간 찾기 API", basePath = "/api/v1/intersection")
@RestController
@RequestMapping("/api/v1/intersection")
public class IntersectionController {
    @Autowired
    TimetableService timetableService;

    @Autowired
    IntersectionService intersectionService;

    @Autowired
    SubIntersectionService subIntersectionService;

    @ApiOperation(value = "find", notes = "해당하는 타임테이블의 모임시간보다 큰 시간 교집합 1개를 return 해주는 api (date는 yyyy-MM-dd format으로 리턴됩니다)")
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public IntersectionResponse find(@ApiParam("unique한 key값 입력") @RequestParam String key) {
        Timetable table = timetableService.findByKeyUrl(key);
        List<IntersectionResponse> l = intersectionService.getIntersection(table.getTableId(), table.getTime());
        return l.get(new Random().nextInt(l.size()));
    }

    @ApiOperation(value = "findAll", notes = "해당 time table의 모든 시간 교집합을 return 해주는 api")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<DateInfo> findAll(@ApiParam("unique한 key값 입력") @RequestParam String key) {
        return intersectionService.getAllIntersection(
                timetableService.findByKeyUrl(key).getTableId()
        );
    }

    @ApiOperation(value = "sub/find",
            notes = "차선책 1개를 리턴해주는 API (전체 팀원의 교집합이 없을 경우 팀원 중 과반수 이상이 모일 수 있는 교집합이 있다면 찾아주고" +
                    " 그 때 누가 시간이 되는지 안되는지 알려주는 API)")
    @RequestMapping(value = "sub/find", method = RequestMethod.GET)
    public SubIntersectionResponse subIntersection(@ApiParam("unique한 key값 입력") @RequestParam String key) {
        //차선책 중 한개만 선택
        List<SubIntersectionResponse> l = subIntersectionService.getSubIntersection(key);
        return l.get(new Random().nextInt(l.size()));
    }
    
//    현재 이 기능 생략
//    @ApiOperation(value = "sub/findByTime",
//            notes = "교집합이 없을 경우 모임시간이 2시간 이상일 때 모임시간-1 한 교집합이 있으면 찾아주는 API (return date format : yyyy-MM-dd)")
//    @RequestMapping(value = "sub/findByTime/{keyUrl}", method = RequestMethod.GET)
//    public List<DateInfo> subIntersectionFindByTime(@ApiParam("unique한 key값 입력") @PathVariable String keyUrl) {
//
//        return null;
//    }
}
