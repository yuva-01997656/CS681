package edu.umb.cs681.hw08;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellablePrimeFactorizer (long d, long from, long to){
		super(d, from, to);
	}
	public void setDone() {
		lock.lock();
	try {
		done = false;
	}
	finally {
		lock.unlock();
	}
	}
	public void generatePrimeFactors(){
		long n = from;
		while( d != 1 && n <= to ){
			lock.lock();
			try{
				if(done){
					break;
				}
				if( n > 2 && isEven(n)) {
					n++;
					continue;
				}
				if(d % n == 0) {
					factors.add(n);
					d /= n;
				}else {
					if(n==2){ n++; }
					else{ n += 2; }
				}
			} 
			finally {
				lock.unlock();
			}
		}
	}
}