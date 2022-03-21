package edu.umb.cs681.hw004;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator implements Runnable {

    protected List<Long> primes = new ArrayList<>();
    protected long start, end;

    public PrimeGenerator(long start, long end){
        if(start >= 1 && end > start){
            this.start = start;
            this.end = end;
        }else{
            throw new RuntimeException("Invalid input values, from:" + start + " to:" + end);
        }
    }

    protected boolean isEven(long n){
        return n % 2 == 0;
    }

    protected boolean isPrime(long n){
        // Numbers smaller than or equal to One are not prime.
        if(n <= 1){ return false; }
        // Except 2, even numbers are not prime.
        if( n > 2 && isEven(n) ){ return false; }
        long i;
        // Find a number "i" which can divide "n"
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        // If such a number "i" is found, n is not prime. Otherwise, n is prime.
        return i == 1;
    }

    @Override
    public void run() {
        generatePrimes();
        System.out.println("Prime Numbers : "+primes);
    }

    public void generatePrimes(){
        for (long n = start; n <= end; n++) {
            if( isPrime(n) ){ primes.add(n); }
        }
    }
}
