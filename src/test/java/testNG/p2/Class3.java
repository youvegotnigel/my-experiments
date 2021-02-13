package testNG.p2;

import org.testng.annotations.Test;

public class Class3 {

    @Test(groups = {"otherClassGroup"})
    public void testTwo(){
        System.out.println("This is other class group test");
    }
}
