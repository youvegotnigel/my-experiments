package calendar;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalendarTest {

    private WebDriver driver;
    private By datePicker = By.id("datepicker");
    private By calendarUI = By.xpath("//div[@id='ui-datepicker-div']");

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");

    }

    @Test
    public void findCalendar(){

        //Click on the date picker
        driver.findElement(datePicker).click();

        //Verify if calender is open
        explicitWaitMethod(calendarUI);

        //Thread.sleep(5000);


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
