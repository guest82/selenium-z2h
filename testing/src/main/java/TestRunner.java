import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

public class TestRunner {

    public static void main(String[] args) throws IOException {
        TestNG testng ;
        List<XmlSuite> suite;
        File xmlFile;
        String xmlFilePath = System.getProperty("testNGxmlPath");
        System.out.println(xmlFilePath);
        if (xmlFilePath != null) {
            testng = new TestNG();
            xmlFile = new File(xmlFilePath);
            if (xmlFile.exists()) {
                suite = (List<XmlSuite>) (new Parser(xmlFilePath).parse());
                testng.setXmlSuites(suite);
                testng.run();
            }
        }else {
            System.out.println("ERROR : Resource path to xml file was not provided");
        }
    }
}
