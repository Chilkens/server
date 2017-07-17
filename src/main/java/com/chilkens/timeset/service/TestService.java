package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.TestRepository;
import com.chilkens.timeset.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hoody on 2017-07-16.
 */

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public void save(Test test) {
        testRepository.save(test);
    }

    public Test findByName(String name) {
        return testRepository.findByName(name);
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }
}
