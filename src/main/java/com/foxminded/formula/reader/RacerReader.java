package com.foxminded.formula.reader;

import com.foxminded.formula.models.RacersInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class RacerReader implements Reader {
    private static final String START_LOG = "start";
    private static final String END_LOG = "end";
    private static final String ABR_LOG = "abbreviations";
    private static final String ARG_EXCEPTION = "It is not a log file";

    public RacersInfo readFromFile(String filepath, RacersInfo racersInfo) {
        if (!new File(filepath).isFile() || !new File(filepath).exists()) {
            throw new IllegalArgumentException("Path is wrong");
        }
        if (filepath.contains(START_LOG)) {
            readLinesFormFile(filepath, racersInfo.getStart());
        } else if (filepath.contains(END_LOG)) {
            readLinesFormFile(filepath, racersInfo.getEnd());
        } else if (filepath.contains(ABR_LOG)) {
            readLinesFormFile(filepath, racersInfo.getAbbreviations());
        } else {
            throw new IllegalArgumentException(ARG_EXCEPTION);
        }
        return racersInfo;
    }

    private void readLinesFormFile(String pathString, List<String> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathString))) {
            while (reader.ready()) {
                list.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
