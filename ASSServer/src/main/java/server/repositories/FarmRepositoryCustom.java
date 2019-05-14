package server.repositories;

import dtos.rowmodels.FarmRowModelDto;
import entities.Farm;
import entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepositoryCustom {
    public Farm findFarmByAccountId(Long accountId);

    List<FarmRowModelDto> findAllFarmsWithOwnerData();
}
