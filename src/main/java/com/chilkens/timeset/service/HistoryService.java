package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.HistoryRepository;
import com.chilkens.timeset.domain.Test;
import com.chilkens.timeset.domain.Time_table;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by user on 2017-07-20.
 */
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    public Time_table findByCreatedBy(String createdBy) {
        return historyRepository.findByCreatedBy(createdBy);
    }

    public List<Time_table> findAll() {
        return historyRepository.findAll();
    }

}
