package calendar;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CalendarTest {

    private WebDriver driver;

    private By datePicker = By.id("datepicker");
    private By calendarUI = By.xpath("//div[@id='ui-datepicker-div']");
    private By monthYearValue = By.xpath("//div[@class='ui-datepicker-title']");

    private By calenderNextButton = By.xpath("//a[@title='Next']");


    //Expected Values
    private static String YEAR = "2024";  //Year has to be a future year
    private static String MONTH = "February";
    private static int DATE = 29;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");

    }

    @Test
    public void verify_calender_is_open() {

        //Click on the date picker
        driver.findElement(datePicker).click();

        //Verify if calender is open
        explicitWaitMethod(calendarUI);
    }

    @Test(dependsOnMethods = {"verify_calender_is_open"})
    public void select_a_new_date() {

        select_any_month_year_date(YEAR, MONTH, DATE);

        printValues();
    }

    //@Test(dependsOnMethods = {"select_a_new_date"})
    public void verify_selected_date() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(getYear(), YEAR, "Selected year is incorrect");
        softAssert.assertEquals(getMonth(), MONTH, "Selected month is incorrect");
        //softAssert.assertEquals(get_selected_date(DATE),DATE, "Selected date is incorrect");

        softAssert.assertAll();

    }

    public String getMonthYearValue() {
        return driver.findElement(monthYearValue).getText();
    }

    public String getMonth() {
        return getMonthYearValue().split(" ")[0].trim();
    }

    public String getYear() {
        return getMonthYearValue().split(" ")[1].trim();
    }

    public String get_selected_date(int date) {

        String xpath = "//a[normalize-space()='" + date + "']";

        try {
            driver.findElement(By.xpath(xpath)).click();
            return driver.findElement(By.xpath(xpath)).getText();

        } catch (Exception e) {

            System.err.println(date + " is an invalid date for " + MONTH + "!! in " + YEAR);
            //System.out.println(e);
        }

        return null;
    }

    public void select_any_month_year_date(String year, String month, int date) {

        if (month.equals("February") && date >= 30) {
            System.err.println(date + " is an invalid date for " + month + "!!");
            return;
        } else if (date > 31) {
            System.err.println(date + " is an invalid date for " + month + "!!");
            return;
        } else {

            while (!(getMonth().equals(month) && getYear().equals(year))) {
                //Click on next button until condition is true
                driver.findElement(calenderNextButton).click();
            }

            get_selected_date(date);
            verify_selected_date();
        }


    }

    public void printValues() {

        //print the month, year and date
        System.out.println("Month Year Value : " + getMonthYearValue());  //March 2021
        System.out.println("Month Value : " + getMonth());  //March
        System.out.println("Year Value : " + getYear());  //2021
        System.out.println("Date Value : " + get_selected_date(DATE));  //4
    }

    public void explicitWaitMethod(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return options;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
