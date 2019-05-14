package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.rowmodels.PurchaseRowModelDto;
import entities.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PurchaseRepositoryCustomImpl implements PurchaseRepositoryCustom {

    @Inject
    private EntityManager em;

//    @Override
//    public Account findAccountByCredentials(String login, String password) {
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//        QAccount account = QAccount.account;
//
//        Account result = (Account) query
//                .from(account)
//                .where(account.login.eq(login).and(account.password.eq(password)))
//                .fetchOne();
//
//        return result;
//    }
//
//    @Override
//    public List<AccountRowModelDto> findAllAccountsWithPersonData() {
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//        QAccount account = QAccount.account;
//        QPerson person = QPerson.person;
//
//        List<AccountRowModelDto> result = (List<AccountRowModelDto>) query.select(Projections.constructor(AccountRowModelDto.class, account.id, account.uniqueId, account.email, account.login, account.accountType, account.passwordExpiredDate, person.firstName, person.secondName, person.surname, account.creationDate, account.registrationConfirmationIndicator, account.activityIndicator))
//                .from(account)
//                .leftJoin(person)
//                .on(account.person.id.eq(person.id))
//                .fetch();
//
//        return result;
//    }
//
//    @Override
//    public Boolean checkEmailAvailability(String mail){
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//        QAccount account = QAccount.account;
//
//        String result = query.select(account.email)
//                .from(account)
//                .where(account.email.eq(mail))
//                .fetchFirst();
//
//        if(StringUtil.isEmpty(result)){
//            return Boolean.TRUE;
//        }
//        return  Boolean.FALSE;
//    }
//
//    @Override
//    public Boolean checkUsernameAvailability(String username){
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//        QAccount account = QAccount.account;
//
//        String result = query.select(account.login)
//                .from(account)
//                .where(account.login.eq(username))
//                .fetchFirst();
//
//        if(StringUtil.isEmpty(result)){
//            return Boolean.TRUE;
//        }
//        return  Boolean.FALSE;
//    }

    @Override
    public List<PurchaseRowModelDto> findAllPurchasesWithPurchaserAndFarmData() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QPurchase purchase = QPurchase.purchase;
        QFarm farm = QFarm.farm;
        QAccount account = QAccount.account;
        QPerson person = QPerson.person;

        List<PurchaseRowModelDto> result = (List<PurchaseRowModelDto>) query.select(Projections.constructor(PurchaseRowModelDto.class, purchase.id, farm.name1, farm.name2, farm.farmNumber, person.firstName, person.secondName, person.surname, account.uniqueId, purchase.moduleType, purchase.purchaseDate, purchase.paymentIndicator, purchase.paymentDate))
                .from(purchase)
                .leftJoin(farm)
                .on(purchase.farm.id.eq(farm.id))
                .leftJoin(account)
                .on(purchase.purchaser.id.eq(account.id))
                .leftJoin(person)
                .on(account.person.id.eq(person.id))
                .fetch();

        return result;
    }
}
