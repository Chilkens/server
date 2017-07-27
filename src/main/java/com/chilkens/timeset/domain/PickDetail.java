package com.chilkens.timeset.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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
    @Column(name="detailId")
    private Long detailId;

    @OneToOne
    @JoinColumn(name = "pickId")
    private Pick pick;

    @Column
    private Date pickDate;

    @Column
    private Long createdBy;

    public static PickDetail build(Long detailed, Date pickDate, Long createdBy) {
        return PickDetail.builder()
                .detailId(detailed)
                .pickDate(pickDate)
                .createdBy(createdBy)
                .build();
    }
}
