package old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;

public class Chrome {
    static Wait<WebDriver> wait;
    public static void main(String[] args) throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver","/home/hs/Downloads/chromedriver_linux64(1)/chromedriver1");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome-stable");
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        //options.setExperimentalOption("general.useragent.override")
        options.addArguments("Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/109.0");

        ChromeDriver driver1 = new ChromeDriver(options);
        driver1.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        //driver1.executeScript("Network.setUserAgentOverride', {\"userAgent\": 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.53 Safari/537.36");
        driver1.get("https://www.okeydostavka.ru/msk/khleb-khlebnyi-dom-tostovyi-v-narezke-500g");
        //driver1.get("https://www.americanairlines.ie/intl/ie/index.jsp");
        driver1.manage().window().maximize();
        System.out.println(driver1.getPageSource());
    }
}
