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
@Table(name = "DEAN_GROUP_0003")
public class DeanGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dean_name")
    private String deanName;
    @ManyToOne()
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @Column(name = "semester")
    private Integer semester;
    @ManyToOne()
    @JoinColumn(name = "degree_course_id")
    private DegreeCourse degreeCourse;
}
