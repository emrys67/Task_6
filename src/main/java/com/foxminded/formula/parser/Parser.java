package com.foxminded.formula.parser;

import com.foxminded.formula.models.Racer;
import com.foxminded.formula.models.RacersInfo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String UNDERSCORE = "_";
    private static final String NULL_EXCEPTION = "Null input is not allowed";
    private static final String GET_RACER_ABR_EXCEPTION = "Abriviation getting error";
    private final List<Racer> racersList = new ArrayList<>();

    public List<Racer> fillInRacerInfo(RacersInfo racersInfo) {
        if (racersInfo == null) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }
        if (racersInfo.getAbbreviations().isEmpty() ||
                racersInfo.getStart().isEmpty() ||
                racersInfo.getEnd().isEmpty()) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }
        fillAbrIn(racersInfo);
        fillStartTimeIn(racersInfo);
        fillEndTimeIn(racersInfo);
        fillInBestLap();
        return racersList;
    }

    private void fillAbrIn(RacersInfo racersInfo) {
        racersInfo.getAbbreviations().stream()
                .forEach(racer -> racersList.add(new Racer(racer.substring(0, racer.indexOf(UNDERSCORE))
                        , racer.substring(racer.indexOf(UNDERSCORE) + 1, racer.indexOf(UNDERSCORE, racer.indexOf(UNDERSCORE) + 1))
                        , racer.substring((racer.indexOf(UNDERSCORE, racer.indexOf(UNDERSCORE) + 1) + 1)))));

    }

    private void fillStartTimeIn(RacersInfo racersInfo) {
        racersInfo.getStart().stream()
                .forEach(racer ->
                        getRacerByAbr(racer.substring(0, 3)).setStartTime(LocalTime.parse(racer.substring(
                                racer.indexOf(UNDERSCORE) + 1))));

    }

    private void fillEndTimeIn(RacersInfo racersInfo) {
        racersInfo.getEnd().stream()
                .forEach(racer ->
                        getRacerByAbr(racer.substring(0, 3)).setEndTime(LocalTime.parse(racer.substring(
                                racer.indexOf(UNDERSCORE) + 1))));
    }

    private void fillInBestLap() {
        racersList.stream()
                .forEach(racer -> racer.setLapTime(racer.getEndTime().minusNanos(racer.getStartTime().getNano())
                        .minusSeconds(racer.getStartTime().getSecond())
                        .minusMinutes(racer.getStartTime().getMinute()).minusHours(racer.getStartTime().getHour())));
    }

    private Racer getRacerByAbr(String abr) {
        var racer = racersList.stream()
                .filter(abrv -> abrv.getAbr().equals(abr))
                .findFirst();
        if (racer.isPresent()) {
            return racer.get();
        } else {
            throw new IllegalArgumentException(GET_RACER_ABR_EXCEPTION);
        }
    }
}
