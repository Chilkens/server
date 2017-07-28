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
@Table(name = "time_table")
public class Timetable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tableId")
    private Long tableId;

    @Column
    private String key; // URL

    @Column
    private String title; // 방 제목

    @Column
    private Integer time; // 모임 시간

    @Column
    private Date start; // 날짜 범위 시작

    @Column
    private Date end; // 날짜 범위 마지막

    @Column
    private Integer max; // 인원수

    @Column
    private Integer current; // 현재까지 timepick한 인원수

    @Column
    private Date createdAt; // 방 개설 날짜

    @Column
    private String createdBy; // 방 개설자

    @Column
    private boolean deleted; // 삭제 여부

    public static Timetable build(String key, String title, Integer time, Date start, Date end,
                                  Integer max, Integer current, Date createdAt, String createdBy, boolean deleted) {

        return Timetable.builder()
                .key(key)
                .title(title)
                .time(time)
                .start(start)
                .end(end)
                .max(max)
                .current(current)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .deleted(deleted)
                .build();
    }
}
