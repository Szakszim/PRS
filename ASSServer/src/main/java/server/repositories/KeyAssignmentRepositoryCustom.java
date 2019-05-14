package server.repositories;

import dtos.other.ModulePrivilegeDto;
import dtos.rowmodels.KeyAssignmentRowModelDto;

import java.util.List;

public interface KeyAssignmentRepositoryCustom {

//    ModulePrivilegeDto getModulePrivilegesByFarmId(Long farmId);

    List<KeyAssignmentRowModelDto> findAllKeysWithFarmAndPurchaserData();
}
