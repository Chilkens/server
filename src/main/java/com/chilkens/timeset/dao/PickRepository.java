package com.chilkens.timeset.dao;

import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Repository
public interface PickRepository extends JpaRepository<Pick, Long>{
    List<Pick> findByTableId(Long tableId);

    @Query(value = "SELECT createdBy FROM pick WHERE tableId = :tableId", nativeQuery = true)
    List<String> findNameByTableId(@Param("tableId") Long tableId);

    @Query(value = "SELECT tableId FROM pick WHERE createdBy = :createdBy", nativeQuery = true)
    List<BigInteger> findTableByName(@Param("createdBy") String createdBy);
}
