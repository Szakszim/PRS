package server.repositories;

import entities.Account;
import entities.CattlePassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CattlePassportRepository extends JpaRepository<CattlePassport,Long>, CattlePassportRepositoryCustom{
}
