import java.util.concurrent.CountDownLatch;

public class WaitForAll implements Runnable {
    private final CountDownLatch start;
    private final CountDownLatch stop;
    private static int counter = 0;


    /**
     * @param start
     * @param stop
     */
    WaitForAll(CountDownLatch start, CountDownLatch stop) {
        this.start = start;
        this.stop = stop;
        counter++;
    }
    public void run() {
        try {
            start.await();
            System.out.println(counter + "Thread is running");
            stop.countDown();
        } catch (InterruptedException ex) {}
    }

}