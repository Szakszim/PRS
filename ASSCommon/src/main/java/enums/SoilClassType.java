package enums;

import helpers.LangHelper;
import lombok.Getter;

public enum SoilClassType {
    FIRST(0),
    SECOND(1),
    THIRD_A(2),
    THIRD_B(3),
    FOURTH_A(4),
    FOURTH_B(5),
    FIFTH(6),
    SIXTH(7),
    SIXTH_RZ(8);

    @Getter
    Integer value;

    SoilClassType(Integer value) {
        this.value = value;
    }

    public String toString(){
        return LangHelper.getLang("common.soil_class_type.enum_"+this.value.toString().toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

//    public Character toString(){
//        return this.symbol;
//    }
}
