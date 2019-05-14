package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.rowmodels.FarmRowModelDto;
import entities.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class FarmRepositoryCustomImpl implements FarmRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public Farm findFarmByAccountId(Long accountId) {
        JPAQueryFactory query = new JPAQueryFactory(em);
//
        QPerson person = QPerson.person;
        QAccount account = QAccount.account;
        QFarm farm = QFarm.farm;
        QConnection connection = QConnection.connection;
//        QDependencies dependencies = QDependencies.dependencies;
//
        Farm result = (Farm) query
                .from(farm)
                .leftJoin(connection)
                .on(farm.id.eq(connection.farm.id))
                .where(connection.account.id.eq(accountId))
                .fetchOne();

        return result;
    }

    public List<FarmRowModelDto> findAllFarmsWithOwnerData(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QFarm farm = QFarm.farm;
        QOwner owner = QOwner.owner;

        List<FarmRowModelDto> result = (List<FarmRowModelDto>) query.select(Projections.constructor(FarmRowModelDto.class,farm.id, farm.name1, farm.name2, farm.farmNumber, farm.address, owner.uniqueId, owner.ownerType, owner.name1, owner.name2, owner.name3, owner.nip))
                .from(farm)
                .leftJoin(owner)
                .on(farm.owner.id.eq(owner.id))
                .fetch();

        return result;
    }
}
