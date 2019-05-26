package dtos;

import entities.Lecture;
import entities.PresenceOnLecture;
import entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PresenceOnLectureDto {
    private Integer id;
    private Date presenceDate;
    private Student student;
    private Lecture lecture;

    public PresenceOnLectureDto(PresenceOnLecture presenceOnLecture) {
        this.id = presenceOnLecture.getId();
        this.presenceDate = presenceOnLecture.getPresenceDate();
        this.student = presenceOnLecture.getStudent();
        this.lecture = presenceOnLecture.getLecture();
    }

    public PresenceOnLecture toEntity() {
        return new PresenceOnLecture(id, presenceDate, student, lecture);
    }
}