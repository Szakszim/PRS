package server.repositories;

import dtos.DegreeCourseDto;
import entities.DegreeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeCourseRepository extends JpaRepository<DegreeCourse, Integer> {
    DegreeCourseDto findDegreeCourseById(Integer id);

    void deleteDegreeCourseById(Integer id);

    @Query("SELECT NEW dtos.DegreeCourseDto(d) FROM DegreeCourse d")
    List<DegreeCourseDto> findAllDegreeCoursesAsDto();
}
