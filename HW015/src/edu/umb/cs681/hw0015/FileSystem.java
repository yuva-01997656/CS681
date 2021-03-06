package edu.umb.cs681.hw0015;


public class FileSystem implements Runnable{
	
	private static FileSystem instance;
	public Directory root = null;
	private int tab;

	private FileSystem() {}
	
	public static FileSystem getFileSystem(){
		
		clearSystem();
		
		if (instance == null){
			instance = new FileSystem();
			
		}
		return instance;
	}
	
	private static void clearSystem(){
		// Set singleton instance to null
		// to clear out old instance
		// in case one exists
		instance = null;
	}
	
	public Directory getRootDirectory(){
		if (this.root == null){
				this.root = new Directory(null, "root");
			}
		
		return this.root;
	}
public void moreElements (FSElement a, int num, StringBuilder s){
		
		StringBuilder sb = s;
	
		if (!a.isFile()){
			
			Directory dir1 = (Directory) a;
			
			dir1.getDirectory(a.getName());
			
			this.tab++;
			
			for (FSElement b :dir1.getChildren()){
			
				sb.append(getTab() + b + "\n");
				
				moreElements(b, this.tab, sb);
			}
		}
		
	}
	
	public String getTab(){
		
		String tabs = "";
		String tab = "\t";
		
		for(int i=0; i < this.tab; i++){
			
			tabs = tabs + tab;
		}
		
		return tabs;
		
	}
public void showAllElements(){
		
		if (root.getChildren().size() > 0){	
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(root + "\n");
				
				for (FSElement e : root.getChildren()){
					
					sb.append("\t" + e + "\n");
					
					if (!e.isFile()){
						
						Directory dir = root.getDirectory(e.getName());
						
						this.tab=2;
						
						for (FSElement a :dir.getChildren()){
							
							sb.append("\t\t" + a + "\n");
							
							moreElements(a, this.tab, sb);
							
						}
						
					}
					
				}
			
			System.out.print(sb);
			
		} else {
			System.out.println("Root directory is empty.");
		}
		
	}
	
	@Override
	public void run() {
		//System.out.println("File System: ");
		
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		File rand = new File(fileSystem.getRootDirectory(), "random file", 5);
		
		Directory system = new Directory(fileSystem.root, "system");
		File a = new File(system, "a.jpg", 10);
		File b = new File(system, "b.jpg", 45);
		File c = new File(system, "c.jpg", 6);
		
		
		Directory home = new Directory(fileSystem.root, "home");
		File d = new File(home, "d.jpg", 12);
		
		Directory pictures = new Directory(home, "pictures");
		File e = new File(pictures, "e.jpg", 75);
		File f = new File(pictures, "f.jpg", 33);
		
		Directory newPics = new Directory(pictures, "new pics");
		File g = new File(pictures, "g.jpg", 16 );
		File h = new File(pictures, "h.jpg", 15);
		
		Directory moreNewPics = new Directory(pictures, "more new pics");
		File i = new File(pictures, "i.jpg", 96);
		File j = new File(pictures, "j.jpg", 25);
		
		Directory evenMoreNewPics = new Directory(pictures, "even more new pics");
		File k = new File(pictures, "k.jpg", 18);
		File l = new File(pictures, "l.jpg", 10);
		
		fileSystem.root.appendChild(system);
		fileSystem.root.appendChild(home);
		fileSystem.root.appendChild(rand);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		pictures.appendChild(e);
		pictures.appendChild(f);
		
		newPics.appendChild(g);
		newPics.appendChild(h);
		
		moreNewPics.appendChild(i);
		moreNewPics.appendChild(j);
		
		evenMoreNewPics.appendChild(k);
		evenMoreNewPics.appendChild(l);
		
		home.appendChild(d);
		home.appendChild(pictures);
		
		pictures.appendChild(newPics);
		newPics.appendChild(moreNewPics);
		moreNewPics.appendChild(evenMoreNewPics);
		
		//fileSystem.showAllElements();
		
		//System.out.println("\nRoot folder size: " + fileSystem.root.getSize());
		
		//System.out.println("Home folder size: " + home.getSize());
		
		//System.out.println("home is a directory : " + home.isDirectory());
		//System.out.println("\"k.jpg\" is a directory : " + k.isDirectory());
		System.out.print(" " + home.getChildren() + "\n");
		System.out.println("home is a directory ? : " + home.isDirectory());
		System.out.println("\"k.jpg\" is a directory ? : " + k.isDirectory());
		System.out.println("Total size : " + home.getTotalSize());
		System.out.println("Size of \"h.jpg\" : " + h.getSize());
	}
	
	public static void main (String args[]) throws InterruptedException{

		for(int i=0;i<2;i++){
			Thread thread1 = new Thread(new FileSystem());
			System.out.print("Children of thread" +  (i+1) +" : ");
			
		    thread1.start();
		    Thread.sleep(1000);
		}
		
		
	}

	

}
