package old;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.datatype.Duration;

public class PhantomJsUtils {
    public static void main(String[] args) throws Exception { // JBrowserDriver part
        //homePage();
        System.setProperty("phantomjs.binary.path","/home/hs/Downloads/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        PhantomJSDriver driver = new PhantomJSDriver(caps);
        WebDriverWait wait = new WebDriverWait(driver, 2);
        driver.get("https://www.auchan.ru/product/kdyayco_kurinoe_s0_30_sht/");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productName")));
        System.out.println(driver.getPageSource());
    }

    public static void homePage() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            final HtmlPage page = webClient.getPage("http://okeydostavka.ru/msk/molochnye-produkty-syry-iaitso/molochnye-produkty/moloko-i-slivki/bzmzh-moloko-utp-domik-v-derevne-3-2-925ml-tba");

            System.out.println(page.getTitleText());
            System.out.println(page.getBody());

            page.executeJavaScript("http://okeydostavka.ru/__qrator/qauth_utm_v2.js");
            System.out.println("JS exec");
            System.out.println(page.asText());
            System.out.println(page.getBody());
        }
    }
}