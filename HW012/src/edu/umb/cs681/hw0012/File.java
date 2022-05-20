package edu.umb.cs681.hw0012;

import java.time.LocalDateTime;
import java.util.Date;

@SuppressWarnings("unused")
public class File extends FSElement {
	
	public File(Directory parent, String name, int size) {
		
		super(parent, name, size, LocalDateTime.now());
		isFile = true;
		isDir = false;
	}
	
	public boolean isDirectory() {
		return this.isDir;
	}
	
	public void setSize (int size) {
		this.size = size;
	}
	
	public int getSize () {
		return this.size;
	}
	
	//override toString() method
    @Override 
    public String toString(){
    	
    	String str = "- " + getName();
        
        return str;
    }
    
   

}
