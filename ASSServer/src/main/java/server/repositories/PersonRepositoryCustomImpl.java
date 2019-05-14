package server.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Account;
import entities.Person;
import entities.QAccount;
import entities.QPerson;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public Person findPersonByAccountId(Long accountId) {
        JPAQueryFactory query = new JPAQueryFactory(em);
//
        QPerson person = QPerson.person;
        QAccount account = QAccount.account;
//
        Person result = (Person) query
                .from(person)
                .leftJoin(account)
                .on(person.id.eq(account.person.id))
                .where(account.id.eq(accountId))
                .fetchOne();

        return result;
    }
}
