package models;

public class Car {
    private String make;
    private String model;
    private String year;
    private CarSpec carSpec;

    public void setCarSpec(CarSpec carSpec) {
        this.carSpec = carSpec;
    }

    public Car(String make, String model, String year) {
        this.make = make;
        this.model = model.replaceAll("^-\\s","");
        this.year = year;
    }

    public Car(String make, CarSpec carSpec) {
        this.make = make;
        this.carSpec = carSpec;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public CarSpec getCarSpec() {
        return carSpec;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", carSpec=" + carSpec +
                '}';
    }
}



