package com.chilkens.timeset.controller;

import com.chilkens.timeset.domain.Test;
import com.chilkens.timeset.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "test API", description = "test API", basePath = "/api/v1/test")
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    TestService testService;

    @ApiOperation(value = "save", notes = "save test")
    @RequestMapping(value = "save/{name}/{email}", method = RequestMethod.POST)
    public String save(@PathVariable String name, @PathVariable String email) {
        try {
            testService.save(Test.build(name, email));
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }

    @ApiOperation(value = "findAll", notes = "find all test")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<Test> findAll() {
        return testService.findAll();
    }

    @ApiOperation(value = "findByName", notes = "find by name test")
    @RequestMapping(value = "findByName/{name}", method = RequestMethod.GET)
    public Test findByName(@PathVariable String name) {
        return testService.findByName(name);
    }

}
