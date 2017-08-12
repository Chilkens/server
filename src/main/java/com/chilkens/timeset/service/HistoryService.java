package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickRepository;
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
    PickRepository pickRepository;

    public List<Timetable> findInTimetable (String createdBy) {
        return timetableRepository.findAllByCreatedBy(createdBy);
    }

    public List<Pick> findInPick (Long tableId) {
        return pickRepository.findByTableId(tableId);
    }
}
