package server.repositories;

import entities.Person;

public interface PersonRepositoryCustom {

    public Person findPersonByAccountId(Long accountId);
}
