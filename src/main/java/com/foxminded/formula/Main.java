package com.foxminded.formula;

import com.foxminded.formula.format.TopRacersFormatter;
import com.foxminded.formula.models.Racers;
import com.foxminded.formula.models.RacersInfo;
import com.foxminded.formula.parser.Parser;
import com.foxminded.formula.reader.RacerReader;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        RacerReader racerReader = new RacerReader();
        RacersInfo racersInfo = new RacersInfo();
        Racers racers = new Racers();
        racerReader.readFromFile("src/main/resources/abbreviations.txt", racersInfo);
        racerReader.readFromFile("src/main/resources/start.log", racersInfo);
        racerReader.readFromFile("src/main/resources/end.log", racersInfo);
        TopRacersFormatter topRacersFormatter = new TopRacersFormatter();
        System.out.println(topRacersFormatter.getRacersFormatted(parser.fillInRacerInfo(racersInfo, racers)));
    }
}
