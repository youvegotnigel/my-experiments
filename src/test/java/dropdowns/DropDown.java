package dropdowns;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class DropDown {

    private WebDriver driver;

    private By pageHeader = By.xpath("//div[contains(@class,'main-header')]");
    private By widgets = By.xpath("//div[contains(@class,'col-md-3')]//div[4]");
    private By selectMenu = By.xpath("//div[@class='element-list collapse show']//li[@id='item-8']");

    private By selectColor = By.xpath("//select[@id='oldSelectMenu']");
    private By selectValue = By.xpath("//div[@id='withOptGroup']//div[contains(@class,'css-1hwfws3')]");

    By multiSelect = By.id("cars");


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");

        navigateToSelectMenu();

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,1000)");
    }

    public void navigateToSelectMenu(){

        explicitWaitMethod(widgets);
        driver.findElement(widgets).click();

        explicitWaitMethod(selectMenu);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //driver.findElement(selectMenu).click();
        /**
         here element was only clickable when used the the JavascriptExecutor.
         more info : http://makeseleniumeasy.com/2020/05/25/elementclickinterceptedexception-element-click-intercepted-not-clickable-at-point-other-element-would-receive-the-click/
         */
        js.executeScript("arguments[0].click()", driver.findElement(selectMenu));

        explicitWaitMethod(pageHeader);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(pageHeader).getText(),"Select Menu","Incorrect Web Page Loaded");
        softAssert.assertAll();
    }


    //get first selected option
    @Test
    public void getFirstSelectedOption ()
    {
        SoftAssert softAssert = new SoftAssert();
        explicitWaitMethod(selectColor);

        Select select = new Select (driver.findElement(selectColor));
        System.out.println("Default Selected Color : " + select.getFirstSelectedOption().getText());
        softAssert.assertEquals(select.getFirstSelectedOption().getText(),"Red","Default Selected Color should be red");

        select.selectByVisibleText("Black");
        System.out.println("What's Your Favourite Color? " + select.getFirstSelectedOption().getText());
        softAssert.assertEquals(select.getFirstSelectedOption().getText(),"Black","Now Selected Color should be Black");

        select.selectByVisibleText("Blue");
        System.out.println("What's Your Favourite Color? " + select.getFirstSelectedOption().getText());
        softAssert.assertEquals(select.getFirstSelectedOption().getText(),"Blue","Now Selected Color should be Blue");

        softAssert.assertAll();
    }

    //get options
    @Test
    public void getOptions ()
    {
        SoftAssert softAssert = new SoftAssert();
        explicitWaitMethod(multiSelect);

        Select select = new Select (driver.findElement(multiSelect));
        List<WebElement> listCars = select.getOptions();
        System.out.println("Get All Cars");

        for (WebElement cars : listCars)
        {
            System.out.println(cars.getText());
        }

        softAssert.assertEquals(listCars.get(0).getText(),"Volvo");
        softAssert.assertEquals(listCars.get(1).getText(),"Saab");
        softAssert.assertEquals(listCars.get(2).getText(),"Opel");
        softAssert.assertEquals(listCars.get(3).getText(),"Audi");

        softAssert.assertAll();
    }

    //get all selected options
    @Test
    public void getAllSelectedOptions ()
    {
        SoftAssert softAssert = new SoftAssert();
        explicitWaitMethod(multiSelect);

        Select select = new Select (driver.findElement(multiSelect));

        /**
         * 3 methods to select an element from a dropdown
         *      1) select by index
         *      2) select by value
         *      3) select by visible text
        */
        select.selectByIndex(0); //Volvo
        select.selectByValue("audi"); //Audi (notice value is "audi" not "Audi")
        select.selectByVisibleText("Saab");

        List <WebElement> selectedCars = select.getAllSelectedOptions();
        System.out.println("What Cars Did You Select?");

        for (WebElement cars : selectedCars)
        {
            System.out.println(cars.getText());
        }

        softAssert.assertEquals(selectedCars.get(0).getText(),"Volvo");
        softAssert.assertEquals(selectedCars.get(1).getText(),"Saab");
        softAssert.assertEquals(selectedCars.get(2).getText(),"Audi");

        softAssert.assertAll();
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
