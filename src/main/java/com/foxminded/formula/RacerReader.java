package com.foxminded.formula;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RacerReader {
    private final List<String> start = new ArrayList<>();
    private final List<String> end = new ArrayList<>();
    private final List<String> abrv = new ArrayList<>();
    private final Racers racers = new Racers();

    public Racers returnRacers(String abr, String start, String end) {
        readStart(start);
        readEnd(end);
        readAbr(abr);
        return racers;
    }

    private void readStart(String pathString) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathString))) {
            while (reader.ready()) {
                start.add(reader.readLine());
            }
            racers.setStart(start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readEnd(String pathString) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathString))) {
            while (reader.ready()) {
                end.add(reader.readLine());
            }
            racers.setEnd(end);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAbr(String pathString) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathString))) {
            while (reader.ready()) {
                abrv.add(reader.readLine());
            }
            racers.setAbriviations(abrv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
