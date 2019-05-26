package dtos;

import entities.DeanGroup;
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
public class DeanGroupDto {
    private Integer id;
    private String deanName;
    private Faculty faculty;
    private Integer semester;
    private DegreeCourse degreeCourse;

    public DeanGroupDto(DeanGroup deanGroup) {
        this.id = deanGroup.getId();
        this.deanName = deanGroup.getDeanName();
        this.faculty = deanGroup.getFaculty();
        this.semester = deanGroup.getSemester();
        this.degreeCourse = deanGroup.getDegreeCourse();
    }

    public DeanGroup toEntity() {
        return new DeanGroup(id, deanName, faculty, semester, degreeCourse);
    }
}
