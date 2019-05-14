package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.rowmodels.AccountRowModelDto;
import entities.Account;
import entities.QAccount;
import entities.QPerson;
import enums.AccountType;
import utils.StringUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public Account findAccountByCredentials(String login, String password) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAccount account = QAccount.account;

        Account result = (Account) query
                .from(account)
                .where(account.login.eq(login).and(account.password.eq(password)))
                .fetchOne();

        return result;
    }

    @Override
    public List<AccountRowModelDto> findAllAccountsWithPersonData() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAccount account = QAccount.account;
        QPerson person = QPerson.person;

        List<AccountRowModelDto> result = (List<AccountRowModelDto>) query.select(Projections.constructor(AccountRowModelDto.class, account.id, account.uniqueId, account.email, account.login, account.accountType, account.passwordExpiredDate, person.firstName, person.secondName, person.surname, account.creationDate, account.registrationConfirmationIndicator, account.activityIndicator))
                .from(account)
                .leftJoin(person)
                .on(account.person.id.eq(person.id))
                .fetch();

        return result;
    }

    @Override
    public Boolean checkEmailAvailability(String mail){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAccount account = QAccount.account;

        String result = query.select(account.email)
                .from(account)
                .where(account.email.eq(mail))
                .fetchFirst();

        if(StringUtil.isEmpty(result)){
            return Boolean.TRUE;
        }
        return  Boolean.FALSE;
    }

    @Override
    public Boolean checkUsernameAvailability(String username){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAccount account = QAccount.account;

        String result = query.select(account.login)
                .from(account)
                .where(account.login.eq(username))
                .fetchFirst();

        if(StringUtil.isEmpty(result)){
            return Boolean.TRUE;
        }
        return  Boolean.FALSE;
    }

    @Override
    public String getNextAccountUniqueId() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QAccount account = QAccount.account;

        List<String> accountUniqueIds = query.select(account.uniqueId)
                .from(account)
                .fetch();

        Long value = 0L;

        if(!accountUniqueIds.isEmpty()){
            String lastOwnerUniqueId = accountUniqueIds.get(accountUniqueIds.size()-1);
            value = Long.valueOf(lastOwnerUniqueId.substring(2));
            value++;
        }

        String result = "C-";
        for(int i=0;i<8-value.toString().length();i++){
            result=result+"0";
        }
        result = result + value.toString();
        return result;
    }
}
