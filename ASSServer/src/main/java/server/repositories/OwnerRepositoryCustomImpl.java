package server.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Dictionary;
import entities.QDictionary;
import entities.QDictionaryType;
import entities.QOwner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class OwnerRepositoryCustomImpl implements OwnerRepositoryCustom {

    @Inject
    private EntityManager em;

    @Override
    public String getNextOwnerUniqueId() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QOwner owner = QOwner.owner;

        List<String> ownerUniqueIds = query.select(owner.uniqueId)
                .from(owner)
                .fetch();

        Long value = 0L;

        if(!ownerUniqueIds.isEmpty()){
            String lastOwnerUniqueId = ownerUniqueIds.get(ownerUniqueIds.size()-1);
            value = Long.valueOf(lastOwnerUniqueId.substring(2));
            value++;
        }

        String result = "O-";
        for(int i=0;i<8-value.toString().length();i++){
            result=result+"0";
        }
        result = result + value.toString();
        return result;
    }
}
