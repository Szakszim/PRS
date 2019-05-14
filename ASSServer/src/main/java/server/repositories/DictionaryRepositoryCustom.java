package server.repositories;

import dtos.other.AnimalToDeleteDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepositoryCustom {

    List<Dictionary> getDictionary(String dictionaryBusinessKey);

}
