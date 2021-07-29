package com.foxminded.formula.format;

import com.foxminded.formula.models.Racer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopRacersFormatter implements Formatter {
    private static final String SPACE = " ";
    private static final String VERTICAL_BAR = "|";
    private static final String NEW_LINE = "\n";
    private static final String EQUAL_SIGN = "=";
    private static final String DOT = ".";
    private static final String MAX_CAR_LENGTH_EXCEPTION = "Problem with finding max car name length";
    private static final String MAX_NAME_LENGTH_EXCEPTION = "Problem with finding max name length";
    private static final String NULL_EXCEPTION = "Null input is not allowed";
    private List<String> list;
    private int counter;
    private int nameLength;
    private int carLength;

    public String getRacersFormatted(List<Racer> racers) {
        if (racers == null) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }
        if (racers.stream().anyMatch(racer -> racer.getAbr() == null || racer.getStartTime() == null ||
                racer.getEndTime() == null || racer.getLapTime() == null)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }
        list = new ArrayList<>();
        counter = 1;
        setMaxLengths(racers);
        return formatRacers(sortByBestLap(racers));
    }

    private List<Racer> sortByBestLap(List<Racer> racers) {
        return racers.stream()
                .sorted(Comparator.comparing(Racer::getLapTime))
                .collect(Collectors.toList());
    }

    private void setMaxLengths(List<Racer> racers) {
        var name = racers.stream()
                .max(Comparator.comparingInt(racer -> racer.getName().length()));
        if (name.isPresent()) {
            nameLength = name.get().getName().length() + 1 + String.valueOf(racers.size()).length();
        } else
            throw new RuntimeException(MAX_NAME_LENGTH_EXCEPTION);
        var car = racers.stream()
                .max(Comparator.comparingInt(racer -> racer.getCar().length()));
        if (car.isPresent()) {
            carLength = car.get().getCar().length();
        } else
            throw new RuntimeException(MAX_CAR_LENGTH_EXCEPTION);
    }

    private String formatRacers(List<Racer> racers) {
        racers.stream()
                .forEach(racer -> list.add(counter + DOT + racer.getName() + StringUtils.repeat(SPACE, nameLength - racer.getName().length() -
                        String.valueOf(counter++).length()) + VERTICAL_BAR
                        + SPACE + racer.getCar() + StringUtils.repeat(SPACE, carLength - racer.getCar().length() + 1) + SPACE + VERTICAL_BAR + SPACE
                        + racer.getLapTime() + NEW_LINE));
        list.add(15, StringUtils.repeat(EQUAL_SIGN, list.get(0).length() - 1) + NEW_LINE);
        var toReturn = new StringBuilder();
        list.stream()
                .forEach(toReturn::append);
        return toReturn.toString();
    }
}
