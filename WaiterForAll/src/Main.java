import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Using pointers:");
        Scanner s = new Scanner( System.in );
        System.out.print( "Enter number of threads: " );
        int amount = s.nextInt();
        Thread[] threads = new Thread[amount];
        range(0, amount).forEach(n -> {
            int numberOfThreads = n + 1;
            threads[n] = new Thread(() -> {
                System.out.println("Thread " + numberOfThreads + " is running");
            });
            threads[n].start();
        });
        for (Thread thread : threads) thread.join();

        System.out.println("Using CountDownLatch:");
        Scanner sc = new Scanner( System.in );
        System.out.print( "Enter number of threads: " );
        int amountC = sc.nextInt();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(amountC);

        range(0, amountC).forEach(i -> new Thread(new WaitForAll(startSignal, doneSignal)).start());

        System.out.println("CountDownLatch Start");
        startSignal.countDown();

        System.out.println("CountDownLatch Stop");
        doneSignal.await();


    }

}
