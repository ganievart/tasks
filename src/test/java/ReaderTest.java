import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * Created by sbt-ganiev-ar on 18.07.2017.
 */
public class ReaderTest {

    private static Reader reader;

    private static String inputFilename;
    private static String outputFilename;

    @BeforeClass
    public static void initReader() {
        reader = new Reader();
        inputFilename = "input.txt";
        outputFilename = "output.txt";
    }


    @Test(expected = RuntimeException.class)
    public void fileNotExistTest() throws Exception {
        reader.parseFile("notExistFile");
    }

    @Test
    public void emptyFileTest() throws Exception {
        reader.saveFile(reader.grouping(reader.parseFile("emptyfile.txt")), outputFilename);
        File file = new File(outputFilename);
        Assert.assertEquals(file.length(), 0);
    }

    @Test
    public void groupingTest() throws Exception {
        Map<Set<Character>, Map<Integer, Double>> mainmap = new HashMap<>();
        Map<Integer, Double> map1 = new HashMap<>();
        Map<Integer, Double> map2 = new HashMap<>();
        map1.put(6, 2.5);
        map1.put(5, 2.0);
        map2.put(4, 2.0);
        mainmap.put(new HashSet<>(Arrays.asList('a', 'o'))
                , map1);
        mainmap.put(new HashSet<>(Arrays.asList('a', 'e'))
                , map2);
        Assert.assertEquals(reader.grouping(reader.parseFile(inputFilename)), mainmap);
    }
}