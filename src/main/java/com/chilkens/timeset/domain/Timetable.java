package com.chilkens.timeset.domain;

        import lombok.*;
        import org.springframework.data.jpa.domain.support.AuditingEntityListener;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.sql.Date;
        import java.sql.Timestamp;

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
    @Column(name = "tableId")
    private Long tableId;

    @Column(unique=true)
    private String keyUrl;

    @Column
    private String title;

    @Column
    private Integer time;

    @Column
    private Date start;

    @Column
    private Date end;

    @Column
    private Integer max;

    @Column
    private Integer current;

    @Column
    private Timestamp createdAt;

    @Column
    private String createdBy;

    @Column
    private Short deleated;

    public static Timetable build(String title, Integer time, Date start, Date end, Integer max, Integer current, String createdBy) {
        return Timetable.builder()
                .title(title)
                .time(time)
                .start(start)
                .end(end)
                .max(max)
                .current(current)
                .createdBy(createdBy)
                .build();
    }
}
