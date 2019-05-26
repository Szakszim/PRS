package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRESENCE_ON_LECTURE_0009")
public class PresenceOnLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "presence_date")
    private Date presenceDate;
    @Column(name = "hour_time")
    private String hourTime;
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne()
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    @ManyToOne()
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;
}
