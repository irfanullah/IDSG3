package UniverseDBValidation;

import org.apache.commons.text.WordUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public class DataHelper {

    public static String getEmptyStringAsNull(String val){
        if(Objects.isNull(val) || val.equals(""))
            return null;
        return val;
    }

    public static String getFirstLetterInUpperCase(String val){
        return WordUtils.capitalizeFully(val);
    }

    public static Integer getNullableInteger(String val){
        try{ return Integer.parseInt(val);  } catch (Exception e){ return null; }
    }

    public static Double getNullableDouble(String val){
        try{ return Double.parseDouble(val); } catch (Exception e){ return null; }
    }

    public static double getDoubleFromU2(String value) {
        double v;

        if (Objects.isNull(value) || value.trim().length() == 0 )
            return 0.0;

        try{
            v = Double.parseDouble(value);
            v = v / 100.0;
            return Math.round(v * 100.0) / 100.0;
        }catch (Exception exception){
            return 0.0;
        }
    }

    public static LocalDateTime getLocalDateFromU2(String date){
        int x;
        if (Objects.isNull(date) || date.trim().length() == 0)
            return null;

        try{
            x = Integer.parseInt(date);
           return LocalDateTime.of(1967,12,31,0,0,0).plusDays(x);
        }catch (Exception e){
            return null;
        }
    }

    public static LocalDateTime getLocalDateTimeFromU2(String date, String time){
        int x;
        double y;
        int iMilliSecs, iSecs;
        if (Objects.isNull(date) || date.trim().length() == 0)
            return null;

        try{
            x = Integer.parseInt(date);
            y = Double.parseDouble(time);
            iSecs = (int)y;
            y = y-iSecs;
            y = y * 1000;
            iMilliSecs = (int)y;
            return LocalDateTime.of(1967,12,31,0,0,0)
                    .plusDays(x)
                    .plusSeconds(iSecs)
                    .plusNanos(iMilliSecs*1000000);
        }catch (Exception e){
            return null;
        }
    }

}
