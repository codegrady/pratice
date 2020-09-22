package pattern;

import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        String pattern = "\\|\\|\\|urwork://(atUser|replyUser)(.*?)\\|\\|\\|";
        Pattern r = Pattern.compile(pattern);

        String tes =  " fdfaklsdjfklasdjfl";

    }
}
