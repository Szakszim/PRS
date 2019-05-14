package server.repositories;

import entities.Account;
import entities.CattlePassport;
import org.springframework.stereotype.Repository;

@Repository
public interface CattlePassportRepositoryCustom {

    CattlePassport findPassportByCattleId(Long cattleId);
}
