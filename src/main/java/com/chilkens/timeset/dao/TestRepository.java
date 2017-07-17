package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hoody on 2017-07-16.
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long>{
    Test findByName(String name);
}
