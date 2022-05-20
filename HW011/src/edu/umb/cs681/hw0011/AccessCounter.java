package edu.umb.cs681.hw0011;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	final ConcurrentMap<Path, AtomicLong> counterMap = new ConcurrentHashMap<>();
	private HashMap<java.nio.file.Path,Integer> accessCountMap= new HashMap<>();
	private ReentrantLock lock = new ReentrantLock();  
	@SuppressWarnings("unused")
	private Path path ;
	@SuppressWarnings("unused")
	private RequestHandler rq; 

	public void increment(Path path){
		lock.lock();
		try{
			if(accessCountMap.containsKey(path))
				accessCountMap.put(path,accessCountMap.get(path)+1);
			else
				{accessCountMap.put(path,1);}
		} finally {
			lock.unlock();
		}
	}

	public int getCount(Path path){
		lock.lock();
		int count=0;
		try{
			if(accessCountMap.containsKey(path))
				count= accessCountMap.get(path);
		} finally {
			lock.unlock();
		}
		return count;
	}
	
	private static AccessCounter instance = null;
	private static ReentrantLock lock1 = new ReentrantLock();
	// Factory method to create or return the singleton instance
	public static AccessCounter getInstance(){
		lock1.lock();
		try{
			if(instance==null){ instance = new AccessCounter(); }
			return instance;
		}finally{
			lock1.unlock();
		}
	}



}

