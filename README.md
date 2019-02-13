# java11-intersection-type
_Reference_: https://docs.oracle.com/javase/specs/jls/se11/html/jls-4.html#jls-4.9

# preview

# project description
We will show easy example where intersection types are very handy:
1. Suppose we have classes for third-party libraries (we can't modify them):
    ```
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
    ```
    ```
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
    ```
1. And we want to write a method that will execute action and close the resource, something like this:
    ```
    static <X> void runAndClose(X action) {
        try (y) {
            y.run();
        } catch (Exception e) {
            // handle
        }
    }
    ```
    * where `X` is: `AutoCloseable` and `Runnable`
1. We may try to go through additional interface:
    * additional interface:
        ```
        interface RunnableAndAutoCloseable extends AutoCloseable, Runnable { }
        ```
    * and method:
        ```
        static void runAndClose2(RunnableAndAutoCloseable y) {
            try (y) {
                y.run();
            } catch (Exception e) {
                // handle
            }
        }
        ```
    * **but note that we cannot modify Impl1 and Impl2 classes, so it is a road to nowhere**
1. We may use intersection type:
    ```
    static <I extends Runnable & AutoCloseable> void runAndClose(I y) {
        try (y) {
            y.run();
        } catch (Exception e) {
            // handle
        }
    }
    ```