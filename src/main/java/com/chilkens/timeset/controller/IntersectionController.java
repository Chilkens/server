package com.chilkens.timeset.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hoody on 2017-07-30.
 */
@Api(value = "Intersection API", description = "공통 시간 찾기 API", basePath = "/api/v1/intersection")
@RestController
@RequestMapping("/api/v1/intersection")
public class IntersectionController {

    @ApiOperation(value = "findByKey", notes = "time table에 대한 unique key 값을 받아 해당하는 타임테이블의 시간 교집합을 return 해주는 api")
    @RequestMapping(method = RequestMethod.GET)
    public String findByKey(@PathVariable String key) {
        return key;
    }
}
