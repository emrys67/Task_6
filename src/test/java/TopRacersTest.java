import com.foxminded.formula.TopRacers;
import com.foxminded.formula.format.TopRacersFormatter;
import com.foxminded.formula.models.Racer;
import com.foxminded.formula.models.RacersInfo;
import com.foxminded.formula.parser.Parser;
import com.foxminded.formula.reader.RacerReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TopRacersTest {
    private static final String ABR_PATH = "src/main/resources/abbreviations.txt";
    private static final String FORMATTED_OUTPUT = "1.Sebastian Vettel   | FERRARI                    | 00:01:04.415";
    private static final List<Racer> racerList = new ArrayList<>();
    private RacersInfo racersInfo;
    private TopRacers topRacers;
    @Mock
    private TopRacersFormatter topRacersFormatter;
    @Mock
    private RacerReader racerReader;
    @Mock
    private Parser parser;

    @BeforeEach
    void setUp() {
        topRacers = new TopRacers(parser, topRacersFormatter, racerReader);
        topRacers.setRacersInfo(new RacersInfo());
        racersInfo = topRacers.getRacersInfo();
    }

    @Test
    void allMethodsAreUsed() {
        when(parser.fillInRacerInfo(racersInfo)).thenReturn(racerList);
        when(topRacersFormatter.getRacersFormatted(racerList)).thenReturn(FORMATTED_OUTPUT);
        when(racerReader.readFromFile(ABR_PATH, racersInfo)).thenReturn(racersInfo);
        topRacers.getTopRacers(ABR_PATH, ABR_PATH, ABR_PATH);
        Mockito.verify(parser).fillInRacerInfo(racersInfo);
        Mockito.verify(topRacersFormatter).getRacersFormatted(racerList);
        verify(racerReader, times(3)).readFromFile(ABR_PATH, racersInfo);
    }
}
