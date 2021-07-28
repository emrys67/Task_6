package com.foxminded.formula.format;

import com.foxminded.formula.models.Racer;

import java.util.List;

public interface Formatter {
    String getRacersFormatted(List<Racer> racers);
}
