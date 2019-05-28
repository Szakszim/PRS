package dtos;

import entities.Lecture;
import entities.Lecturer;
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
    private String hourTime;
    private Student student;
    private Lecture lecture;
    private Lecturer lecturer;
    private String room;
    private Boolean wasLate;

    public PresenceOnLectureDto(PresenceOnLecture presenceOnLecture) {
        this.id = presenceOnLecture.getId();
        this.presenceDate = presenceOnLecture.getPresenceDate();
        this.hourTime = presenceOnLecture.getHourTime();
        this.student = presenceOnLecture.getStudent();
        this.lecture = presenceOnLecture.getLecture();
        this.lecturer = presenceOnLecture.getLecturer();
        this.room = presenceOnLecture.getRoom();
        this.wasLate = presenceOnLecture.getWasLate();
    }

    public PresenceOnLecture toEntity() {
        return new PresenceOnLecture(id, presenceDate, hourTime, student, lecture, lecturer, room, wasLate);
    }
}
