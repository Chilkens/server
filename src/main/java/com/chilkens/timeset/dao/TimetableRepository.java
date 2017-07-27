package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Timetable findByKey(String key);
    Timetable findByCreatedBy (String createdBy);
    //    Timetable addTimeTable ();
}
