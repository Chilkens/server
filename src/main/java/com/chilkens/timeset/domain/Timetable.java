package com.chilkens.timeset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    @Column(name = "keyUrl", unique = true)
    private String keyUrl; // URL

    @Column
    private String title; // 방 제목

    @Column
    private Integer time; // 모임 시간

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start; // 날짜 범위 시작

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end; // 날짜 범위 마지막

    @Column
    private Integer max; // 인원수

    @Column
    private Integer current; // 현재까지 timepick한 인원수

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt; // 방 개설 날짜

    @Column
    private String createdBy; // 방 개설자

    @Column
    private boolean deleted; // 삭제 여부

    private static String getKey() {
        SimpleDateFormat time = new SimpleDateFormat("yyMMddHHmmssSSS");
        String randomString = "";
        for(int i= 0; i<10; i++) {
            randomString += String.valueOf((char) ((int) (new Random().nextInt(26)) + 97));
        }
        return time.format(new Date(System.currentTimeMillis())) + randomString;
    }

    public static Timetable build(String keyUrl, String title, Integer time, Date start, Date end,
                                  Integer max, Integer current, String createdBy) {
        return Timetable.builder()
                .keyUrl(keyUrl)
                .title(title)
                .time(time)
                .start(start)
                .end(end)
                .max(max)
                .current(current)
                .createdBy(createdBy)
                .build();
    }

    public static Timetable build(Timetable timetable) {
        return Timetable.builder()
                .keyUrl(getKey())
                .title(timetable.title)
                .time(timetable.time)
                .start(timetable.start)
                .end(timetable.end)
                .max(timetable.max)
                .current(0)
                .createdBy(timetable.createdBy)
                .build();
    }
}
