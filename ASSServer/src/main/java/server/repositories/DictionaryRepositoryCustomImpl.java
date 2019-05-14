package server.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dtos.other.AnimalToDeleteDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DictionaryRepositoryCustomImpl implements DictionaryRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public List<Dictionary> getDictionary(String dictionaryBusinessKey) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QDictionaryType dictionaryType = QDictionaryType.dictionaryType;
        QDictionary dictionary = QDictionary.dictionary;

        List<Dictionary> result = query.select(dictionary)
                .from(dictionary)
                .leftJoin(dictionaryType)
                .on(dictionary.dictionaryType.id.eq(dictionaryType.id))
                .where(dictionaryType.businessKey.eq(dictionaryBusinessKey))
                .fetch();

        return result;
    }
}
