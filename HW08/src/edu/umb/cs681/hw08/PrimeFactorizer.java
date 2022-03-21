package edu.umb.cs681.hw08;
import java.util.LinkedList;
// Generates prime factors of a given number (d)
// Prime factors are generated in the range of 2 and d
// from: lower bound of the range (2)
// to: upper bound of the range (d)
public class PrimeFactorizer {
	protected long d, from, to;
	protected LinkedList<Long> factors = new LinkedList<Long>();
	public PrimeFactorizer(long d){
		if(d >= 2){
		this.d = d;
		this.from = 2;
		this.to = d;
		}else{
		throw new RuntimeException("Input must be >= 2.");
		}
	}
	public long getd() { 
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
			}
			else {
				if(n==2)
				{ 
					n++; 
				}
				else{ 
					n += 2; 
				}
			}
		}
	}
}