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
public class TimeTable implements Serializable {
    @Id
    @Column
    private Long tableId;

    @Column(unique=true)
    private String key;

    @Column
    private String title;

    @Column
    private Long time;

    @Column
    private Date start;

    @Column
    private Date end;

    @Column
    private Long max;

    @Column
    private Long current;

    @Column
    private Timestamp createdAt;

    @Column
    private String createdBy;

    @Column
    private Short deleated;

    public static TimeTable build(String key, String title, Long time, Date start, Date end, Long max, Long current, Timestamp createdAt, String createdBy, Short deleated) {
        return TimeTable.builder()
                .key(key)
                .title(title)
                .time(time)
                .start(start)
                .end(end)
                .max(max)
                .current(current)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .deleated(deleated)
                .build();
    }
}
