package enums;

import helpers.LangHelper;

public enum SexType {
    MAN("XY"),
    WOMAN("XX");

    String value;

    SexType(String value) {
        this.value = value;
    }

    public String toString(){
        return LangHelper.getLang("common.sex_type.enum_"+this.value.toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

}
