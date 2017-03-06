package models;

import framework.MyLogger;
import steps.CompareCarsStep;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class CarStore {

    private static Map<String,Car> garage = new HashMap<String,Car>();
    private static MyLogger log = new MyLogger(CompareCarsStep.class);

    public static void addCarToGarage(String name, Car car) {
        garage.put(name,car);
        log.info("The car '"+name+"' has added to garage");
    }

    public static Car getCarFromGarage(String name) {
        if (!garage.containsKey(name)) {
            log.error("Car "+name+" doesn't exists");
            Assert.fail("Car '"+name+"' doesn't exists");
        }
        return garage.get(name);
    }

    public static void viewGarage() {
        System.out.println("The garage has "+garage.size()+" cars");
        for (Map.Entry<String,Car> carEntry : garage.entrySet()) {
            System.out.println(carEntry);
        }
    }

    public static Map<String, Car> getAllCars() {
        return garage;
    }
}
