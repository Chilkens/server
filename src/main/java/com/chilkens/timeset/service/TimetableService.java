package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.TimetableRepository;
import com.chilkens.timeset.domain.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Service
public class TimetableService {
    @Autowired
    TimetableRepository timetableRepository;

    public void save(Timetable timetable){
        timetableRepository.save(timetable);
    }
}
