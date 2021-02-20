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
import org.testng.asserts.SoftAssert;

public class JavascriptCommands {

    private WebDriver driver;

    private By LargeAndDeepDom = By.linkText("Large & Deep DOM");
    private By PageHeaderName = By.cssSelector("div[class='example'] h3");
    private By LargeAndDeepDomPageFooter = By.xpath("//div[@class='large-4 large-centered columns']//div[1]");
    private By table = By.id("large-table");

    private By JavaScriptAlert = By.linkText("JavaScript Alerts");


    @BeforeClass
    public void setup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        //System.out.println(title);
        Assert.assertEquals(title,"The Internet");

    }

    @Test(priority = 1)
    public void scroll_to_bottom_of_the_page(){

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        driver.findElement(LargeAndDeepDom).click();
        Assert.assertEquals(driver.findElement(PageHeaderName).getText(), "Large & Deep DOM");

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //System.out.println(driver.findElement(pageFooter).getText());
        Assert.assertEquals(driver.findElement(LargeAndDeepDomPageFooter).getText(),"Powered by Elemental Selenium");

    }

    @Test(priority = 2)
    public void scroll_to_top_of_the_page(){

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Assert.assertEquals(driver.findElement(PageHeaderName).getText(), "Large & Deep DOM");
    }

    @Test(priority = 3)
    public void scroll_to_a_specific_element(){

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(table));

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(table));

        System.out.println(driver.findElement(table).getText());
        //Assert.assertEquals(driver.findElement(table).getText(),"Table");

    }

    @Test(priority = 4)
    public void click_on_an_element(){

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        driver.navigate().back();

        js.executeScript("arguments[0].click();", driver.findElement(JavaScriptAlert));
        Assert.assertEquals(driver.findElement(PageHeaderName).getText(), "JavaScript Alerts");

    }

    @Test(priority = 5)
    public void refresh_the_browser(){

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("history.go(0)");

    }

    @Test(priority = 6)
    public void going_back_X_no_of_pages(){

        SoftAssert softAssert = new SoftAssert();

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        driver.get("https://www.selenium.dev/");
        softAssert.assertEquals(driver.getTitle(), "SeleniumHQ Browser Automation");

        driver.get("https://www.facebook.com");
        softAssert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

        driver.get("https://twitter.com/");
        softAssert.assertEquals(driver.getTitle(), "Twitter. It’s what’s happening / Twitter");

        driver.get("https://www.google.com");
        softAssert.assertEquals(driver.getTitle(), "Google");

        //go back 3 pages
        js.executeScript("history.go(-3)");
        softAssert.assertEquals(driver.getTitle(), "SeleniumHQ Browser Automation");

        softAssert.assertAll();
    }


    @Test(priority = 7)
    public void testing_session_storage(){

        String itemName = "football";
        String itemValue = "10";

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("window.sessionstorage.setitem(itemName, itemValue)");


        String result = (String) js.executeScript(String.format("return window.sessionstorage.getItem('%s');", itemValue));

        System.out.println(result);

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
