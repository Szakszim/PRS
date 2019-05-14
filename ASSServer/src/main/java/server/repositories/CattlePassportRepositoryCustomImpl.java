package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.other.AnimalToDeleteDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.CattlePassport;
import entities.QAnimal;
import entities.QAnimalHall;
import entities.QCattlePassport;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CattlePassportRepositoryCustomImpl implements CattlePassportRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public CattlePassport findPassportByCattleId(Long cattleId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

       QAnimal cattle =QAnimal.animal;
        QCattlePassport cattlePassport = QCattlePassport.cattlePassport;

        CattlePassport result = (CattlePassport) query
                .from(cattlePassport)
                .where(cattlePassport.cattle.id.eq(cattleId))
                .fetchOne();

        return result;
    }

}
