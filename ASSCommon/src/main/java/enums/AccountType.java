package enums;

import helpers.LangHelper;

public enum AccountType {
    ADMINISTRATOR('A'),
    CUSTOMER('C');

    Character symbol;

    AccountType(Character sign) {
        this.symbol = sign;
    }

    public String toString(){
        return LangHelper.getLang("registration_screen.account_type.enum_"+this.symbol.toString().toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

//    public Character toString(){
//        return this.symbol;
//    }
}
