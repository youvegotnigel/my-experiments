package selenium_grid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridDemo {

    private String grid_url = "http://192.168.8.100:4444/wd/hub";


    @Test
    public void test01() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.WIN10);

        URL url = new URL(grid_url);

        WebDriver driver = new RemoteWebDriver(url,caps);
        driver.manage().window().maximize();
        System.out.println("Test 01 Started");
        driver.get("https://www.google.com/");
        Thread.sleep(3000);
        System.out.println("This is test 01 - "+"Title of the page is : " + driver.getTitle());
        driver.quit();
        System.out.println("Test 01 Ended");
    }

    @Test
    public void test02() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.WIN10);

        URL url = new URL(grid_url);

        WebDriver driver = new RemoteWebDriver(url,caps);
        driver.manage().window().maximize();
        System.out.println("Test 02 Started");
        driver.get("https://www.google.com/");
        Thread.sleep(3000);
        System.out.println("This is test 02 - "+"Title of the page is : " + driver.getTitle());
        driver.quit();
        System.out.println("Test 02 Ended");
    }

    @Test
    public void test03() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.WIN10);

        URL url = new URL(grid_url);

        WebDriver driver = new RemoteWebDriver(url,caps);
        driver.manage().window().maximize();
        System.out.println("Test 03 Started");
        driver.get("https://www.google.com/");
        Thread.sleep(3000);
        System.out.println("This is test 03 - "+"Title of the page is : " + driver.getTitle());
        driver.quit();
        System.out.println("Test 03 Ended");
    }


}
