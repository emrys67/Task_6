package com.foxminded.formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Racers {
    private static final String GET_RACER_ABR_EXCEPTION = "Abriviation getting error";
    private List<Racer> racersList = new ArrayList<>();

    public Racer getRacerByAbr(String abr) {
        var racer = racersList.stream()
                .filter(abrv -> abrv.getAbr().equals(abr))
                .findFirst();
        if (racer.isPresent()) {
            return racer.get();
        } else {
            throw new IllegalArgumentException(GET_RACER_ABR_EXCEPTION);
        }
    }

    public List<Racer> getRacersList() {
        return racersList;
    }

    public void setRacersList(List<Racer> racersList) {
        this.racersList = racersList;
    }

    public void addRacer(Racer racer) {
        racersList.add(racer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Racers)) return false;
        var racers = (Racers) o;
        return racersList.equals(racers.racersList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racersList);
    }

    @Override
    public String toString() {
        return "Racers{" +
                "racersList=" + racersList +
                '}';
    }
}
