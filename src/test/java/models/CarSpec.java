package models;

public class CarSpec {
    private String engine;
    private String transmission;

    public CarSpec(String engine, String transmission) {
        this.engine = engine;
        this.transmission = transmission;
    }

    public String getEngine() {
        return engine;
    }

    public String getTransmission() {
        return transmission;
    }

    @Override
    public String toString() {
        return "CarSpec{" +
                "engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                '}';
    }
}
