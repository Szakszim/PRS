package requests;

import entities.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class CurrencyTypeRequest extends DictionaryRequest{
    @Override
    public Dictionary findByBusinessCode(String businessCode){
 return null;
    }

    @Override
    public List<Dictionary> getDictionary(){
        return new ArrayList<>();
    }
}
