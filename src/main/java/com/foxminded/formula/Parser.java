package com.foxminded.formula;

import java.time.LocalTime;

public class Parser {
    private static final String UNDERSCORE = "_";
    private static final String NULL_EXCEPTION = "Null input is not allowed";
    private Racers racers;

    public Racers fillInRacerInfo(RacersInfo racersInfo) {
        if (racersInfo == null) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }
        racers = new Racers();
        fillAbrIn(racersInfo);
        fillStartTimeIn(racersInfo);
        fillEndTimeIn(racersInfo);
        fillInBestLap();
        return racers;
    }

    private void fillAbrIn(RacersInfo racersInfo) {
        racersInfo.getAbbreviations().stream()
                .forEach(racer -> racers.addRacer(new Racer(racer.substring(0, racer.indexOf(UNDERSCORE))
                        , racer.substring(racer.indexOf(UNDERSCORE) + 1, racer.indexOf(UNDERSCORE, racer.indexOf(UNDERSCORE) + 1))
                        , racer.substring((racer.indexOf(UNDERSCORE, racer.indexOf(UNDERSCORE) + 1) + 1)))));

    }

    private void fillStartTimeIn(RacersInfo racersInfo) {
        racersInfo.getStart().stream()
                .forEach(racer ->
                        racers.getRacerByAbr(racer.substring(0, 3)).setStartTime(LocalTime.parse(racer.substring(
                                racer.indexOf(UNDERSCORE) + 1))));

    }

    private void fillEndTimeIn(RacersInfo racersInfo) {
        racersInfo.getEnd().stream()
                .forEach(racer ->
                        racers.getRacerByAbr(racer.substring(0, 3)).setEndTime(LocalTime.parse(racer.substring(
                                racer.indexOf(UNDERSCORE) + 1))));
    }

    private void fillInBestLap() {
        racers.getRacersList().stream()
                .forEach(racer -> racer.setLapTime(racer.getEndTime().minusNanos(racer.getStartTime().getNano())
                        .minusSeconds(racer.getStartTime().getSecond())
                        .minusMinutes(racer.getStartTime().getMinute()).minusHours(racer.getStartTime().getHour())));
    }
}
