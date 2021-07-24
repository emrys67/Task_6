package com.foxminded.formula;

import java.time.LocalTime;
import java.util.Objects;

public class Racer {
    private String abr;
    private String name;
    private String car;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime lapTime;

    public Racer(String abr, String name, String car) {
        this.abr = abr;
        this.name = name;
        this.car = car;
    }

    public Racer(String abr) {
        this.abr = abr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getLapTime() {
        return lapTime;
    }

    public void setLapTime(LocalTime lapTime) {
        this.lapTime = lapTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Racer)) return false;
        Racer racer = (Racer) o;
        return abr.equals(racer.abr) && startTime.equals(racer.startTime) && endTime.equals(racer.endTime) && name.equals(racer.name) && car.equals(racer.car) && lapTime.equals(racer.lapTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abr, startTime, endTime, name, car, lapTime);
    }

    @Override
    public String toString() {
        return "Racer{" +
                "abr='" + abr + '\'' +
                ", name='" + name + '\'' +
                ", car='" + car + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", lapTime=" + lapTime +
                '}';
    }
}
