package string.search;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ReplaceBlanksTest {
    @Test
    public void containBlanks() {
        ReplaceBlanks replaceBlanks = new ReplaceBlanks(Optional.of("I work at oracle."));
        Optional<String> result = replaceBlanks.replaceAll(' ',"%20");
        assertEquals("result should be: I%20work%20at%20oracle.","I%20work%20at%20oracle.",result.get());

        ReplaceBlanks replaceBlanks2 = new ReplaceBlanks(Optional.empty());
        result = replaceBlanks2.replaceAll(' ',"%20");
        assertThat(result.isPresent(), is(Boolean.FALSE));

        ReplaceBlanks replaceBlanks3 = new ReplaceBlanks(Optional.of(" helloworld"));
        result = replaceBlanks3.replaceAll(' ',"%20");
        assertEquals("result should be: %20helloworld","%20helloworld",result.get());
    }
}
