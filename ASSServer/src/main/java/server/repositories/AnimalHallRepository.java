package server.repositories;

import entities.Account;
import entities.AnimalHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalHallRepository extends JpaRepository<AnimalHall,Long>,AnimalHallRepositoryCustom{
}
