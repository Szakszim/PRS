package server.repositories;

import dtos.LectureTypeDto;
import entities.LectureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureTypeRepository extends JpaRepository<LectureType, Integer> {
    LectureTypeDto findLectureTypeById(Integer id);

    void deleteLectureTypeById(Integer id);

    @Query("SELECT NEW CardDto(c.id,c.student) FROM Card c")
    List<LectureTypeDto> findAllLectureTypesAsDto();
}
