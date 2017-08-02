package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Pick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Repository
public interface PickRepository extends JpaRepository<Pick, Long>{
    List<Pick> findById(Long pickId);
}
