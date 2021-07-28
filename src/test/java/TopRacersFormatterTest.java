import com.foxminded.formula.format.TopRacersFormatter;
import com.foxminded.formula.models.Racer;
import com.foxminded.formula.models.RacersInfo;
import com.foxminded.formula.parser.Parser;
import com.foxminded.formula.reader.RacerReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopRacersFormatterTest {
    private static final String ABR_PATH = "src/main/resources/abbreviations.txt";
    private static final String START_PATH = "src/main/resources/start.log";
    private static final String END_PATH = "src/main/resources/end.log";
    private static final String NULL_EXCEPTION = "Null input is not allowed";
    private static final String RACERS_FORMATTED =
            "1.Sebastian Vettel   | FERRARI                    | 00:01:04.415\n" +
                    "2.Daniel Ricciardo   | RED BULL RACING TAG HEUER  | 00:01:12.013\n" +
                    "3.Valtteri Bottas    | MERCEDES                   | 00:01:12.434\n" +
                    "4.Lewis Hamilton     | MERCEDES                   | 00:01:12.460\n" +
                    "5.Stoffel Vandoorne  | MCLAREN RENAULT            | 00:01:12.463\n" +
                    "6.Kimi Raikkonen     | FERRARI                    | 00:01:12.639\n" +
                    "7.Fernando Alonso    | MCLAREN RENAULT            | 00:01:12.657\n" +
                    "8.Sergey Sirotkin    | WILLIAMS MERCEDES          | 00:01:12.706\n" +
                    "9.Charles Leclerc    | SAUBER FERRARI             | 00:01:12.829\n" +
                    "10.Sergio Perez      | FORCE INDIA MERCEDES       | 00:01:12.848\n" +
                    "11.Romain Grosjean   | HAAS FERRARI               | 00:01:12.930\n" +
                    "12.Pierre Gasly      | SCUDERIA TORO ROSSO HONDA  | 00:01:12.941\n" +
                    "13.Carlos Sainz      | RENAULT                    | 00:01:12.950\n" +
                    "14.Esteban Ocon      | FORCE INDIA MERCEDES       | 00:01:13.028\n" +
                    "15.Nico Hulkenberg   | RENAULT                    | 00:01:13.065\n" +
                    "================================================================\n" +
                    "16.Brendon Hartley   | SCUDERIA TORO ROSSO HONDA  | 00:01:13.179\n" +
                    "17.Marcus Ericsson   | SAUBER FERRARI             | 00:01:13.265\n" +
                    "18.Lance Stroll      | WILLIAMS MERCEDES          | 00:01:13.323\n" +
                    "19.Kevin Magnussen   | HAAS FERRARI               | 00:01:13.393\n";
    private TopRacersFormatter topRacersFormatter;
    private RacerReader racerReader;
    private Parser parser;
    private RacersInfo racersInfo;
    private List<Racer> racersList;

    @BeforeEach
    void setUp() {
        racersInfo = new RacersInfo();
        racerReader = new RacerReader();
        parser = new Parser();
        topRacersFormatter = new TopRacersFormatter();
    }

    @Test
    void shouldReturnRacersString() {
        racerReader.readFromFile(START_PATH, racersInfo);
        racerReader.readFromFile(END_PATH, racersInfo);
        racerReader.readFromFile(ABR_PATH, racersInfo);
        String actual = topRacersFormatter.getRacersFormatted(parser.fillInRacerInfo(racersInfo));
        assertEquals(RACERS_FORMATTED, actual);
    }
    @Test
    void nullInput(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            topRacersFormatter.getRacersFormatted(null);
        });
        String actual = exception.getMessage();
        assertEquals(NULL_EXCEPTION,actual);
    }

    @Test
    void nullStart(){
        racerReader.readFromFile(START_PATH, racersInfo);
        racerReader.readFromFile(END_PATH, racersInfo);
        racerReader.readFromFile(ABR_PATH, racersInfo);
        racersList = parser.fillInRacerInfo(racersInfo);
        racersList.get(0).setStartTime(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            topRacersFormatter.getRacersFormatted(racersList);
        });
        String actual = exception.getMessage();
        assertEquals(NULL_EXCEPTION,actual);
    }
    @Test
    void nullEnd(){
        racerReader.readFromFile(START_PATH, racersInfo);
        racerReader.readFromFile(END_PATH, racersInfo);
        racerReader.readFromFile(ABR_PATH, racersInfo);
        racersList = parser.fillInRacerInfo(racersInfo);
        racersList.get(0).setEndTime(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            topRacersFormatter.getRacersFormatted(racersList);
        });
        String actual = exception.getMessage();
        assertEquals(NULL_EXCEPTION,actual);
    }
    @Test
    void nullAbr(){
        racerReader.readFromFile(START_PATH, racersInfo);
        racerReader.readFromFile(END_PATH, racersInfo);
        racerReader.readFromFile(ABR_PATH, racersInfo);
        racersList = parser.fillInRacerInfo(racersInfo);
        racersList.get(0).setAbr(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            topRacersFormatter.getRacersFormatted(racersList);
        });
        String actual = exception.getMessage();
        assertEquals(NULL_EXCEPTION,actual);
    }
}
