package ui_integration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNgTest {

    @BeforeClass
    public void initBrowserTests(){
        String browser = System.getProperty("browser");
        System.out.println("BoforeClass" + browser);
    }

    @Test
    @Parameters({"param1", "param2", "param3"})
    public void testWithExternalParam(String param1, String param2, String param3){
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(param3);
    }

    @Test(groups = {"chrome"})
    public void chromeOnlyTest(){
        System.out.println("I am Chrome Only test :), I am special ... will be filtered from System Property");
    }

    @Test(groups = {"firefox", "smoke"})
    public void firefoxOnlyTest(){
        System.out.println("I am Firefox Only test :) i will be filtered directly from XMl");
    }
}
