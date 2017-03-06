package framework;

import org.apache.log4j.Logger;

public class MyLogger {
    private Logger logger;

    public MyLogger(Class clazz) {
        logger = Logger.getLogger(clazz);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void stepInfo(String stepNumber, String stepName) {
        info("\n====-----[ "+stepNumber+" "+stepName+" ]-----====");
    }

    public void degub(String message) {
        logger.debug(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void startLog() {
        info("\n-------------------------------------");
        info("Test case: "+Thread.currentThread().getStackTrace()[2].getClassName());
        info("-------------------------------------");
        info("--------==[ Preconditions ]==--------\n");
    }

    public void stopLog() {
        info("\n***************************************************");
        info("***** Test case: "+Thread.currentThread().getStackTrace()[2].getClassName()+" Passed! *****\n");
    }


}
