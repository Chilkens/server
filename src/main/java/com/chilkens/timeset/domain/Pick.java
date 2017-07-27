package com.chilkens.timeset.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="pick")
public class Pick  implements Serializable {
    @Id
    @Column
    private Long pickId;

    @OneToOne
    @JoinColumn(name = "tableId")
    private TimeTable time_table;

    @Column
    private String createdBy;

    @Column
    private Timestamp createdAt;

    @Column
    private Short deleted;

    public static Pick build(String createdBy, Timestamp createdAt, Short deleted) {
        return Pick.builder()
                .createdBy(createdBy)
                .createdAt(createdAt)
                .deleted(deleted)
                .build();
    }
}
