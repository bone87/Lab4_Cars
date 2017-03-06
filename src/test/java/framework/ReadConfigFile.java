package framework;

import framework.BrowserFactory.BrowserType;

import java.io.*;
import java.util.Properties;

public class ReadConfigFile {
    private BrowserType type;
    private String url;
    private long timeForImplicitlyWait;
    private long timeForExplicitWait;
    private String lo4jPath;

    public ReadConfigFile() {
        Properties prop = new Properties();
        try(InputStream is = new FileInputStream("src/test/resources/config/config.properties")) {
            prop.load(is);

            String browser = prop.getProperty("browser");
            if (browser.equals("firefox")) {
                type = BrowserType.FIREFOX;
            } else if (browser.equals("chrome")) {
                type = BrowserType.CHROME;
            }
            url = prop.getProperty("url");
            timeForImplicitlyWait = Long.parseLong(prop.getProperty("implicitlyWait"));
            timeForExplicitWait = Long.parseLong(prop.getProperty("explicitWait"));
            lo4jPath = prop.getProperty("log4jPath");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BrowserType getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public long getTimeForImplicitlyWait() {
        return timeForImplicitlyWait;
    }

    public long getTimeForExplicitWait() {
        return timeForExplicitWait;
    }

    public String getLo4jPath() {
        return lo4jPath;
    }

}
