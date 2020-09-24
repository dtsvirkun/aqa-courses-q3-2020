package io.ctdev.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup2() {
        System.out.println("this is super before suite");
    }

    @BeforeClass
    public void beforeEachCLass() {
        System.out.println("Before each class");
    }


    @AfterSuite
    public void teardown() {
        System.out.println("This is after suite");

    }

}
