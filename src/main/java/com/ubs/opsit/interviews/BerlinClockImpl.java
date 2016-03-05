package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by satish on 04-03-2016.
 */
public class BerlinClockImpl implements TimeConverter{


    /**
     * Converts time from HH-MM-SS format into Berlin Clock format
     *
     * @param aTime time in format HH-MM-SS
     * @return Berlin Clock format
     */
    @Override
    public String convertTime(String aTime) {
        int[] time = BerlinClockUtils.parseTime(aTime);
        return printBerlinClock(time).toString();
    }

    private StringBuilder printBerlinClock(int[] time){
        boolean secondsLampBink = getSwitchOnSecondsLamps(time);
        int[] hoursMinutesLampsOn = getSwitchOnHoursMinutesLamps(time);
        return produceBerlinClock(secondsLampBink,hoursMinutesLampsOn);
    }

    private boolean getSwitchOnSecondsLamps(int[] time){
        int seconds = time[2];
        boolean secondsLamp = seconds % 2 == 0;
        return secondsLamp;
    }

    private int[] getSwitchOnHoursMinutesLamps(int[] time){
        int hours = time[0];
        int minutes = time[1];

        int hoursTopRow = hours / 5;
        int hoursSecondRow = hours % 5;
        int minutesTopRow = minutes / 5;
        int minutesSecondRow = minutes % 5;

        return new int[] {hoursTopRow,hoursSecondRow,minutesTopRow,minutesSecondRow};
    }



    private StringBuilder produceBerlinClock(boolean secondBlink, int[] switchOnLamps) {
        final StringBuilder berlinClock = new StringBuilder();

        BerlinClockUtils.appendControlCharacters(berlinClock);
        berlinClock.append(secondBlink ? BerlinClockConstants.SECOND_ON : BerlinClockConstants.LAMP_OFF);
        BerlinClockUtils.appendControlCharacters(berlinClock);

        int hoursTopRow = switchOnLamps[0];
        produceBerlinClockRow(berlinClock, BerlinClockConstants.HOURS_ON_FORMAT, hoursTopRow);
        BerlinClockUtils.appendControlCharacters(berlinClock);

        int hoursSecondRow = switchOnLamps[1];
        produceBerlinClockRow(berlinClock, BerlinClockConstants.HOURS_ON_FORMAT, hoursSecondRow);
        BerlinClockUtils.appendControlCharacters(berlinClock);

        int minutesTopRow = switchOnLamps[2];
        produceBerlinClockRow(berlinClock, BerlinClockConstants.MINUTES_TOP_ROW_ON_FORMAT, minutesTopRow);
        BerlinClockUtils.appendControlCharacters(berlinClock);

        int minutesSecondRow = switchOnLamps[3];
        produceBerlinClockRow(berlinClock, BerlinClockConstants.MINUTES_BOTTOM_ROW_ON_FORMAT, minutesSecondRow);

        return berlinClock;
    }

    private void produceBerlinClockRow(StringBuilder berlinClock, String LampFormat, int OnLamps) {
        char[] row = LampFormat.toCharArray();
        Arrays.fill(row, OnLamps, LampFormat.length(), BerlinClockConstants.LAMP_OFF);
        berlinClock.append(row);
    }

}

