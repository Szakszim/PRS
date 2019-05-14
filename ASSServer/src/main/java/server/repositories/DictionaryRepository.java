package server.repositories;

import entities.AnimalHall;
import entities.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary,Long>,DictionaryRepositoryCustom{
}
