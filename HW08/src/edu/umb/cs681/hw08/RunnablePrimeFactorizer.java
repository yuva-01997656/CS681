package edu.umb.cs681.hw08;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
	public RunnablePrimeFactorizer(long d, long from, long to) {
		super(d);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
		}
	}
	protected boolean isEven(long n){
		if(n%2 == 0){ 
			return true; 
		}
		else{ 
			return false; 
		}
	}
	public void generatePrimeFactors() {
		long n = from;
		while( d != 1 && n <= to ){
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
	}
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}
}