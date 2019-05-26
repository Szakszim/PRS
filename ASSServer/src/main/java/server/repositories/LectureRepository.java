package server.repositories;

import dtos.LectureDto;
import entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    LectureDto findLectureById(Integer id);

    void deleteLectureById(Integer id);

    @Query("SELECT NEW dtos.LectureDto(l) FROM Lecture l")
    List<LectureDto> findAllLecturesAsDto();
}
