package dtos;

import entities.DegreeCourse;
import entities.Faculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DegreeCourseDto {
    private Integer id;
    private String degreeName;
    private Faculty faculty;

    public DegreeCourseDto(DegreeCourse degreeCourse) {
        this.id = degreeCourse.getId();
        this.degreeName = degreeCourse.getDegreeName();
        this.faculty = degreeCourse.getFaculty();
    }

    public DegreeCourse toEntity() {
        return new DegreeCourse(id, degreeName, faculty);
    }
}
