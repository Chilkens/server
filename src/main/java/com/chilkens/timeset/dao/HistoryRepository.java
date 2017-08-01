package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Pick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<Pick, Long>{
}
