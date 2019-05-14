package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.rowmodels.FarmRowModelDto;
import entities.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class FieldCoordinateRepositoryCustomImpl implements FieldCoordinateRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public List<List<FieldCoordinate>> findAllCoordinatesByFarmId(Long farmId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QFarm farm = QFarm.farm;
        QField field = QField.field;
        QFieldCoordinate fieldCoordinate = QFieldCoordinate.fieldCoordinate;


        List<Field> fields = query.select(field)
                .from(field)
                .leftJoin(farm)
                .on(field.farm.id.eq(farm.id))
                .where(farm.id.eq(farmId))
                .fetch();

//        List<FieldCoordinate> list = query.select(fieldCoordinate)
//                .from(fieldCoordinate)
//                .leftJoin(field)
//                .on(fieldCoordinate.field.id.eq(field.id))
//                .leftJoin(farm)
//                .on(field.farm.id.eq(farm.id))
//                .where(farm.id.eq(farmId))
//                .fetch();


        List<List<FieldCoordinate>> result = new ArrayList<>();
        for(int i =0;i<fields.size();i++){
            List<FieldCoordinate> coordinateList = query.select(fieldCoordinate)
                    .from(fieldCoordinate)
                    .leftJoin(field)
                    .on(fieldCoordinate.field.id.eq(field.id))
                    .where(field.id.eq(fields.get(i).getId()))
                    .fetch();

            if(!coordinateList.isEmpty()){
                result.add(coordinateList);
            }
        }

        return result;
    }
}
