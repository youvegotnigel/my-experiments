package javascript;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class the100meterScroll {

    private WebDriver driver;

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void scroll_down_test() throws InterruptedException {

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("https://the100meterscroll.com/");
        System.out.println("Page title is " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"THE 100 METER SCROLL","page title not matching");

        //Maximize window
        driver.manage().window().maximize();

        By start = By.xpath("//div[@id='startTxt']");
        By rank = By.xpath("//div[@id='rankCoin']");
        By fastTime = By.xpath("//div[@id='finalTime']");
        By field = By.xpath("//div[@id='field']");

        //click on start
        explicitWaitMethod(start);
        driver.findElement(start).click();

        //wait 3 seconds
        Thread.sleep(3000);

        //String script = "arguments[0].scrollIntoView(true)";
        //js.executeScript(script, driver.findElement(rank));
        String transformScript = "document.getElementById('field')";

        int count = 0;

        while (!driver.findElement(rank).isDisplayed()){
            System.out.println("Inside while loop : " + count);
            js.executeScript("window.scrollBy(0,500)");
            System.out.println(js.executeScript(transformScript,driver.findElement(field)));
            count++;
        }


        explicitWaitMethod(rank);
        System.out.println("Your rank : " + driver.findElement(rank).getText());

        //explicitWaitMethod(fastTime);
        System.out.println("Your time : " + driver.findElement(fastTime).getText());
    }

    public void explicitWaitMethod(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
