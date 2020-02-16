package rest_integration;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MyRestTest {


    @DataProvider
    public Object[][] myDataProvider() {
        return new Object[][]{
            {"aaa", "dffdds"},
            {"bbb", "ddsdsdsf"},
        };
    }

    @Test(dataProvider = "myDataProvider")
    public void myTest(String a, String b){
        //SOME REST TEST
        System.out.println(a);
        System.out.println(b);
    }

}
