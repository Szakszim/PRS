package server.repositories;

import dtos.rowmodels.FarmRowModelDto;
import entities.Farm;
import entities.FieldCoordinate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldCoordinateRepositoryCustom {

    List<List<FieldCoordinate>> findAllCoordinatesByFarmId(Long farmId);

}
