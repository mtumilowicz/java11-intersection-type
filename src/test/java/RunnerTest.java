import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2019-02-13.
 */
public class RunnerTest {

    @Test
    public void runAndClose() {
        Runner.runAndClose(new Impl1());
        Runner.runAndClose(new Impl2());
    }
}