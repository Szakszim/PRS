package server.repositories;

import dtos.other.BasicFieldDataDto;
import entities.FieldCoordinate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepositoryCustom {

    BasicFieldDataDto getBasicFieldDataByFieldId(Long fieldId);

}
