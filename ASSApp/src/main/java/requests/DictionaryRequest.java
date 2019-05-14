package requests;

import entities.Dictionary;

import java.util.List;

public abstract class DictionaryRequest {
    public abstract Dictionary findByBusinessCode(String businessCode);

    public abstract List<Dictionary> getDictionary();
}
