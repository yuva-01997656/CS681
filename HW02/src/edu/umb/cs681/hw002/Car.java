package edu.umb.cs681.hw002;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unused")
public class Car {
    private String carModel;
    private String company;
    private int speed;
    private double price;
    private int year;

    public Car(String model, String company, int speed, double price, int year) {
        this.carModel = model;
        this.company = company;
        this.speed = speed;
        this.price = price;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car[ " + company + " model ]";
    }


    public String getCarModel() {
        return carModel;
    }

    public String getCompany() {
        return company;
    }

    public int getSpeed() {
        return speed;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>(Arrays.asList(
                    new Car("X9","BMW",  300, 300000.00, 2022),
                    new Car("A3", "Audi", 250, 450000.00, 2019),
                    new Car("Benz", "Mercedes", 210, 200000.00, 2021),
                    new Car("X6", "BMW",  240, 250000.00, 2018),
                    new Car("A2", "Audi", 290, 350000.00, 2017)
        ));

        // car with min price
        Car lowestPriceCar = cars.stream()
                .min(Comparator.comparing(Car::getPrice))
                .orElseThrow(() -> new RuntimeException("No cars found"));

        System.out.printf("The lowest price car is %s with price %.2f %n", lowestPriceCar, lowestPriceCar.getPrice());

        // Car with max price
        Car maxPriceCar = cars.stream()
                .max(Comparator.comparing(Car::getPrice))
                .get();

        System.out.printf("The highest price car is %s with price %.2f %n", maxPriceCar, maxPriceCar.getPrice());

        // Average price of cars using reduce
        double averagePrice = cars.stream().map(Car::getPrice)
                .reduce(0.0, Double::sum, (finalResult,
                                          intermediateResult) -> finalResult)/cars.size();

        System.out.printf("Average price of  cars is: %.2f", averagePrice);
    }
}