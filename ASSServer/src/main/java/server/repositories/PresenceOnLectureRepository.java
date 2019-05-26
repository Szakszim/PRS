package server.repositories;

import dtos.PresenceOnLectureDto;
import entities.PresenceOnLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceOnLectureRepository extends JpaRepository<PresenceOnLecture, Integer> {
    PresenceOnLectureDto findPresenceOnLectureById(Integer id);

    void deletePresenceOnLectureById(Integer id);

    @Query("SELECT NEW dtos.PresenceOnLectureDto(p) FROM PresenceOnLecture p")
    List<PresenceOnLectureDto> findAllPresenceOnLecturesAsDto();
}
