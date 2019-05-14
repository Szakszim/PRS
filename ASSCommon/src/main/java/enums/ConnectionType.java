package enums;

import helpers.LangHelper;

public enum ConnectionType {
    MANAGER('M'),
    WORKER('W');

    Character symbol;

    ConnectionType(Character sign) {
        this.symbol = sign;
    }

    public String toString(){
        return LangHelper.getLang("enum.connection_type."+this.symbol.toString().toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

//    public Character toString(){
//        return this.symbol;
//    }
}
