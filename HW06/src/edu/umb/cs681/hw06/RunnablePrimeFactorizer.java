package edu.umb.cs681.hw06;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
	public RunnablePrimeFactorizer(long d, long from, long to) {
		super(d);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("from must be >= 2, and to must be >= from.");
		}
	}
	protected boolean isEven(long num){
		if(num%2 == 0){ return true; }
		else{ return false; }
	}
	public void generatePrimeFactors() {
		long x = from;
		while( d != 1 && x <= to ){
			if( x > 2 && isEven(x)) {
				x++;
				continue;
			}
			if(d % x == 0) {
				factors.add(x);
				d /= x;
			}else {
				if(x==2){ 
					x++;
				}
				else{
					x += 2;
				}
			}
		}
	}
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread no." + Thread.currentThread().getId() + " generated " + factors);
	}
	// public static void main(String[] args) {
	// // Factorization of 36 with a separate thread
	// System.out.println("Factorization of 36");
	// RunnablePrimeFactorizer runnable = new RunnablePrimeFactorizer(36, 2,(long)Math.sqrt(36));
	// Thread thread = new Thread(runnable);
	// System.out.println("Thread #" + thread.getId() +
	// " performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
	// thread.start();
	// try {
	// thread.join();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
	//
	// // Factorization of 84 with two threads
	// System.out.println("Factorization of 84");
	// LinkedList<RunnablePrimeFactorizer> runnables = new LinkedList<RunnablePrimeFactorizer>();
	// LinkedList<Thread> threads = new LinkedList<Thread>();
	//
	// runnables.add( new RunnablePrimeFactorizer(84, 2,(long)Math.sqrt(84)/2 ));
	// runnables.add( new RunnablePrimeFactorizer(84, 1+(long)Math.sqrt(84)/2, (long)Math.sqrt(84) ));
	//
	// thread = new Thread(runnables.get(0));
	// threads.add(thread);
	// System.out.println("Thread #" + thread.getId() +
	// " performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());
	// thread = new Thread(runnables.get(1));
	// threads.add(thread);
	// System.out.println("Thread #" + thread.getId() +
	// " performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());
	// threads.forEach( (t)->t.start() );
	// threads.forEach( (t)->{ try{t.join();}
	// catch(InterruptedException e){e.printStackTrace(); }} );
	// LinkedList<Long> factors = new LinkedList<Long>();
	// runnables.forEach( (factorizer) -> factors.addAll(factorizer.getPrimeFactors()) );
	// System.out.println("Final result: " + factors + "\n");
	//
	// runnables.clear();
	// threads.clear();
	//
	// // Factorization of 2489 with two threads
	// System.out.println("Factorization of 2489");
	// runnables.add( new RunnablePrimeFactorizer(2489, 2,(long)Math.sqrt(2489)/2 ));
	// runnables.add( new RunnablePrimeFactorizer(2489, 1+(long)Math.sqrt(2489)/2, (long)Math.sqrt(2489) ));
	//
	// thread = new Thread(runnables.get(0));
	// threads.add(thread);
	// System.out.println("Thread #" + thread.getId() +
	// " performs factorization in the range of " +runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());
	//
	// thread = new Thread(runnables.get(1));
	// threads.add(thread);
	// System.out.println("Thread #" + thread.getId() +
	// " performs factorization in the range of " +runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());
	//
	// threads.forEach( (t)->t.start() );
	// threads.forEach( (t)->{ try{t.join();}
	// catch(InterruptedException e){e.printStackTrace(); }} );
	//
	// LinkedList<Long> factors2 = new LinkedList<Long>();
	// runnables.forEach( (factorizer) ->factors2.addAll(factorizer.getPrimeFactors()) );
	// System.out.println("Final result: " + factors2);
	// }
}