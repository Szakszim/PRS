package helpers;

import java.util.Locale;
import java.util.ResourceBundle;

public class LangHelper {

    public static String getLang(String key) {
        if (key == null)
            return null;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", getCurrentLanguage());
        if (resourceBundle.containsKey(key))
            return resourceBundle.getString(key);
        else
            return key;
    }

    public static String getLang(String key, Locale locale) {
        if (key == null)
            return null;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale);
        if (resourceBundle.containsKey(key))
            return resourceBundle.getString(key);
        else
            return key;
    }

    public static Locale getCurrentLanguage() {
        return Locale.getDefault();
    }
}
