package server.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ConnectionRepositoryCustomImpl implements ConnectionRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public List<Connection> getAllConnectionsByUserId(Long userId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QConnection connection = QConnection.connection;
        QFarm farm = QFarm.farm;

        List<Connection> result = query.select(connection)
                .from(connection)
                .leftJoin(farm)
                .on(connection.farm.id.eq(farm.id))
                .where(connection.account.id.eq(userId))
                .fetch();

        return result;
    }


    @Override
    public List<Connection> findAllConnectionsByFarmId(Long farmId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QConnection connection = QConnection.connection;
        QFarm farm = QFarm.farm;

        List<Connection> result = query.select(connection)
                .from(connection)
                .leftJoin(farm)
                .on(connection.farm.id.eq(farm.id))
                .where(farm.id.eq(farmId))
                .fetch();

        return result;
    }
}
