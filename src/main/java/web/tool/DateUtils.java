package web.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getDate(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }
}
