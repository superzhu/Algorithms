package string;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringContainTest {
    private static final Logger logger = LoggerFactory.getLogger(StringContainTest.class);

    @Test
    public void hashCheck() {
        logger.debug("running hashCheck..." +logger.getClass());
        boolean flag = StringContain.hashCheck("ABCD".toCharArray(),"CA".toCharArray());
        assertThat(flag, is(Boolean.TRUE));
        assertTrue(Boolean.TRUE.equals(flag));
    }
}
