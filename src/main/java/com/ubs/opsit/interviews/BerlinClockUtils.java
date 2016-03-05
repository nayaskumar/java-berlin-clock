package com.ubs.opsit.interviews;

import java.util.StringTokenizer;

/**
 * Created by satish on 05-03-2016.
 */
public final class BerlinClockUtils {

    public static int[] parseTime(String aTime) {
        StringTokenizer token = new StringTokenizer(aTime, ":");
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        while (token.hasMoreElements()) {
            hours = Integer.parseInt((String)token.nextElement());
            minutes = Integer.parseInt((String)token.nextElement());
            seconds = Integer.parseInt((String)token.nextElement());
        }
        return new int[]{hours, minutes, seconds};
    }

    public static void appendControlCharacters(StringBuilder temp){
        temp.append("\r");
        temp.append("\n");
    }
}


