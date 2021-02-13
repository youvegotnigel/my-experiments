package performance;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class PerformanceChromeTest {

    private static final int NUMBER_OF_BROWSERS = 3;
    private List<WebDriver> driverList = new ArrayList<>(NUMBER_OF_BROWSERS);

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setupTest() {
        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            driverList.add(new ChromeDriver());
        }
    }

    @AfterClass
    public void teardown() {
        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            driverList.get(i).quit();
        }
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        final CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);

        for (final WebDriver driver : driverList) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        singleTestExcution(driver);
                    } catch (Throwable e) {
                        errorCollector.addError(e);
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    private void singleTestExcution(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        By searchInput = By.id("searchInput");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys("Software");
        By searchButton = By.id("searchButton");
        wait.until(elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Computer software"));
    }
}
