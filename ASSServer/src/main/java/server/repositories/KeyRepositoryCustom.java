package server.repositories;

import dtos.other.ModulePrivilegeDto;
import dtos.rowmodels.KeyAssignmentRowModelDto;
import dtos.rowmodels.KeyRowModelDto;
import entities.Key;

import java.util.List;

public interface KeyRepositoryCustom {

    ModulePrivilegeDto getModulePrivilegesByFarmId(Long farmId);

    List<KeyRowModelDto> findAllKeys();

    Key getFirstFreeKey();
}
