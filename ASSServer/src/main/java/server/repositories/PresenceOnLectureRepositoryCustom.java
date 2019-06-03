package server.repositories;

import dtos.PresenceOnLectureDto;
import entities.PresenceOnLecture;
import entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PresenceOnLectureRepositoryCustom{

    PresenceOnLecture findByPresenceDateAndHourTimeRoomAndStudent(Date date, String hour, String room, Student student);

}
