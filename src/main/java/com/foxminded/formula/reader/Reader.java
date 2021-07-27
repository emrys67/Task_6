package com.foxminded.formula.reader;

import com.foxminded.formula.models.RacersInfo;

public interface Reader {
    RacersInfo readFromFile(String filepath, RacersInfo racersInfo);
}
