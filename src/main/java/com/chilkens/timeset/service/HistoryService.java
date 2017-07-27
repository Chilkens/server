package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.HistoryRepository;
import com.chilkens.timeset.domain.TimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017-07-20.
 */
@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    public TimeTable findByCreatedBy(String createdBy) {
        return historyRepository.findByCreatedBy(createdBy);
    }

    public List<TimeTable> findAll() {
        return historyRepository.findAll();
    }

}
