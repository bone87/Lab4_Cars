package framework;

public class Property {
    private static ReadConfigFile instance = null;

    private Property(){}

    public static ReadConfigFile getProperties(){
        if (instance == null) {
            instance = new ReadConfigFile();
        }
        return instance;
    }

}
