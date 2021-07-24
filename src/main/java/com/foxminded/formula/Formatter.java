package com.foxminded.formula;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Formatter {
    private static final String SPACE = " ";
    private static final String VERTICAL_BAR = "|";
    private static final String NEW_LINE = "\n";
    private static final String EQUAL_SIGN = "=";
    private static final String DOT = ".";
    private static final String MAX_CAR_LENGTH_EXCEPTION = "Problem with finding max car name length";
    private static final String MAX_NAME_LENGTH_EXCEPTION = "Problem with finding max name length";
    private final List<String> list = new ArrayList<>();
    private int counter = 1;
    private int nameLength;
    private int carLength;

    public String getRacersFormatted(Racers racers) {
        sortByBestLap(racers);
        setMaxLengths(racers);
        return formatRacers(racers);
    }

    private void sortByBestLap(Racers racers) {
        racers.setRacersList(racers.getRacersList().stream()
                .sorted(Comparator.comparing(Racer::getLapTime))
                .collect(Collectors.toList()));
    }

    private void setMaxLengths(Racers racers) {
        var name = racers.getRacersList().stream()
                .max(Comparator.comparingInt(racer -> racer.getName().length()));
        if (name.isPresent()) {
            nameLength = name.get().getName().length() + 1 + String.valueOf(racers.getRacersList().size()).length();
        } else
            throw new RuntimeException(MAX_NAME_LENGTH_EXCEPTION);
        var car = racers.getRacersList().stream()
                .max(Comparator.comparingInt(racer -> racer.getCar().length()));
        if (car.isPresent()) {
            carLength = car.get().getCar().length();
        } else
            throw new RuntimeException(MAX_CAR_LENGTH_EXCEPTION);
    }

    private String formatRacers(Racers racers) {
        racers.getRacersList().stream()
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
