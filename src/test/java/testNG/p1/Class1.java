package testNG.p1;

import org.testng.annotations.Test;

public class Class1 {

    @Test(groups = {"config"})
    public void verifyConfig(){
        System.out.println("Pre configs are setup");
    }
}
