import com.foxminded.formula.parser.Parser;
import com.foxminded.formula.reader.RacerReader;
import com.foxminded.formula.models.Racers;
import com.foxminded.formula.models.RacersInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private static final String ABR_PATH = "src/main/resources/abbreviations.txt";
    private static final String START_PATH = "src/main/resources/start.log";
    private static final String END_PATH = "src/main/resources/end.log";
    private static final String NULL_EXCEPTION = "Null input is not allowed";
    private static final String EXPECTED_RACERS_LIST = "Racers{racersList=[Racer{abr='DRR', name='Daniel Ricciardo'," +
            " car='RED BULL RACING TAG HEUER'" + ", startTime=12:14:12.054, endTime=12:15:24.067, lapTime=00:01:12.013}," +
            " Racer{abr='SVF', name='Sebastian" + " Vettel', car='FERRARI', startTime=12:02:58.917, endTime=12:04:03.332," +
            " lapTime=00:01:04.415}," + " Racer{abr='LHM', name='Lewis Hamilton', car='MERCEDES', startTime=12:18:20.125," +
            " endTime=12:19:32.585," + " lapTime=00:01:12.460}, Racer{abr='KRF', name='Kimi Raikkonen', car='FERRARI'," +
            " startTime=12:03:01.250," + " endTime=12:04:13.889, lapTime=00:01:12.639}, Racer{abr='VBM', name='Valtteri Bottas'," +
            " car='MERCEDES'," + " startTime=12:00, endTime=12:01:12.434, lapTime=00:01:12.434}, Racer{abr='EOF', name='Esteban Ocon'," +
            " car='FORCE INDIA MERCEDES', startTime=12:17:58.810, endTime=12:19:11.838, lapTime=00:01:13.028}," +
            " Racer{abr='FAM', name='Fernando Alonso', car='MCLAREN RENAULT', startTime=12:13:04.512," +
            " endTime=12:14:17.169, lapTime=00:01:12.657}, Racer{abr='CSR', name='Carlos Sainz', car='RENAULT'," +
            " startTime=12:03:15.145, endTime=12:04:28.095, lapTime=00:01:12.950}, Racer{abr='SPF', name='Sergio Perez'," +
            " car='FORCE INDIA MERCEDES', startTime=12:12:01.035, endTime=12:13:13.883, lapTime=00:01:12.848}," +
            " Racer{abr='PGS', name='Pierre Gasly', car='SCUDERIA TORO ROSSO HONDA', startTime=12:07:23.645," +
            " endTime=12:08:36.586, lapTime=00:01:12.941}, Racer{abr='NHR', name='Nico Hulkenberg', car='RENAULT'," +
            " startTime=12:02:49.914, endTime=12:04:02.979, lapTime=00:01:13.065}, Racer{abr='SVM', name='Stoffel Vandoorne'," +
            " car='MCLAREN RENAULT', startTime=12:18:37.735, endTime=12:19:50.198, lapTime=00:01:12.463}, Racer{abr='SSW'," +
            " name='Sergey Sirotkin', car='WILLIAMS MERCEDES', startTime=12:16:11.648, endTime=12:17:24.354," +
            " lapTime=00:01:12.706}, Racer{abr='CLS', name='Charles Leclerc', car='SAUBER FERRARI', startTime=12:09:41.921," +
            " endTime=12:10:54.750, lapTime=00:01:12.829}, Racer{abr='RGH', name='Romain Grosjean', car='HAAS FERRARI'," +
            " startTime=12:05:14.511, endTime=12:06:27.441, lapTime=00:01:12.930}, Racer{abr='BHS', name='Brendon Hartley'," +
            " car='SCUDERIA TORO ROSSO HONDA', startTime=12:14:51.985, endTime=12:16:05.164, lapTime=00:01:13.179}," +
            " Racer{abr='MES', name='Marcus Ericsson', car='SAUBER FERRARI', startTime=12:04:45.513, endTime=12:05:58.778," +
            " lapTime=00:01:13.265}, Racer{abr='LSW', name='Lance Stroll', car='WILLIAMS MERCEDES', startTime=12:06:13.511," +
            " endTime=12:07:26.834, lapTime=00:01:13.323}, Racer{abr='KMH', name='Kevin Magnussen', car='HAAS FERRARI'," +
            " startTime=12:02:51.003, endTime=12:04:04.396, lapTime=00:01:13.393}]}";
    private Parser parser;
    private RacerReader reader;
    private RacersInfo racersInfo;
    private Racers racers;

    @BeforeEach
    void setUp() {
        racersInfo = new RacersInfo();
        parser = new Parser();
        reader = new RacerReader();
        racers = new Racers();
    }

    @Test
    void returnFilledRacers() {
        reader.readFromFile(START_PATH, racersInfo);
        reader.readFromFile(END_PATH, racersInfo);
        reader.readFromFile(ABR_PATH, racersInfo);
        String actual = parser.fillInRacerInfo(racersInfo,racers).toString();
        assertEquals(EXPECTED_RACERS_LIST, actual);
    }

    @Test
    void fillInNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parser.fillInRacerInfo(null, racers);
        });
        String actual = exception.getMessage();
        assertEquals(NULL_EXCEPTION, actual);
    }
}
