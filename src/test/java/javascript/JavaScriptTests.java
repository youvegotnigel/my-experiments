package javascript;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class JavaScriptTests {

    private WebDriver driver;

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //driver.manage().window().maximize();
        //driver.get("https://demoqa.com/automation-practice-form");

    }

    @Test
    public void perform_a_sleep_test(){

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("http://demo.guru99.com/V4/");

        //Maximize window
        driver.manage().window().maximize();

        //Set the Script Timeout to 20 seconds
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        //Declare and set the start time
        long start_time = System.currentTimeMillis();

        //Call executeAsyncScript() method to wait for 5 seconds
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

        //Get the difference (currentTime - startTime)  of times.
        System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));
    }

    @Test
    public void perform_login() throws InterruptedException {

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("http://demo.guru99.com/V4/");
        System.out.println("Page title is " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Guru99 Bank Home Page","error title");

        WebElement username =driver.findElement(By.name("uid"));
        WebElement password =driver.findElement(By.name("password"));
        WebElement button =driver.findElement(By.name("btnLogin"));


        //Login using send keys method
//        username.sendKeys("admin");
//        password.sendKeys("123");

        //Login using javascript
        js.executeScript("arguments[0].value='admin'",username); //set username to admin
        js.executeScript("arguments[0].value=arguments[1]",password,"123"); //set password to 123

        //Perform Click on LOGIN button using JavascriptExecutor
        js.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        System.out.println("Before Accept Alert Text = " + alertText);
        Assert.assertEquals(alertText,"User or Password is not valid");

        //Thread.sleep(3000);
        alert.accept(); //accept alert
        //Thread.sleep(3000);
        System.out.println("Current Url = " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),"http://demo.guru99.com/V4/index.php");
    }

    @Test
    public void test_alert_box(){
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        System.out.println("Page title is " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Selenium Easy Demo - Automate All Scenarios","page title not matching");

        WebElement button = driver.findElement(By.xpath("//button[@class='btn btn-default']"));

        //click on the button
        Assert.assertEquals(button.getText(),"Click me!");
        button.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals(alertText,"I am an alert box!");
        alert.accept();

    }

    @Test
    public void test_confirm_box_accept(){
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        System.out.println("Page title is " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Selenium Easy Demo - Automate All Scenarios","page title not matching");

        WebElement button = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg'][normalize-space()='Click me!']"));
        WebElement result = driver.findElement(By.xpath("//p[@id='confirm-demo']"));

        //assert button text
        Assert.assertEquals(button.getText(),"Click me!");
        button.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals(alertText,"Press a button!");

        //Accept alert
        alert.accept();
        Assert.assertEquals(result.getText(),"You pressed OK!");

    }

    @Test
    public void test_confirm_box_reject(){
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        System.out.println("Page title is " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Selenium Easy Demo - Automate All Scenarios","page title not matching");

        WebElement button = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg'][normalize-space()='Click me!']"));
        WebElement result = driver.findElement(By.xpath("//p[@id='confirm-demo']"));

        //click on the button
        Assert.assertEquals(button.getText(),"Click me!");
        button.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals(alertText,"Press a button!");

        //Accept alert
        alert.dismiss();
        Assert.assertEquals(result.getText(),"You pressed Cancel!");

    }

    @Test
    public void test_prompt_box(){
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        System.out.println("Page title is " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Selenium Easy Demo - Automate All Scenarios","page title not matching");

        WebElement button = driver.findElement(By.xpath("//button[normalize-space()='Click for Prompt Box']"));
        WebElement result = driver.findElement(By.xpath("//p[@id='prompt-demo']"));

        //click on the button
        Assert.assertEquals(button.getText(),"Click for Prompt Box");

        //Perform click on button using JavascriptExecutor
        js.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals(alertText,"Please enter your name");

        String target = "selenium is fun";

        //send the target to alert box
        alert.sendKeys(target);

        //Accept alert
        alert.accept();
        Assert.assertEquals(result.getText(),"You have entered \'" + target + "\' !");

    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
