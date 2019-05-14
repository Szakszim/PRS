package server.repositories;

import dtos.rowmodels.AccountRowModelDto;
import dtos.rowmodels.PurchaseRowModelDto;
import entities.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepositoryCustom {

//    Account findAccountByCredentials(String login, String password);
//
//    List<AccountRowModelDto> findAllAccountsWithPersonData();
//
//    Boolean checkEmailAvailability(String mail);
//
//    Boolean checkUsernameAvailability(String username);
public List<PurchaseRowModelDto> findAllPurchasesWithPurchaserAndFarmData();
}
