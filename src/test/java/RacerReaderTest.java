import com.foxminded.formula.RacerReader;
import com.foxminded.formula.RacersInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RacerReaderTest {
    private static final String ABR_PATH = "src/main/resources/abbreviations.txt";
    private static final String START_PATH = "src/main/resources/start.log";
    private static final String END_PATH = "src/main/resources/end.log";
    private RacerReader reader;
    private RacersInfo racersInfo;

    @BeforeEach
    void setup() {
        reader = new RacerReader();
        racersInfo = new RacersInfo();
    }

    @Test
    void filesReaded() {
        reader.readFromFile(START_PATH, racersInfo);
        reader.readFromFile(END_PATH, racersInfo);
        reader.readFromFile(ABR_PATH, racersInfo);
        assertTrue(!racersInfo.getAbbreviations().isEmpty() && !racersInfo.getStart().isEmpty() &&
                !racersInfo.getEnd().isEmpty());
    }
}
