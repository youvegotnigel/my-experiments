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

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarTest {

    private WebDriver driver;

    private By datePicker = By.id("datepicker");
    private By calendarUI = By.xpath("//div[@id='ui-datepicker-div']");
    private By monthYearValue = By.xpath("//div[@class='ui-datepicker-title']");

    private By calenderNextButton = By.xpath("//a[@title='Next']");


    //Expected Values dynamic date
//    private static String YEAR = "2021";  //Year has to be a future year
//    private static String MONTH = "June";
//    private static int DATE = 9;

    //Expected Values for current date
    private String YEAR = getCurrentYear();
    private String MONTH = getCurrentMonth();
    private int DATE = getCurrentDate();

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

        //Click on the date picker
        driver.findElement(datePicker).click();

        printValues();
    }

    @Test(dependsOnMethods = {"select_a_new_date"})
    public void verify_selected_date() {

        //Click on the date picker
        driver.findElement(datePicker).click();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(getYear(), YEAR, "Selected year is incorrect");
        softAssert.assertEquals(getMonth(), MONTH, "Selected month is incorrect");
        softAssert.assertEquals(Integer.parseInt(get_selected_date(DATE)),DATE, "Selected date is incorrect");

        softAssert.assertAll();

    }

    public String getMonthYearValue() {
        explicitWaitMethod(monthYearValue);
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
            explicitWaitMethod(By.xpath(xpath));
            driver.findElement(By.xpath(xpath)).click();
            return driver.findElement(By.xpath(xpath)).getText();

        } catch (Exception e) {

            System.err.println(date + " is an invalid date for " + MONTH + "!! in " + YEAR);
            //System.out.println(e);
        }

        return "-1";
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
            //verify_selected_date();
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        return options;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public String getCurrentYear(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);

        String year = strDate.split(" ")[2].trim();
        return year;
    }

    public String getCurrentMonth(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);

        String month = strDate.split(" ")[1].trim();
        return month;
    }

    public int getCurrentDate(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);

        String temp_date = strDate.split(" ")[0].trim();
        return Integer.parseInt(temp_date);
    }
}
