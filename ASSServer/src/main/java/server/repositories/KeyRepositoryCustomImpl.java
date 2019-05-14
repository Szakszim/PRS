package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.other.ModulePrivilegeDto;
import dtos.rowmodels.KeyAssignmentRowModelDto;
import dtos.rowmodels.KeyRowModelDto;
import entities.*;
import enums.ModuleType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class KeyRepositoryCustomImpl implements KeyRepositoryCustom {
    @Inject
    private EntityManager em;

    @Override
    public ModulePrivilegeDto getModulePrivilegesByFarmId(Long farmId) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAccount account = QAccount.account;

        QKeyAssignment keyAssignment = QKeyAssignment.keyAssignment;
        QKey key = QKey.key;
        QFarm farm = QFarm.farm;
        Date currentDate = new Date();

        Object result1 = (Object) query
                .from(keyAssignment)
                .leftJoin(farm)
                .on(farm.id.eq(keyAssignment.farm.id))
                .where(farm.id.eq(farmId)
                        .and(keyAssignment.licenseExpiredDate.after(currentDate))
                        .and(keyAssignment.confirmationIndicator.eq(Boolean.TRUE))
                        .and(keyAssignment.moduleType.eq(ModuleType.PLANTS)))
                .fetchOne();

        Object result2 = (Object) query
                .from(keyAssignment)
                .leftJoin(key)
                .on(key.id.eq(keyAssignment.licenseKey.id))
                .leftJoin(farm)
                .on(farm.id.eq(keyAssignment.farm.id))
                .where(farm.id.eq(farmId)
                        .and(key.keyExpiredDate.after(currentDate))
                        .and(key.activityIndicator.eq(Boolean.TRUE))
                        .and(keyAssignment.moduleType.eq(ModuleType.ANIMALS)))
                .fetchOne();

        Object result3 = (Object) query
                .from(keyAssignment)
                .leftJoin(key)
                .on(key.id.eq(keyAssignment.licenseKey.id))
                .leftJoin(farm)
                .on(farm.id.eq(keyAssignment.farm.id))
                .where(farm.id.eq(farmId)
                        .and(key.keyExpiredDate.after(currentDate))
                        .and(key.activityIndicator.eq(Boolean.TRUE))
                        .and(keyAssignment.moduleType.eq(ModuleType.MACHINERY)))
                .fetchOne();

        Object result4 = (Object) query
                .from(keyAssignment)
                .leftJoin(key)
                .on(key.id.eq(keyAssignment.licenseKey.id))
                .leftJoin(farm)
                .on(farm.id.eq(keyAssignment.farm.id))
                .where(farm.id.eq(farmId)
                        .and(key.keyExpiredDate.after(currentDate))
                        .and(key.activityIndicator.eq(Boolean.TRUE))
                        .and(keyAssignment.moduleType.eq(ModuleType.ACCOUNTANT)))
                .fetchOne();


//        SELECT * FROM KEY_ASSIGNMENT_0017
//        LEFT JOIN KEY_0016 K0016 on KEY_ASSIGNMENT_0017.[KEY] = K0016.ID
//        LEFT JOIN FARM_0006 F0006 on KEY_ASSIGNMENT_0017.FARM = F0006.ID
//        WHERE F0006.ID = 100000
//        AND K0016.KEY_EXPIRED_DATE >= GETDATE()
//        AND K0016.ACTIVITY_INDICATOR = 1
//        AND K0016.MODULE_BK = 1;

        ModulePrivilegeDto modulePrivilegeDto = new ModulePrivilegeDto();

        if (result1 != null) {
            modulePrivilegeDto.setPlantsModulePrivilege(Boolean.TRUE);
        }
        if (result2 != null) {
            modulePrivilegeDto.setAnimalsModulePrivilege(Boolean.TRUE);
        }
        if (result3 != null) {
            modulePrivilegeDto.setMachineryModulePrivilege(Boolean.TRUE);
        }
        if (result4 != null) {
            modulePrivilegeDto.setAccountantModulePrivilege(Boolean.TRUE);
        }

        return modulePrivilegeDto;
    }

    @Override
    public List<KeyRowModelDto> findAllKeys() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QKey key = QKey.key;

        List<KeyRowModelDto> result = (List<KeyRowModelDto>) query.select(Projections.constructor(KeyRowModelDto.class,key.id, key.value,key.creationDate,key.activityIndicator, key.keyExpiredDate, key.usageIndicator, key.usageDate))
                .from(key)
                .fetch();
        //TODO CHANGE TO JOIN TO PURCHASE IT IS BETTER THAN PURCHASE.PURCHASER.ID

        return result;
    }

    @Override
    public Key getFirstFreeKey(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QKey key = QKey.key;

        Key result = query.select(key)
                .from(key)
                .where(key.activityIndicator.isTrue().and(key.usageIndicator.isFalse()).and(key.keyExpiredDate.after(new Date())))
                .fetchOne();

        return result;
    }
}
