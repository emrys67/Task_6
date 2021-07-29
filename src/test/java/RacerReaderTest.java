import com.foxminded.formula.models.RacersInfo;
import com.foxminded.formula.reader.RacerReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RacerReaderTest {
    private static final String ABR_PATH = "src/main/resources/abbreviations.txt";
    private static final String START_PATH = "src/main/resources/start.log";
    private static final String END_PATH = "src/main/resources/end.log";
    private static final String WORNG_PATH = "src/main/resources/onion.txt";
    private static final String WRONG_LOG = "src/main/resources/wrong.log";
    private static final String ARG_EXCEPTION = "It is not a log file";
    private static final String PATH_EXCEPTION = "Path is wrong";
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

    @Test
    void readWrongLog() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reader.readFromFile(WRONG_LOG, racersInfo);
        });
        String actual = exception.getMessage();
        assertEquals(ARG_EXCEPTION, actual);
    }

    @Test
    void readWrongPath() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reader.readFromFile(WORNG_PATH, racersInfo);
        });
        String actual = exception.getMessage();
        assertEquals(PATH_EXCEPTION, actual);
    }

}
