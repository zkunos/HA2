package edu.corvinus.ha2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helpers {

    public static String getDayText() {
        return new SimpleDateFormat("EEEE").format(new Date()).toLowerCase();
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy.MM.dd").format(new Date());
    }
}
