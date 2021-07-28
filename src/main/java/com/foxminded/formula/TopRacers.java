package com.foxminded.formula;

import com.foxminded.formula.format.TopRacersFormatter;
import com.foxminded.formula.models.RacersInfo;
import com.foxminded.formula.parser.Parser;
import com.foxminded.formula.reader.RacerReader;

public class TopRacers {
    private Parser parser;
    private TopRacersFormatter formatter;
    private RacerReader reader;
    private RacersInfo racersInfo;

    public TopRacers(Parser parser, TopRacersFormatter formatter, RacerReader reader) {
        this.parser = parser;
        this.formatter = formatter;
        this.reader = reader;
    }

    public String getTopRacers(String abbreviation, String start, String end) {
        reader.readFromFile(start, racersInfo);
        reader.readFromFile(end, racersInfo);
        reader.readFromFile(abbreviation, racersInfo);
        var list = parser.fillInRacerInfo(racersInfo);
        racersInfo = new RacersInfo();
        return formatter.getRacersFormatted(list);
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public TopRacersFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(TopRacersFormatter formatter) {
        this.formatter = formatter;
    }

    public RacerReader getReader() {
        return reader;
    }

    public void setReader(RacerReader reader) {
        this.reader = reader;
    }

    public RacersInfo getRacersInfo() {
        return racersInfo;
    }

    public void setRacersInfo(RacersInfo racersInfo) {
        this.racersInfo = racersInfo;
    }
}
