package testNG.p2;

import org.testng.annotations.Test;

public class Class2 {

    @Test(dependsOnGroups={"sameClassGroup","otherClassGroup"})
    public void doTest(){
        System.out.println("this is do test");
    }

    @Test(groups = {"sameClassGroup"})
    public void testOne(){
        System.out.println("This is same class group test");
    }
}
