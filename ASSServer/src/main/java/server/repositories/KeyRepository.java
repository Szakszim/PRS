package server.repositories;

import entities.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Long>, KeyRepositoryCustom {
}
