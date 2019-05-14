package server.repositories;

import dtos.other.AnimalToDeleteDto;
import dtos.other.BasicHallDataDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalHallRepositoryCustom {

    List<AnimalRowModelDto> getAnimalListByFarmId(Long farmId);

    void deleteAnimalByIdAndType(AnimalToDeleteDto animalToDeleteDto);

    List<BasicHallDataDto> findAllCoordinatesByFarmId(Long farmId);
}
