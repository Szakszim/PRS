package enums;

import helpers.LangHelper;

public enum OwnerType {
    INDYVIDUAL('I'),
    CONCERN('C');

    Character symbol;

    OwnerType(Character sign) {
        this.symbol = sign;
    }

    public String toString(){
        return LangHelper.getLang("registration_screen.owner_type.enum_"+this.symbol.toString().toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

//    public Character toString(){
//        return this.symbol;
//    }
}
