package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Timetable findByKeyUrl(String keyUrl);

    Timetable findByTableId(Long tableId);

    @Modifying
    @Query("UPDATE Timetable SET current = current + 1 WHERE tableId = :tableId")
    void updateCurrrent(@Param("tableId") Long tableId);

    @OrderBy("tableId DESC")
    List<Timetable> findAllByCreatedBy(String createdBy);

    /*
    @Query(value = "SELECT * FROM time_table WHERE createdBy = :createdBy AND current != max order by tableId desc",
            nativeQuery = true)
    List<Timetable> findProgressTable(@Param("createdBy") String createdBy);

    @Query(value = "SELECT * FROM time_table WHERE createdBy = :createdBy AND current = max order by tableId desc",
            nativeQuery = true)
    List<Timetable> findFinishTable(@Param("createdBy") String createdBy);
    */
}
