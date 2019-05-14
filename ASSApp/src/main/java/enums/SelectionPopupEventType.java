package enums;

import lombok.Getter;

public enum SelectionPopupEventType {
    SELECT(0),
    CREATE(1),
    CLOSE(2);

    @Getter
    Integer value;

    SelectionPopupEventType(Integer value) {
        this.value = value;
    }
}
