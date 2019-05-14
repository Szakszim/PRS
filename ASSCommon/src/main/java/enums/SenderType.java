package enums;

import helpers.LangHelper;
import lombok.Getter;

public enum SenderType {
    SYSTEM('S'),
    ADMINISTRATOR('A'),
    MANAGER('M'),
    WORKER('W');

    @Getter
    Character value;

    SenderType(Character value) {
        this.value = value;
    }

    public String toString(){
        return LangHelper.getLang("registration_screen.sender_type.enum_"+this.value.toString().toLowerCase());
//return "%registration_screen.header";
        //TODO ADD RESOURCE BUNDLE FOR LANG HERE
    }

//    public Character toString(){
//        return this.symbol;
//    }
}
