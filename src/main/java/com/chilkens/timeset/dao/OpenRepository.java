package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Test;
import com.chilkens.timeset.domain.Time_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017-07-20.
 */
@Repository
public interface OpenRepository  extends JpaRepository<Time_table, Long> {
    Time_table addTimeTable ();
}
