package edu.umb.cs681.hw009;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton{
	private ConcurrentSingleton(){};
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<>();
	public static ConcurrentSingleton getInstance(){
		
		ConcurrentSingleton cs = instance.get();
		
		if(cs == null) {
			cs = new ConcurrentSingleton();
			
			if(!instance.compareAndSet(null, cs)) {
				cs = instance.get();
			}
		}
		return cs;
	} 
	
	public static void main(String[] args){
		for(int i=0; i<10; i++){
		new Thread(
				()->{System.out.println(ConcurrentSingleton.getInstance());}).start();
		}
	}
}