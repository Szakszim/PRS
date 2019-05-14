package server.repositories;

import entities.Key;
import entities.KeyAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyAssignmentRepository extends JpaRepository<KeyAssignment, Long>, KeyAssignmentRepositoryCustom {
}
