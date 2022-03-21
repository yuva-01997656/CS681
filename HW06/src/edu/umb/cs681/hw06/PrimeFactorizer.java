package edu.umb.cs681.hw06;
import java.util.LinkedList;
// Generates prime factors of a given number (dividend)
// Prime factors are generated in the range of 2 and dividend
// from is the lower bound of the range (2)
// to is the upper bound of the range (d
public class PrimeFactorizer {
	protected long d, from, to;
	protected LinkedList<Long> factors = new LinkedList<Long>();
	public PrimeFactorizer(long x){
		if(x >= 2){
			this.d = x;
			this.from = 2;
			this.to = x;
		}else{
			throw new RuntimeException("Input must be >= 2");
		}
	}
	public long getDividend() {
		return d; 
	}
	public long getFrom(){ 
		return from; 
	}
	public long getTo(){ 
		return to; 
	}
	public LinkedList<Long> getPrimeFactors(){ 
		return factors; 
	}
	public void generatePrimeFactors() {
		long n = 2;
		while( d != 1 && n <= to ){
			if(d % n == 0) {
				factors.add(n);
				d /= n;
			}else {
				if(n==2){ 
					n++; 
				}
				else{
					n += 2; 
				}
			}
		}
	}
}