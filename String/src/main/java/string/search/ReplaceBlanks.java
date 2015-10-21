package string.search;

import java.util.Optional;

/**
 * Please implement a function to replace each blank in a string with “%20”. For instance, it
 * outputs “We%20are%20happy.” if the input is “We are happy.”.
 */
public class ReplaceBlanks {
    private Optional<String> origin = Optional.empty();

    public ReplaceBlanks(Optional<String> input) {
        origin = input;
    }

    public Optional<String> replaceAll(char regex, String replacement) {
        if(!origin.isPresent() || origin.get().isEmpty()) {
            return Optional.empty();
        }

        int numberOfBlank = 0;
        String originStr = origin.get();
        int originLen = originStr.length();

        for(int i =0; i< originLen; i++) {
            if(originStr.charAt(i) == regex)
                ++ numberOfBlank;
        }

        StringBuilder builder = new StringBuilder(originLen + numberOfBlank*(replacement.length()-1) );

        for(int i =0; i< originLen; i++) {
            if(originStr.charAt(i) == regex) {
                builder.append(replacement);
            } else {
                builder.append(originStr.charAt(i));
            }
        }

        return Optional.of(builder.toString());
    }
}