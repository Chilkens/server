package com.chilkens.timeset.dao;


import com.chilkens.timeset.domain.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017-07-20.
 */
@Repository
public interface HistoryRepository extends JpaRepository<TimeTable, Long> {
    TimeTable findByCreatedBy (String createdBy);
}
