package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class baseTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){

        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void test01(){
        driver.get("https://www.hyrtutorials.com/");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title,"H Y R Tutorials - H Y R Tutorials");
    }

    //validate all url connections
    @Test
    public void test02() throws IOException, InterruptedException {

        test01();
        Thread.sleep(1000);
        Set<String> brokenURLs = new HashSet<String>();

        By anchorLinks = By.tagName("a"); //get all the elements with anchor links
        List<WebElement> allLinks = driver.findElements(anchorLinks); //get all the elements with anchor links

        System.out.println("All urls : "+allLinks.size());
        for(WebElement a : allLinks){
            String urlLink = a.getAttribute("href");  // get the url names inside href attribute
            //System.out.println(urlLink);   //print all the found urls

            URL url = new URL(urlLink);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() != 200)
                {brokenURLs.add(urlLink);}
//                System.err.println(urlLink + " - " + httpURLConnection.getResponseCode() + " - " + httpURLConnection.getResponseMessage());
//            else
//                System.out.println(urlLink + " - " + httpURLConnection.getResponseMessage());


            httpURLConnection.disconnect();
        }

        System.out.println("broken urls: "+ brokenURLs.size());
        //printout the broken urls
        for(String a: brokenURLs){
            System.err.println(a);
        }
    }

    @AfterMethod public void tearDown(){
        driver.quit();
    }
}
