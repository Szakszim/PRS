package dtos.base;

import entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryDto {
    private Long id;
    private DictionaryType dictionaryType;
    private String businessKey;
    private String symbol;
    private String value;
    private String description;

    public DictionaryDto(Dictionary dictionary) {
        this.id = dictionary.getId();
        this.dictionaryType = dictionary.getDictionaryType();
        this.businessKey = dictionary.getBusinessKey();
        this.symbol = dictionary.getSymbol();
        this.value = dictionary.getValue();
        this.description = dictionary.getDescription();
    }

    public Dictionary toEntity() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(this.id);
        dictionary.setDictionaryType(this.dictionaryType);
        dictionary.setBusinessKey(this.businessKey);
        dictionary.setSymbol(this.symbol);
        dictionary.setValue(this.value);
        dictionary.setDescription(this.description);

        return dictionary;
    }
}
