package server.repositories;

import entities.Connection;
import entities.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepositoryCustom {

    List<Connection> getAllConnectionsByUserId(Long userId);

    List<Connection> findAllConnectionsByFarmId(Long farmId);

}
