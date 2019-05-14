package server.repositories;

import entities.Connection;
import entities.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection,Long>,ConnectionRepositoryCustom{

}
