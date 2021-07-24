import com.foxminded.formula.RacerReader;
import com.foxminded.formula.Racers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RacerReaderTest {
    private static final String ABR_PATH = "src/main/resources/abbreviations.txt";
    private static final String START_PATH = "src/main/resources/start.log";
    private static final String END_PATH = "src/main/resources/end.log";
    private RacerReader reader;

    @BeforeEach
    void setup() {
        reader = new RacerReader();
    }

    @Test
    void filesReaded() {
        Racers actual = reader.returnRacers(ABR_PATH, START_PATH, END_PATH);
        assertTrue(!actual.getAbriviations().isEmpty() && !actual.getStart().isEmpty() &&
                !actual.getEnd().isEmpty());
    }
}
