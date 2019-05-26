package server.repositories;

import dtos.FacultyDto;
import entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    FacultyDto findFacultyById(Integer id);

    void deleteFacultyById(Integer id);

    @Query("SELECT NEW dtos.FacultyDto(f) FROM Faculty f")
    List<FacultyDto> findAllFacultiesAsDto();
}
