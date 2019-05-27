package dtos;

import entities.DeanGroup;
import entities.Lecture;
import entities.LectureType;
import entities.Lecturer;
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
    private DeanGroup deanGroup;
    private LectureType lectureType;
    private Lecturer lecturer;


    public LectureDto(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureName = lecture.getLectureName();
        this.deanGroup = lecture.getDeanGroup();
        this.lectureType = lecture.getLectureType();
        this.lecturer = lecture.getLecturer();
    }

    public Lecture toEntity() {
        return new Lecture(id, lectureName, deanGroup, lectureType, lecturer);
    }
}
