package testNG;

import com.github.lkkushan101.accessilenium.accessilenium;
import org.testng.annotations.Test;

import java.io.IOException;

public class AccessibilityReport {


    @Test
    public void test() throws IOException
    {
        accessilenium.TheAccessibilityReport.testAccessibility("https://www.facebook.com");
    }
}

