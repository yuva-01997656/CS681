package edu.umb.cs681.hw07;
import java.util.concurrent.locks.ReentrantLock;
public class ConcurrentSingleton {
	private static ConcurrentSingleton obj = null;
	private static ReentrantLock lock = new ReentrantLock();
	public static ConcurrentSingleton getInstance(){
		lock.lock();
		try {
			if (obj == null)
			obj = new ConcurrentSingleton();
			return obj;
		} finally {
			lock.unlock();
		}
	}
}