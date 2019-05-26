package server.repositories;

import dtos.LecturerDto;
import entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    LecturerDto findLecturerByEMailAndPassword(String eMail, String password);
}
