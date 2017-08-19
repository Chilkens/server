package com.chilkens.timeset.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hoody on 2017-07-16.
 */

@CrossOrigin(origins = "*")
@RestController
public class IndexController {
    @RequestMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Chilkens' Server");
    }

    @RequestMapping("/chilkens")
    public void index(HttpServletResponse response) {
        try {
            response.sendRedirect("/swagger-ui.html"); //api로 redirect
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
