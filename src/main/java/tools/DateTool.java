package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateTool {

    public String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        String currentDate = simpleDateFormat.format(calendar.getTime()).toString();
        return currentDate;

    }

    public String getDate(int unit, int increment) {

        Calendar date = Calendar.getInstance();
        if (unit > 0) {
            date.add(unit, increment);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("CST+0"));
        String stringDate = sdf.format(date.getTime());
        return stringDate;

    }

}
