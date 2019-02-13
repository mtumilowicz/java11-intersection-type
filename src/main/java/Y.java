import java.io.Serializable;

/**
 * Created by mtumilowicz on 2019-02-13.
 */
class Y {
    static <I extends Runnable & AutoCloseable> void yyy(I y) {
        try (y) {
            y.run();
        } catch (Exception e) {
            // handle
        }
    }

    static void yyy2(RunnableAndAutoCloseable y) {
        try (y) {
            y.run();
        } catch (Exception e) {
            // handle
        }
    }
}

interface RunnableAndAutoCloseable extends AutoCloseable, Runnable {
}

class Impl1 implements AutoCloseable, Runnable {

    @Override
    public void close() {
        System.out.println("closed1");
    }

    @Override
    public void run() {
        System.out.println("run1");
    }
}

class Impl2 implements AutoCloseable, Runnable, Serializable {

    @Override
    public void close() {
        System.out.println("closed2");
    }

    @Override
    public void run() {
        System.out.println("run2");
    }
}