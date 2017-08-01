package com.chilkens.timeset.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "pick_detail")
public class PickDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "detailId")
    private Long detailId;

    @Column(name = "pickId")
    private Long pickId; // Foreign Key

    @Column
    private Date pickDate; // 선택 날짜

    @Column
    private Integer pickTime; // 해당 날짜에서 선택한 시간

    public static PickDetail build(Long pickId, Date pickDate, Integer pickTime) {

        return PickDetail.builder()
                .pickId(pickId)
                .pickDate(pickDate)
                .pickTime(pickTime)
                .build();
    }
}
