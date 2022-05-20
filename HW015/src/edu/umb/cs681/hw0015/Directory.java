package edu.umb.cs681.hw0015;


import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
	
	private ConcurrentLinkedQueue<FSElement> queue;

	public Directory(Directory parent, String name) {
		super(parent, name, 0, LocalDateTime.now());
		queue = new ConcurrentLinkedQueue<FSElement>();
		isDir = true;
		isFile = false;
		lock = new ReentrantLock();
	}

	public ConcurrentLinkedQueue<FSElement> getChildren() {
		
		return this.queue;
		
	}
	
	public Directory getDirectory(String directory) {
		lock.lock();
		try {
			String foundDirectory = null;
			Directory dir = null;
			
			Iterator<FSElement> iterator = queue.iterator();
			while (iterator.hasNext()) {
			    foundDirectory = iterator.next().getName();
				
				if (foundDirectory == directory){
					
					dir = (Directory) iterator.next();
				}
			}
			
			return dir;
		}finally {
			lock.unlock();
		}
		
		
		
	}
	
	public void appendChild (FSElement element) {
		lock.lock();
		try {
			queue.add(element);
		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	public int getTotalSize () {
		lock.lock();
		try {
			int totalSize = 0;
			
			for (FSElement element: queue){
			
					totalSize+= element.getSize();
				
			}
			
			return totalSize;
		} finally {
			lock.unlock();
		}
		
	}
	
	public int getSize() {
		return this.size;
	}
	
	//override toString() method
    @Override 
	public String toString() {
		return getName();
	}

//	@Override
//	public void run() {
//		FileSystem fileSystem = FileSystem.getFileSystem();
//		File rand = new File(fileSystem.getRootDirectory(), "random file", 5);
//		Directory system = new Directory(fileSystem.root, "system");
//		File a = new File(system, "a.jpg", 10);
//		File b = new File(system, "b.jpg", 10);
//		File c = new File(system, "c.jpg", 10);
//		
//		
//		Directory home = new Directory(fileSystem.root, "home");
//		File d = new File(home, "d.jpg", 10);
//		
//		Directory pictures = new Directory(home, "pictures");
//		File e = new File(pictures, "e.jpg", 10);
//		File f = new File(pictures, "f.jpg", 10);
//		fileSystem.root.appendChild(system);
//		fileSystem.root.appendChild(home);
//		fileSystem.root.appendChild(rand);
//		
//		system.appendChild(a);
//		system.appendChild(b);
//		system.appendChild(c);
//		
//		pictures.appendChild(e);
//		pictures.appendChild(f);
//		
//		home.appendChild(d);
//		home.appendChild(pictures);
//		System.out.println(home.getSize());
//		System.out.println(home.getqueue());
//		
//		
//	}

	@Override
	public boolean isDirectory() {
		return this.isDir;
	}

}
