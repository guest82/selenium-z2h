package ui_integration;

import static ui_integration.CommonVariables.BASE_URL;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import builder.ChromeStandaloneDriver;

public class BaseTest_1 {

    ChromeStandaloneDriver chromeStandaloneDriver = new ChromeStandaloneDriver();

    @BeforeClass
    public void beforeClassInit(){
        chromeStandaloneDriver.start();
        chromeStandaloneDriver.getDriver().get(BASE_URL);
    }

    @AfterClass
    public void closeBrowser(){
        chromeStandaloneDriver.getDriver().close();
    }

}
