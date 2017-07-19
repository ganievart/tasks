import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by sbt-ganiev-ar on 18.07.2017.
 */
public class Constants {

    static final Pattern pattern = Pattern.compile("\\s|\\.|;|,|$");
    static final Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','y','u','i','o'));

}
