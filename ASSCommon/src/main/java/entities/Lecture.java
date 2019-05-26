package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LECTURE_0008")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lecture_name")
    private String lectureName;
    @ManyToOne()
    @JoinColumn(name = "degree_course_id")
    private DegreeCourse degreeCourse;
    @ManyToOne()
    @JoinColumn(name = "lecture_type")
    private LectureType lectureType;
}
