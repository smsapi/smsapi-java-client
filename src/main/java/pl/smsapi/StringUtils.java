package pl.smsapi;

/**
 * Created by c on 14/05/14.
 */
public class StringUtils {

    public static java.lang.String join(java.lang.String array[], char separator) {
        if (array.length == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for (i = 0; i < array.length - 1; i++) {
            stringBuilder.append(array[i]).append(separator);
        }

        return stringBuilder.toString() + array[i];
    }
}
