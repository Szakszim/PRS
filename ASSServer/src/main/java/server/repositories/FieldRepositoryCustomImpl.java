package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.other.BasicFieldDataDto;
import entities.*;
import org.hibernate.criterion.Projection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class FieldRepositoryCustomImpl implements FieldRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public BasicFieldDataDto getBasicFieldDataByFieldId(Long fieldId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QField field = QField.field;

       BasicFieldDataDto result = query.select(Projections.constructor(BasicFieldDataDto.class,field.id,field.name, field.registrationNumber, field.area, field.measurementUnit, field.possessionState,field.description))
               .from(field)
               .where(field.id.eq(fieldId))
               .fetchOne();

        return result;
    }
}
