package com.chilkens.timeset.controller;

import com.chilkens.timeset.dao.TimetableRepository;
import com.chilkens.timeset.dto.IntersectionResponse;
import com.chilkens.timeset.dto.StatusResponse;
import com.chilkens.timeset.service.IntersectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * Created by hoody on 2017-07-30.
 */

@Slf4j
@CrossOrigin(origins = "*")
@Api(value = "Intersection API", description = "공통 시간 찾기 API", basePath = "/api/v1/intersection")
@RestController
@RequestMapping("/api/v1/intersection")
public class IntersectionController {

    @Autowired
    IntersectionService intersectionService;

    @Autowired
    TimetableRepository timetableRepository;

    //차선책 중 한개만 선택
    @ApiOperation(value = "sub/find",
            notes = "교집합 찾는 API. 교집합이 있을 경우 교집합을 찾아서 넘겨주고 / 교집합이 없을 경우 차선책을 넘겨준다. 차선책도 없을 경우 null 반환 (date format : 'yyyy-MM-dd')")
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public Object find(@ApiParam("unique한 key값 입력") @RequestParam String keyUrl) {
        List<IntersectionResponse> l = intersectionService.getIntersection(keyUrl);

        int idx = (int)(long)timetableRepository.findTableIdByKeyUrl(keyUrl) % l.size();
        log.info("randomIdx : " + String.valueOf(idx));

        if(l.size() == 0) {
            return new StatusResponse().build("empty");
        }

        return l.get(idx);
    }
}
