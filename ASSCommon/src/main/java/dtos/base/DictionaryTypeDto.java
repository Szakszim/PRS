package dtos.base;

import entities.Dictionary;
import entities.DictionaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryTypeDto {
    private Long id;
    private String businessKey;
    private String value;
    private String description;

    public DictionaryTypeDto(DictionaryType dictionaryType) {
        this.id = dictionaryType.getId();
        this.businessKey = dictionaryType.getBusinessKey();
        this.value = dictionaryType.getValue();
        this.description = dictionaryType.getDescription();
    }

    public DictionaryType toEntity() {
        DictionaryType dictionaryType = new DictionaryType();
        dictionaryType.setId(this.id);
        dictionaryType.setBusinessKey(this.businessKey);
        dictionaryType.setValue(this.value);
        dictionaryType.setDescription(this.description);

        return dictionaryType;
    }
}
