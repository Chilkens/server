package com.chilkens.timeset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hoody on 2017-08-02.
 */

/*
    pick 테이블과 pick_detail 테이블 조인한 Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "pick")
@EntityListeners(value = { AuditingEntityListener.class })
public class PickJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pickId")
    private Long pickId;

    @Column(name="tableId")
    private Long tableId; // Foreign Key

    @Column
    private String createdBy; // 작성한 사람

    @Column(insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt; // 작성 날짜

    @Column
    private boolean deleted; // 삭제 여부

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="pickId")
    private List<PickDetail> pickDetailList = new ArrayList<>();

    /*
     sample result
     PickJoin(pickId=3, tableId=2, createdBy=은영, createdAt=2017-07-30 05:53:56.0, deleted=false, pick_detail=[PickDetail(detailId=26, pickId=3, pickDate=2017-08-01 00:00:00.0, pickTime=22)])
     PickJoin(pickId=4, tableId=2, createdBy=나연, createdAt=2017-07-30 05:53:56.0, deleted=false, pick_detail=[PickDetail(detailId=33, pickId=4, pickDate=2017-08-02 00:00:00.0, pickTime=12|18)])
     PickJoin(pickId=5, tableId=2, createdBy=병찬, createdAt=2017-07-30 05:53:56.0, deleted=false, pick_detail=[PickDetail(detailId=28, pickId=5, pickDate=2017-08-05 00:00:00.0, pickTime=18), PickDetail(detailId=29, pickId=5, pickDate=2017-08-06 00:00:00.0, pickTime=15)])
     */

}
