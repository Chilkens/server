package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Timetable findByKeyUrl(String keyUrl);
    List<Timetable> findAllByCreatedBy(String createdBy);
}
