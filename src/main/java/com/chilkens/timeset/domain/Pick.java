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
@Table(name="pick")
public class Pick implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pickId")
    private Long pickId;

    @Column(name="tableId")
    private Long tableId; // Foreign Key

    @Column
    private String createdBy; // 작성한 사람

    @Column(insertable = false)
    private Date createdAt; // 작성 날짜

    @Column
    private boolean deleted; // 삭제 여부

    public static Pick build(Long tableId, String createdBy) {

        return Pick.builder()
                .tableId(tableId)
                .createdBy(createdBy)
                .build();
    }
}
