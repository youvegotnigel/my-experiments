package javascript;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    @Test
    public void scroll_down_test(){

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Launching the Site.
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
        System.out.println("Page title is " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"The Internet","page title not matching");

        //Maximize window
        driver.manage().window().maximize();

        By textBlocks = By.className("jscroll-added");

        //int paragraphCount = driver.findElements(textBlocks).size();
        int INDEX = 10; //expected paragraph number


        //String script = "arguments[0].scrollIntoView(true)";
        //js.executeScript(script, driver.findElement(rank));
        //String transformScript = "document.getElementById('field')";

        int count = 0;

        while (getParagraphCount(textBlocks) < INDEX){
            //System.out.println("Inside while loop : " + count);
            //System.out.println("Index count : " + INDEX);
            //System.out.println("Paragraph count : " + getParagraphCount(textBlocks));

            //js.executeScript("window.scrollBy(0,500)");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            count++;
        }

        System.out.println("Loop count : " + count);

    }

    private int getParagraphCount(By elemement){
        return driver.findElements(elemement).size();
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
