package ui_integration;

import static ui_integration.CommonVariables.BASE_URL;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import builder.SeleniumDriver;

public class BaseTest_2 {
    SeleniumDriver seleniumDriver;


    /*
    -DhubUrl=http://10.54.245.62:4444 -Dbrowser=chrome
    -DhubUrl=http://localhost:4444 -Dbrowser=chrome
    https://opensource.zalando.com/zalenium/

    docker pull elgalu/selenium
    docker pull dosel/Zalenium

    winpty docker run --rm -ti --name zalenium -p 4444:4444 -v //var/run/docker.sock://var/run/docker.sock -v //tmp/videos://home/seluser/videos --privileged dosel/zalenium start
    docker stop zalenium
     */
    @BeforeClass(alwaysRun = true)
    public void beforeClassInit() throws Exception {
        String browser = System.getProperty("browser");
        String hubUrl = System.getProperty("hubUrl");
        if (hubUrl != null){
            seleniumDriver = new SeleniumDriver(hubUrl);
            System.out.println("-I- on hub " + hubUrl);
        }else{
            seleniumDriver = new SeleniumDriver();
            System.out.println("-I- standalone");
        }

        if (browser != null){
            seleniumDriver.start(browser);
        }else{
            seleniumDriver.start();
        }

        System.out.println("-I- Running on " + browser);


        seleniumDriver.getDriver().get(BASE_URL);
    }

    @AfterClass
    public void closeBrowser(){
        seleniumDriver.getDriver().close();
    }
}
