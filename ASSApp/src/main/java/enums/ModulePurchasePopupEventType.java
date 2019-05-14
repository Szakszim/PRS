package enums;

import lombok.Getter;

public enum ModulePurchasePopupEventType {
    PURCHASE(0),
    ENTER(1),
    CLOSE(2);

    @Getter
    Integer value;

    ModulePurchasePopupEventType(Integer value) {
        this.value = value;
    }
}
