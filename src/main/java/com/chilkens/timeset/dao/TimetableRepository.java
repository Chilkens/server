package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.sql.Time;
import java.util.List;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Timetable findByKeyUrl(String keyUrl);

    @Modifying
    @Query("UPDATE Timetable SET current = current + 1 WHERE tableId = :tableId")
    void updateCurrrent(@Param("tableId") Long tableId);

    List<Timetable> findAllByCreatedByOrderByCreatedAtDesc(String createdBy);

    List<Timetable> findTop5ByCreatedByOrderByCreatedAtDesc(String createdBy);

    @Query("SELECT t.tableId FROM Timetable t WHERE keyUrl=:keyUrl")
    Long findTableIdByKeyUrl(@Param("keyUrl") String keyUrl);
}