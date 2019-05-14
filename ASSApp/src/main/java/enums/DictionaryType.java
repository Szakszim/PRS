package enums;

import lombok.Getter;

public enum DictionaryType {
    STREET_TYPE("000"),
    AREA_MESUREMENT_UNIT("001");

    @Getter
    String businessKey;

    DictionaryType(String businessKey) {
        this.businessKey = businessKey;
    }
}
