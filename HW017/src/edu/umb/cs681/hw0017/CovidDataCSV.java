package edu.umb.cs681.hw0017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors; 
import java.util.stream.Stream;
public class CovidDataCSV implements Runnable{
    public static void main(String[] args) {
		Thread t1 = new Thread(new CovidDataCSV());
		t1.start();
		Thread t2 = new Thread(new CovidDataCSV());
		t2.start();
		Thread t3 = new Thread(new CovidDataCSV());
		t3.start();
    }
		
		@SuppressWarnings("rawtypes")
		@Override
		public void run() {
			
			Path file_path = Paths.get("./src/Data/CovidPVI_Data.txt"); 
			try( Stream<String> data = Files.lines(file_path) ){
				List<List<String>> matrix = data.map( d -> { 
					return Stream.of( d.split(",")).map(value->value.substring(0, value.length())) .collect( Collectors.toList() ); 
				}).collect(Collectors.toList());
				List totalPopulation = matrix.stream().parallel().filter((i) -> i.get(4).contains("Massachusetts")).collect(Collectors.toList());
				List suffolkDeaths = matrix.stream().parallel().filter((i) -> i.get(5).contains("Suffolk")).collect(Collectors.toList()).get(0);
				
				String massachusettsDeaths = matrix.stream().parallel().filter((i) -> i.get(4).contains("Massachusetts"))
						.map((i) -> i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));
				System.out.println("\n1. Deaths occurred in the Suffolk county of the Massachusetts state are: " + suffolkDeaths.get(7)+ " - " + Thread.currentThread().getName());
				System.out.println("\n2. Total deaths in State of Massachusetts are: " + massachusettsDeaths+ " - " + Thread.currentThread().getName());
				System.out.println("\n3. Average number of deaths in State of Massachusetts are: " + Integer.parseInt(massachusettsDeaths)/totalPopulation.size()+ " - " + Thread.currentThread().getName());
			}
	        catch(IOException e) {
	        	e.printStackTrace(); 
	        }
		}
} 
