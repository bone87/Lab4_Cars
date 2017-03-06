package framework;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SoftAssert {

    private MyLogger log = new MyLogger(SoftAssert.class);
    private List<String> errors = new ArrayList<String>();

    public void assertEqual(Object exp, Object act, String message) {
        if (!exp.equals(act)) {
            errors.add(message + " -> FAILED");
        } else {
            log.info(message);
            System.out.println(exp + " -> " + act);
        }
    }

    public void assertActContainsExp(String exp, String act, String message) {
        String text = "actual: " + act + " -> expected: " + exp;
        if (!act.contains(exp)) {
            errors.add(message + " FAILED, " + text);
            log.error(message + " FAILED, " + text);
        } else {
            log.info(message + " " + text);
        }
    }

    public void assertAll() {
        if (errors.size() > 0) {
            log.info("\nERRORS: " + errors.size());
            for (int k = 0; k < errors.size(); k++) {
                log.info(k + 1 + ". " + errors.get(k));
            }
            Assert.assertFalse(true);
        }
    }

}
