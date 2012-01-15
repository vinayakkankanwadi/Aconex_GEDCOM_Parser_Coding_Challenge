/**
 *
Class TestCasesGEDCOM2XML used to test functionality of GEDCOM to XML file
 *
 * @version

1.0 14 Jan 2012
 * @author

Vinayak Kankanwadi
*/

import org.junit.*;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import static org.junit.Assert.*;

public class TestCasesGEDCOM2XML extends Assert {
 
    public static void executeConvert( String strInputFile, 
		String strOutputFile, String strResultFile ) throws IOException {
        System.out.println("In TestCasesGEDCOM2XML::executeConvert");

	GEDCOM2XML g2x = new GEDCOM2XML(strInputFile,strOutputFile);
	g2x.convert();

	assertTrue("Files should be same", 
		fileCompare(strOutputFile,strResultFile));

    }

    public static boolean fileCompare(String strFileOne,String strFileTwo) 
							throws IOException {
        System.out.println("In TestCasesGEDCOM2XML::fileCompare");

	BufferedReader brOne = new BufferedReader(new FileReader(strFileOne));
	BufferedReader brTwo = new BufferedReader(new FileReader(strFileTwo));

	String strLine;
	while((strLine = brOne.readLine()) != null)
	{
		if(!strLine.equals(brTwo.readLine()))
		{
			return false;
		}
	}
	if(brTwo.readLine() != null)
	{
			return false;
	}
	return true;
    }
 
    @BeforeClass
    public static void BeforeClass() {
        System.out.println("In TestCasesGEDCOM2XML::BeforeClass");
    }
 
    @Before
    public void setUp() throws Exception {
        System.out.println("In TestCasesGEDCOM2XML::setUp");
    }
 
    @Test
    public void testVariableWhiteSpaceBtLevelAndTag() throws IOException, Exception {
        System.out.println("In TestCasesGEDCOM2XML::testVariableWhiteSpacebtLevelAndTag");

	String strInputFile = "TestCasesGEDCOM2XMLTestDataVariableWhiteSpaceBetweenLevelAndTag.txt";
	String strOutputFile = "TestCasesGEDCOM2XMLTestDataVariableWhiteSpaceBetweenLevelAndTagOutput.xml";
	String strResultFile = "TestCasesGEDCOM2XMLTestDataVariableWhiteSpaceBetweenLevelAndTagResult.xml";

	executeConvert(strInputFile,strOutputFile,strResultFile);
    }

    @Test
    public void testBlankLines() throws IOException, Exception {
        System.out.println("In TestCasesGEDCOM2XML::testBlankLines");

	String strInputFile = "TestCasesGEDCOM2XMLTestDataBlankLines.txt";
	String strOutputFile = "TestCasesGEDCOM2XMLTestDataBlankLinesOutput.xml";
	String strResultFile = "TestCasesGEDCOM2XMLTestDataBlankLinesResult.xml";

	executeConvert(strInputFile,strOutputFile,strResultFile);
    }

    @Test
    public void testConvertGEDCOM2XML() throws IOException, Exception {
        System.out.println("In TestCasesGEDCOM2XML::testConvertGEDCOM2XML");

	String strInputFile = "GEDCOM_Parser_Challenge_sample_data.txt";
        String strOutputFile = "GEDCOM_Parser_Challenge_Output.xml";
	String strResultFile = "GEDCOM_Parser_Challenge_result.xml";

        File fileOutput = new File(strOutputFile);
	assertFalse("File should not exist", fileOutput.exists());

	executeConvert(strInputFile,strOutputFile,strResultFile);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("In TestCasesGEDCOM2XML::tearDown");
    }
 
    @AfterClass
    public static void AfterClass() {
        System.out.println("In TestCasesGEDCOM2XML::AfterClass");
    }

}
