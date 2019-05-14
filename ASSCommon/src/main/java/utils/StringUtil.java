package utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {

    public static Boolean isEmpty(String text){
        if(text !=null && !text.isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static String joinStringsWithDelimiter(String delimiter, String... string){

        return Arrays.stream(string).collect(Collectors.joining(delimiter));
    }

    public static String joinStringsWithoutDelimiter(String... string){

        return Arrays.stream(string).collect(Collectors.joining());
    }
}
