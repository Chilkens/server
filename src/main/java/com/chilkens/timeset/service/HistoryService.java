package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.HistoryRepository;
import com.chilkens.timeset.dao.TimetableRepository;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    TimetableRepository timetableRepository;

    @Autowired
    HistoryRepository historyRepository;

    public Timetable findByCreatedBy(String createdBy) {
        return timetableRepository.findByCreatedBy(createdBy);
    }

    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    /*public List<Pick> findAll() {
        return historyRepository.findAll();
    }findAll*/
}
