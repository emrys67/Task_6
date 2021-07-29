package com.foxminded.formula;

import com.foxminded.formula.format.TopRacersFormatter;
import com.foxminded.formula.parser.Parser;
import com.foxminded.formula.reader.RacerReader;

public class Main {
    public static void main(String[] args) {
        TopRacers topRacers = new TopRacers(new Parser(), new TopRacersFormatter(), new RacerReader());
        System.out.println(topRacers.getTopRacers("src/main/resources/abbreviations.txt",
                "src/main/resources/start.log", "src/main/resources/end.log"));
    }
}
