package edu.umb.cs681.hw001;


import java.util.ArrayList;

public class Car {

    private String brand;
    private String carModel;
    private int mileage;
    private int year;
    private int dominationCount;
    private float carPrice;

    public Car(String brand, String model, int mileage, int year,  float price) {
        this.brand = brand;
        this.carModel = model;
        this.mileage = mileage;
        this.year = year;
        this.carPrice = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getMileage() { return mileage; }

    public int getYear() {
        return year;
    }

    public float getCarPrice() {
        return carPrice;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        int count = 0;
        for (Car car : cars) {
            if (!car.equals(this)) {
                if ((this.getYear() >= car.getYear()) && (this.getMileage() <= car.getMileage()) && (this.getCarPrice() <= car.getCarPrice())) {
                    if ((this.getYear() > car.getYear()) || (this.getMileage() < car.getMileage()) || (this.getCarPrice() < car.getCarPrice())) {
                        count++;
                    }
                }
            }
        }
        this.dominationCount = count;
    }

    public int getDominationCount() {
        return this.dominationCount;
    }


}