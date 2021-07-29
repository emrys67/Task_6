package com.foxminded.formula.models;

import java.util.ArrayList;
import java.util.List;

public class RacersInfo {
    private List<String> start;
    private List<String> end;
    private List<String> abbreviations;

    public RacersInfo() {
        start = new ArrayList<>();
        end = new ArrayList<>();
        abbreviations = new ArrayList<>();
    }

    public List<String> getStart() {
        return start;
    }

    public void setStart(List<String> start) {
        this.start = start;
    }

    public List<String> getEnd() {
        return end;
    }

    public void setEnd(List<String> end) {
        this.end = end;
    }

    public List<String> getAbbreviations() {
        return abbreviations;
    }

    public void setAbbreviations(List<String> abbreviations) {
        this.abbreviations = abbreviations;
    }
}
