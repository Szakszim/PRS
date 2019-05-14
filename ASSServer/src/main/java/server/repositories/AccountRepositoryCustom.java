package server.repositories;

import dtos.rowmodels.AccountRowModelDto;
import entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositoryCustom {

    Account findAccountByCredentials(String login, String password);

    List<AccountRowModelDto> findAllAccountsWithPersonData();

    Boolean checkEmailAvailability(String mail);

    Boolean checkUsernameAvailability(String username);

    String getNextAccountUniqueId();
}
