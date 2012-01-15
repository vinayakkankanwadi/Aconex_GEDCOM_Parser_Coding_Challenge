/**
 *
Class TestSuit to test functionality of GEDCOM to XML file
 *
 * @version

1.0 14 Jan 2012
 * @author

Vinayak Kankanwadi
*/

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestCasesGEDCOM2XML.class
}) public class GEDCOMTestSuite {

    public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { GEDCOMTestSuite.class });
    }
}
