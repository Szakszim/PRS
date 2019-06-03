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
public interface PresenceOnLectureRepository extends JpaRepository<PresenceOnLecture, Integer>, PresenceOnLectureRepositoryCustom{
    PresenceOnLectureDto findPresenceOnLectureById(Integer id);

    void deletePresenceOnLectureById(Integer id);

    @Query("SELECT NEW dtos.PresenceOnLectureDto(p) FROM PresenceOnLecture p")
    List<PresenceOnLectureDto> findAllPresenceOnLecturesAsDto();

    PresenceOnLecture findByPresenceDateAndHourTimeRoomAndStudent(Date date, String hour, String room, Student student);

    List<PresenceOnLecture> findAllByPresenceDateAndHourTimeAndRoom(Date date, String hour, String room);

    List<PresenceOnLecture> findAllByLecturer_Id(Integer id);

    List<PresenceOnLecture> findAllByLecture_Id(Integer id);

    List<PresenceOnLecture> findAllByStudent_IdAndLecture_Id(Integer studentId,Integer lectureId);
}
