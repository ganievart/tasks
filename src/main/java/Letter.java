

import java.util.Set;


/**
 * Created by sbt-ganiev-ar on 18.07.2017.
 */
public class Letter {

    private Integer wordlength;
    private Set<Character> characterSet;
    private Long vowelsNum;


    public Letter(Integer wordlength, Set<Character> characterSet, Long vowelsNum) {
        this.wordlength = wordlength;
        this.characterSet = characterSet;
        this.vowelsNum = vowelsNum;
    }

    public Integer getWordlength() {
        return wordlength;
    }

    public void setWordlength(Integer wordlength) {
        this.wordlength = wordlength;
    }

    public Set<Character> getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(Set<Character> characterSet) {
        this.characterSet = characterSet;
    }

    public Long getVowelsNum() {
        return vowelsNum;
    }

    public void setVowelsNum(Long vowelsNum) {
        this.vowelsNum = vowelsNum;
    }

    @Override
    public String toString() {
        return "(" + characterSet + "," + wordlength + ") " + vowelsNum;
    }
}
