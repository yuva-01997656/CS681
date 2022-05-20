package edu.umb.cs681.hw0016;

import java.util.ArrayList;

public class Client {
	public static void main(String args[])
	{
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car1 = new Car("Prius", 25000);
		Car car2 = new Car("Escape", 15000);
		Car car3 = new Car("ModelS", 30000);
		Car car4 = new Car("CX-5", 28000);
		
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		
		int carMakerNum = cars.stream()
				.parallel()
				.map( (Car car)-> car.getMaker() )
				.reduce(0,
				(result,carMaker)-> ++result,
				(finalResult,intermediateResult)->finalResult + intermediateResult);
		System.out.println("Number of car makers are : " + carMakerNum);

		
	}
}