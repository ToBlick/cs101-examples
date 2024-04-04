package midterm_2_practice;

import java.util.List;

public class StringToChars {
    public static char[][] toCharArrays(List<String> sentence) {

        char[][] chars = new char[sentence.size()][];
        for(int j = 0; j < sentence.size(); ++j) {
            chars[j] = sentence.get(j).toCharArray();
        }
        return chars;
    }
}
