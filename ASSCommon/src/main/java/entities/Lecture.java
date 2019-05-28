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
    @JoinColumn(name = "dean_group_id")
    private DeanGroup deanGroup;
    @ManyToOne()
    @JoinColumn(name = "lecture_type_id")
    private LectureType lectureType;
    @ManyToOne()
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
        {
            return true;
        }
        if(!(obj instanceof Lecture))
        {
            return false;
        }
        Lecture lecture = (Lecture)obj;
        return id.equals(lecture.id) && lectureName.equals(lecture.getLectureName());
    }
}
