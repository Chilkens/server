package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.PickDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Repository
public interface PickDetailRepository extends JpaRepository<PickDetail, Long> {
}
