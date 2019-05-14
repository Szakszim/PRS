package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.other.ModulePrivilegeDto;
import dtos.rowmodels.KeyAssignmentRowModelDto;
import entities.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class KeyAssignmentRepositoryCustomImpl implements KeyAssignmentRepositoryCustom {
    @Inject
    private EntityManager em;

//    @Override
//    public ModulePrivilegeDto getModulePrivilegesByFarmId(Long farmId) {
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//        QAccount account = QAccount.account;
//
//        QKeyAssignment keyAssignment = QKeyAssignment.keyAssignment;
//        QKey key = QKey.key;
//        QFarm farm = QFarm.farm;
//        Date currentDate = new Date();
//
//        Object result1 = (Object) query
//                .from(keyAssignment)
//                .leftJoin(key)
//                .on(key.id.eq(keyAssignment.licenseKey.id))
//                .leftJoin(farm)
//                .on(farm.id.eq(keyAssignment.farm.id))
//                .where(farm.id.eq(farmId)
//                        .and(key.keyExpiredDate.after(currentDate))
//                        .and(key.activityIndicator.eq(Boolean.TRUE))
//                        .and(keyAssignment.moduleType.businessKey.eq("000")))
//                .fetchOne();
//
//        Object result2 = (Object) query
//                .from(keyAssignment)
//                .leftJoin(key)
//                .on(key.id.eq(keyAssignment.licenseKey.id))
//                .leftJoin(farm)
//                .on(farm.id.eq(keyAssignment.farm.id))
//                .where(farm.id.eq(farmId)
//                        .and(key.keyExpiredDate.after(currentDate))
//                        .and(key.activityIndicator.eq(Boolean.TRUE))
//                        .and(keyAssignment.moduleType.businessKey.eq("001")))
//                .fetchOne();
//
//        Object result3 = (Object) query
//                .from(keyAssignment)
//                .leftJoin(key)
//                .on(key.id.eq(keyAssignment.licenseKey.id))
//                .leftJoin(farm)
//                .on(farm.id.eq(keyAssignment.farm.id))
//                .where(farm.id.eq(farmId)
//                        .and(key.keyExpiredDate.after(currentDate))
//                        .and(key.activityIndicator.eq(Boolean.TRUE))
//                        .and(keyAssignment.moduleType.businessKey.eq("002")))
//                .fetchOne();
//
//        Object result4 = (Object) query
//                .from(keyAssignment)
//                .leftJoin(key)
//                .on(key.id.eq(keyAssignment.licenseKey.id))
//                .leftJoin(farm)
//                .on(farm.id.eq(keyAssignment.farm.id))
//                .where(farm.id.eq(farmId)
//                        .and(key.keyExpiredDate.after(currentDate))
//                        .and(key.activityIndicator.eq(Boolean.TRUE))
//                        .and(keyAssignment.moduleType.businessKey.eq("003")))
//                .fetchOne();
//
//
////        SELECT * FROM KEY_ASSIGNMENT_0017
////        LEFT JOIN KEY_0016 K0016 on KEY_ASSIGNMENT_0017.[KEY] = K0016.ID
////        LEFT JOIN FARM_0006 F0006 on KEY_ASSIGNMENT_0017.FARM = F0006.ID
////        WHERE F0006.ID = 100000
////        AND K0016.KEY_EXPIRED_DATE >= GETDATE()
////        AND K0016.ACTIVITY_INDICATOR = 1
////        AND K0016.MODULE_BK = 1;
//
//        ModulePrivilegeDto modulePrivilegeDto = new ModulePrivilegeDto();
//
//        if (result1 != null) {
//            modulePrivilegeDto.setPlantsModulePrivilege(Boolean.TRUE);
//        }
//        if (result2 != null) {
//            modulePrivilegeDto.setAnimalsModulePrivilege(Boolean.TRUE);
//        }
//        if (result3 != null) {
//            modulePrivilegeDto.setMachineryModulePrivilege(Boolean.TRUE);
//        }
//        if (result4 != null) {
//            modulePrivilegeDto.setAccountantModulePrivilege(Boolean.TRUE);
//        }
//
//        return modulePrivilegeDto;
//    }

    @Override
    public List<KeyAssignmentRowModelDto> findAllKeysWithFarmAndPurchaserData() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QKey key = QKey.key;
        QKeyAssignment keyAssignment = QKeyAssignment.keyAssignment;
        QFarm farm = QFarm.farm;
        QPurchase purchase = QPurchase.purchase;
        QAccount account = QAccount.account;
        QPerson person = QPerson.person;

        List<KeyAssignmentRowModelDto> result = (List<KeyAssignmentRowModelDto>) query.select(Projections.constructor(KeyAssignmentRowModelDto.class,keyAssignment.id, key.value, keyAssignment.moduleType, keyAssignment.licenseExpiredDate, farm.name1, farm.name2, farm.farmNumber, person.firstName, person.secondName, person.surname, keyAssignment.confirmationIndicator))
                .from(keyAssignment)
                .leftJoin(key)
                .on(keyAssignment.licenseKey.id.eq(key.id))
                .leftJoin(farm)
                .on(keyAssignment.farm.id.eq(farm.id))
                .leftJoin(purchase)
                .on(keyAssignment.purchase.id.eq(purchase.id))
                .leftJoin(account)
                .on(purchase.purchaser.id.eq(account.id))
                .leftJoin(person)
                .on(account.person.id.eq(person.id))
                .fetch();
        //TODO CHANGE TO JOIN TO PURCHASE IT IS BETTER THAN PURCHASE.PURCHASER.ID

        return result;
    }
}
