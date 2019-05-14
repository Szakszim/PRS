package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.other.AnimalToDeleteDto;
import dtos.other.BasicHallDataDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class AnimalHallRepositoryCustomImpl implements AnimalHallRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public List<AnimalRowModelDto> getAnimalListByFarmId(Long farmId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAnimal cattle = QAnimal.animal;
        QCattlePassport cattlePassport = QCattlePassport.cattlePassport;
        QAnimalHall animalHall = QAnimalHall.animalHall;

        List<AnimalRowModelDto> result = (List<AnimalRowModelDto>) query
                .select(Projections.constructor(AnimalRowModelDto.class,cattle.id, cattlePassport.cattleNumber,  cattlePassport.sexType,  cattlePassport.raceType.businessKey, animalHall.animalType.businessKey,cattlePassport.motherNumber,  cattlePassport.birthDate,cattle.animalHall, cattlePassport.description))
                .from(cattlePassport)
                .leftJoin(cattle)
                .on(cattlePassport.cattle.id.eq(cattle.id))
                .leftJoin(animalHall)
                .on(cattle.animalHall.id.eq(animalHall.id))
                .where(cattle.farm.id.eq(farmId))
                .fetch();

        return result;
    }

    public void deleteAnimalByIdAndType(AnimalToDeleteDto animalToDeleteDto){

    }

    @Override
    public List<BasicHallDataDto> findAllCoordinatesByFarmId(Long farmId){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAnimalHall animalHall = QAnimalHall.animalHall;

        List<BasicHallDataDto> result = query.select(Projections.constructor(BasicHallDataDto.class, animalHall.id, animalHall.name, animalHall.animalType, animalHall.capacity, animalHall.fullLevel, animalHall.latitude, animalHall.longitude))
                .from(animalHall)
                .where(animalHall.farm.id.eq(farmId))
                .fetch();

        return result;
    }
}
