package UniverseDBValidation;

import org.apache.commons.text.WordUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String WEB_DATE_FORMAT = "dd MMM yyyy";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
    private static final DateTimeFormatter WEB_DATE_FORMATTER = DateTimeFormatter.ofPattern(WEB_DATE_FORMAT);

    public static Double roundOff(double number, double decimalPlaces){
        double factor = Math.pow(10.0, decimalPlaces);
        return Math.round(number * factor) / factor;
    }

    public static String getEmptyStringAsNull(String val) {
        if (val == null || val.trim().equals(""))
            return null;

        return val;
    }

    public static String getFirstLetterInUpperCase(String val) {
        return WordUtils.capitalizeFully(val);
    }

    public static Integer getNullableInteger(String val) {
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double getNullableDouble(String val) {
        try {
            return Double.parseDouble(val);
        } catch (Exception e) {
            return null;
        }
    }

    public static double getDoubleFromU2(String value) {
        double v;

        if (value == null || value.trim().length() == 0)
            return 0.0;

        try {

            v = Double.parseDouble(value);
            v = v / 100.0;
            return Math.round(v * 100.0) / 100.0;

        } catch (Exception exception) {
            return 0.0;
        }
    }

    public static LocalDateTime getLocalDateFromU2(String date) {
        int x;
        if (date == null || date.trim().length() == 0)
            return null;

        try {
            x = Integer.parseInt(date);
            return LocalDateTime.of(1967, 12, 31, 0, 0, 0).plusDays(x);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime getLocalDateTimeFromU2(String date, String time) {
        int x;
        double y;
        int iMilliSecs, iSecs;
        if (date == null || date.trim().length() == 0)
            return null;

        try {
            x = Integer.parseInt(date);
            y = Double.parseDouble(time);
            iSecs = (int) y;
            y = y - iSecs;
            y = y * 1000;
            iMilliSecs = (int) y;
            return LocalDateTime.of(1967, 12, 31, 0, 0, 0)
                    .plusDays(x)
                    .plusSeconds(iSecs)
                    .plusNanos(iMilliSecs * 1000000);
        } catch (Exception e) {
            return null;
        }
    }

    public static String concatStrings(String[] values, int start, int end){
        StringBuilder sb = new StringBuilder();
        for(int i=start;i<end;i++){
            sb.append(values[i]);
        }
        return sb.toString();
    }

    public static String[] getMultiValue(String val, int max, String delimiter){
        String[] values = val.split(delimiter);

        if(values.length >= max)
            return values;

        String[] allValues = new String[(max - values.length) + values.length];
        int i=0;

        for(;i< values.length;i++)
            allValues[i] = values[i];

        for(;i< allValues.length;i++)
            allValues[i] = "";

        return allValues;
    }

    public static LocalDateTime getArrayIndexSafeLocalDateFromU2(String[] strings, int index){
        if(strings != null && index <= strings.length-1)
            return getLocalDateFromU2(strings[index]);
        return null;
    }

    public static Double getArrayIndexSafeU2Double(String[] strings, int index){
        if(strings != null && index <= strings.length-1)
            return getDoubleFromU2(strings[index]);
        return 0.0;
    }

    public static String getArrayIndexSafeString(String[] strings, int index){
        if(strings != null && index <= strings.length-1)
            return getEmptyStringAsNull(strings[index]);
        return null;
    }

    public static String formatDate(LocalDateTime dateTime){
        try { return dateTime.format(DATE_FORMATTER); } catch (Exception e){ return null; }
    }

    public static String formatTime(LocalDateTime dateTime){
        try { return dateTime.format(TIME_FORMATTER); } catch (Exception e){ return null; }
    }
}
