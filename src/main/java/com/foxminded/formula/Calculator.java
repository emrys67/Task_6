package com.foxminded.formula;

import java.time.LocalTime;

public class Calculator {
    private final static String UNDERSCORE = "_";
    private final static String NULL_EXCEPTION = "Null input is not allowed";

    public Racers fillInRacerInfo(Racers racers) {
        if (racers == null) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }
        fillAbrIn(racers);
        fillStartTimeIn(racers);
        fillEndTimeIn(racers);
        fillInBestLap(racers);
        return racers;
    }

    private void fillAbrIn(Racers racers) {
        racers.getAbbriviations().stream()
                .forEach(racer -> racers.addRacer(new Racer(racer.substring(0, racer.indexOf(UNDERSCORE))
                        , racer.substring(racer.indexOf(UNDERSCORE) + 1, racer.indexOf(UNDERSCORE, racer.indexOf(UNDERSCORE) + 1))
                        , racer.substring((racer.indexOf(UNDERSCORE, racer.indexOf(UNDERSCORE) + 1) + 1)))));

    }

    private void fillStartTimeIn(Racers racers) {
        racers.getStart().stream()
                .forEach(racer ->
                        racers.getRacerByAbr(racer.substring(0, 3)).setStartTime(LocalTime.parse(racer.substring(
                                racer.indexOf(UNDERSCORE) + 1))));

    }

    private void fillEndTimeIn(Racers racers) {
        racers.getEnd().stream()
                .forEach(racer ->
                        racers.getRacerByAbr(racer.substring(0, 3)).setEndTime(LocalTime.parse(racer.substring(
                                racer.indexOf(UNDERSCORE) + 1))));
    }

    private void fillInBestLap(Racers racers) {
        racers.getRacersList().stream()
                .forEach(racer -> racer.setLapTime(racer.getEndTime().minusNanos(racer.getStartTime().getNano())
                        .minusSeconds(racer.getStartTime().getSecond())
                        .minusMinutes(racer.getStartTime().getMinute()).minusHours(racer.getStartTime().getHour())));
    }
}
