package enums;

import helpers.LangHelper;
import lombok.Getter;

public enum PossessionStateType {
    OWNERSHIP('O'),
    LEASE('L');

    @Getter
    Character value;

    PossessionStateType(Character value) {
        this.value = value;
    }

    public String toString(){
        return LangHelper.getLang("common.possession_state_type.enum_"+this.value.toString().toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

//    public Character toString(){
//        return this.symbol;
//    }
}
