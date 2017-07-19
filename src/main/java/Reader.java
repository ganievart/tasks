import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This main class reads words from input.txt and determines the
 * average number of vowels per word grouped by:
 * set of volwes present in word and length of the word.
 * <p>
 * Result writes to output.txt
 *
 * @author Ganiev Arthur
 */
public class Reader {

    public List<Letter> parseFile(String inputFilename) {
        List<Letter> letters = new ArrayList<>();
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(inputFilename).toURI());
            try (Stream<String> stream = Files.lines(path)) {
                stream.forEach((line) -> {
                    String words[] = Constants.pattern.split(line);
                    for (String w : words) {
                        if (w.length() != 0) {
                            parseWord(w, letters);
                        }
                    }
                });
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return letters;
    }

    private void parseWord(String word, List<Letter> letters) {
        Set<Character> characters = new HashSet<>();
        long count = 0L;
        for (int i = 0; i < word.length(); i++) {
            Character c = Character.toLowerCase(word.charAt(i));
            if (Constants.vowels.contains(c)) {
                characters.add(c);
                count++;
            }
        }
        letters.add(new Letter(word.length(), characters, count));
    }

    public void saveFile(Map<Set<Character>, Map<Integer, Double>> grouped, String outputFilename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFilename))) {
            for (Set<Character> characters : grouped.keySet()) {
                for (Integer wordlength : grouped.get(characters).keySet())
                    out.write("(" + characters + "," + wordlength + ") -> " + grouped.get(characters).get(wordlength) + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Set<Character>, Map<Integer, Double>> grouping(List<Letter> letters) {
        Map<Set<Character>, Map<Integer, Double>> result = new HashMap<>();
        letters.stream()
                .collect(Collectors.groupingBy(Letter::getCharacterSet,
                        Collectors.groupingBy(Letter::getWordlength,
                                Collectors.averagingDouble(Letter::getVowelsNum))))
                .forEach(result::put);
        return result;
    }

    public static void main(String[] args) throws URISyntaxException {
        String inputFilename = "input.txt";
        String outputFilename = "output.txt";

        Reader reader = new Reader();

        getLogger().log(Level.INFO, "Start parse file: {0}", inputFilename);
        List<Letter> letters = reader.parseFile(inputFilename);

        getLogger().log(Level.INFO, "Save result to file: {0}", outputFilename);
        reader.saveFile(reader.grouping(letters), outputFilename);

        getLogger().log(Level.INFO, "The program successfully finished");

    }

    private static Logger getLogger() {
        return Logger.getLogger(Reader.class.getName());
    }
}
