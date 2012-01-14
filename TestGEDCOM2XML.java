/**
 *
Class TestGEDCOM2XML used to test functionality of GEDCOM to XML file
 *
 * @version

1.0 14 Jan 2012
 * @author

Vinayak Kankanwadi
*/

import java.io.IOException;

public class TestGEDCOM2XML
{
	public TestGEDCOM2XML()
	{
	}
	
	public static void main(String[] args) throws IOException, Exception
	{
		if(args.length < 2)
		{
			System.out.println("Usage: java TestGEDCOM2XML <gedcominputfilename> <xmloutputfilename>");
		}

		GEDCOM2XML g2x = new GEDCOM2XML(args[0],args[1]);
		g2x.convert();
	}
}
