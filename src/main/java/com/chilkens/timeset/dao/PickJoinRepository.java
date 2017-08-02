package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.PickJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hoody on 2017-08-02.
 */
@Repository
public interface PickJoinRepository extends JpaRepository<PickJoin, Long> {
    List<PickJoin> findByTableId(Long tableId);
}
