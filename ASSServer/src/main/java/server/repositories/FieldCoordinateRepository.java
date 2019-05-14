package server.repositories;

import entities.Farm;
import entities.FieldCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldCoordinateRepository extends JpaRepository<FieldCoordinate,Long>, FieldCoordinateRepositoryCustom{
}
