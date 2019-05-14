package server.repositories;

import entities.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepositoryCustom {

    String getNextOwnerUniqueId();

}
