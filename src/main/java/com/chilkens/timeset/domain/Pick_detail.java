package com.chilkens.timeset.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table
public class Pick_detail {
    @Column
    private Long detailed;

    @OneToOne
    @JoinColumn(name = "pickId")
    private Pick pick;

    @Column
    private Date pickDate;

    @Column
    private Long createdBy;

    public static Pick_detail build(Long detailed, Date pickDate, Long createdBy) {
        return Pick_detail.builder()
                .detailed(detailed)
                .pickDate(pickDate)
                .createdBy(createdBy)
                .build();
    }
}
