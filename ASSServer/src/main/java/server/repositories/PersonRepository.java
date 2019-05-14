package server.repositories;

import entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long>, PersonRepositoryCustom {
}
