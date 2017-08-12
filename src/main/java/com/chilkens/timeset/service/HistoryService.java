package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickRepository;
import com.chilkens.timeset.dao.TimetableRepository;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    TimetableRepository timetableRepository;

    @Autowired
    PickRepository pickRepository;

    public List<Timetable> findAllInTimetable (String createdBy) {
        return timetableRepository.findAllByCreatedBy(createdBy);
    }

    public List<Timetable> findFinInTimetable (String createdBy) {
        List<Timetable> timetable = timetableRepository.findAllByCreatedBy(createdBy);
        List<Timetable> finishTable = new ArrayList<>();
        for(int i=0; i<timetable.size(); i++){
            if(timetable.get(i).getCurrent() == timetable.get(i).getMax()){
                finishTable.add(timetable.get(i));
            }
        }
        return finishTable;
    }

    public List<Timetable> findProgInTimetable (String createdBy) {
        List<Timetable> timetable = timetableRepository.findAllByCreatedBy(createdBy);
        List<Timetable> progressTable = new ArrayList<>();
        for(int i=0; i<timetable.size(); i++){
            if(timetable.get(i).getCurrent() < timetable.get(i).getMax()){
                progressTable.add(timetable.get(i));
            }
        }
        return progressTable;
    }

    public List<Pick> findInPick (Long tableId) {
        return pickRepository.findByTableId(tableId);
    }
}
