package edu.umb.cs681.hw004;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class SingleThread {

    public static void main(String[] args) {
        Runnable task = new PrimeGenerator(1L, 2000000L);
        Thread t = new Thread(task);
        t.start();

        Long startTime = System.currentTimeMillis();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();

        System.out.println("Total time taken in Single threaded environment = "+ Math.abs(endTime - startTime) + " millisecond");

    }
}
