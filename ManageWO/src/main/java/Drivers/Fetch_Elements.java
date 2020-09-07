package Drivers;

import Services.AppEnv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

/**
 * This class will manage the Selenium libraries used to get elements
 */
public class Fetch_Elements {
    private static Fetch_Elements fetch_elements = new Fetch_Elements();
    private static AppEnv appEnv = new AppEnv();

    private Fetch_Elements() {
    }

    /* Static 'instance' method */
    public static Fetch_Elements getInstance(AppEnv appEnv) {
        Fetch_Elements.appEnv = appEnv;
        return fetch_elements;
    }

    public WebElement GetObj(List<String> locator, List<String> locatorValue) {
        WebElement webElement = null;
        int Index = 0;
        RemoteWebDriver localDriver = appEnv.getDriver();
        try {
            for (String loc : locator) {
                if (loc.equalsIgnoreCase("id")) {
                    webElement = localDriver.findElement(By.id(locatorValue.get(Index)));
                }
                if (loc.equalsIgnoreCase("xpath")) {
                    webElement = localDriver.findElement(By.xpath(locatorValue.get(Index)));
                }
                if (loc.equalsIgnoreCase("css")) {
                    webElement = localDriver.findElement(By.cssSelector(locatorValue.get(Index)));
                }
                if (loc.equalsIgnoreCase("name")) {
                    webElement = localDriver.findElement(By.name(locatorValue.get(Index)));
                }
            }
        } catch (Exception exh) {
            System.out.println("Fetcher Exception");
        }
        return webElement;
    }

    public WebElement GetObj(String locator, String locatorValue) {
        WebElement webElement = null;
        RemoteWebDriver localDriver = appEnv.getDriver();
        try {
                if (locator.equalsIgnoreCase("id")) {
                    webElement = localDriver.findElement(By.id(locatorValue));
                }
                if (locator.equalsIgnoreCase("xpath")) {
                    webElement = localDriver.findElement(By.xpath(locatorValue));
                }
                if (locator.equalsIgnoreCase("css")) {
                    webElement = localDriver.findElement(By.cssSelector(locatorValue));
                }
                if (locator.equalsIgnoreCase("name")) {
                    webElement = localDriver.findElement(By.name(locatorValue));
                }

        } catch (Exception exh) {
            System.out.println("Fetcher Exception: Element was not located against given parameter");
        }
        return webElement;
    }
}
/*--------------------------------------------------------------------------------------------------------------------*/