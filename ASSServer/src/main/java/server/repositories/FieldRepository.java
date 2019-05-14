package server.repositories;

import entities.Field;
import entities.FieldCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field,Long>, FieldRepositoryCustom{
}
