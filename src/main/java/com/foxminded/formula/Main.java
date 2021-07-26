package com.foxminded.formula;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        RacerReader racerReader = new RacerReader();
        RacersInfo racersInfo = new RacersInfo();
        racerReader.readFromFile("src/main/resources/abbreviations.txt", racersInfo);
        racerReader.readFromFile("src/main/resources/start.log", racersInfo);
        racerReader.readFromFile("src/main/resources/end.log", racersInfo);
        Formatter formatter = new Formatter();
        System.out.println(formatter.getRacersFormatted(parser.fillInRacerInfo(racersInfo)));
    }
}
