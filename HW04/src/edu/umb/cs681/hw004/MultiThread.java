package edu.umb.cs681.hw004;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class MultiThread {

    public static void main(String[] args) {
        Runnable task1 = new PrimeGenerator(1L, 2000000L);
        Runnable task2 = new PrimeGenerator(1L, 2000000L);
        Runnable task3 = new PrimeGenerator(1L, 2000000L);

        // Creating threads
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        // starting threads
        t1.start();
        t2.start();
        t3.start();

        Long startTime = System.currentTimeMillis();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();

        System.out.println("Total time taken in multithreading environment = "+ Math.abs(endTime - startTime) + " millisecond");
    }
}
