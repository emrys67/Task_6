package com.foxminded.formula;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        RacerReader racerReader = new RacerReader();
        Racers racers = racerReader.returnRacers("src/main/resources/abbreviations.txt", "src/main/resources/start.log"
                , "src/main/resources/end.log");
        Formatter formatter = new Formatter();
        System.out.println(formatter.getRacersFormatted(calculator.fillInRacerInfo(racers)));
    }
}
