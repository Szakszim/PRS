package enums;

import helpers.LangHelper;
import lombok.Getter;

public enum ModuleType {
    PLANTS("PLA"),
    ANIMALS("ANI"),
    MACHINERY("MAC"),
    ACCOUNTANT("ACC");

    @Getter
    String value;

    ModuleType(String value) {
        this.value = value;
    }

    public String toString(){
        return LangHelper.getLang("common.module_type.enum_"+this.value.toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

//    public Character toString(){
//        return this.symbol;
//    }
}
