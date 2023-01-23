package old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Firefox {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/home/hs/Downloads/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        options.addArguments("Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/109.0");
        options.setBinary("/home/hs/Downloads/firefox/firefox");
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.auchan.ru/product/kdyayco_kurinoe_s0_30_sht/");
    }
}
