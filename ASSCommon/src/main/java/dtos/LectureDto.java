package dtos;

import entities.DegreeCourse;
import entities.Lecture;
import entities.LectureType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto {
    private Integer id;
    private String lectureName;
    private DegreeCourse degreeCourse;
    private LectureType lectureType;


    public LectureDto(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureName = lecture.getLectureName();
        this.degreeCourse = lecture.getDegreeCourse();
        this.lectureType = lecture.getLectureType();
    }

    public Lecture toEntity() {
        return new Lecture(id, lectureName, degreeCourse, lectureType);
    }
}
