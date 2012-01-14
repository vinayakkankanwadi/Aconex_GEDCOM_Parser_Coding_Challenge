/**
 * 
Class XMLWriter provides XML Writing functionality
It takes GEDCOM parsed input and converts it into XML file
 *
 * @version

1.0 14 Jan 2012
 * @author

Vinayak Kankanwadi
*/

import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class XMLWriter
{
	private Writer out;

	public XMLWriter() throws IOException
	{
		out = new BufferedWriter (new OutputStreamWriter(System.out));
	}

	public XMLWriter(String strFilename) throws IOException
	{
		if ( strFilename != null ) 
		{
			out = new BufferedWriter(new FileWriter(strFilename));
		}
	}

	public void createNode(Parse obj) throws IOException
	{
		indentation(obj);
		out.write("<"+obj.getStrTag());
		if( obj.getStrId() != null )
		{	
			out.write(" id="+obj.getStrId());
		}
		if( obj.getStrValue() != null )
		{	
			out.write(" value=\""+obj.getStrValue()+"\"");
		}
		out.write(">\n");
	}

	public void childNode(Parse obj) throws IOException
	{
		indentation(obj);
		out.write("<"+obj.getStrTag()+">");
		if ( obj.getStrValue() != null )
		{
			out.write(obj.getStrValue());
		}
		out.write("</"+obj.getStrTag()+">\n");
	}

	public void endNode(Parse obj) throws IOException
	{
		indentation(obj);
		out.write("</"+obj.getStrTag()+">\n");
		out.flush();
	}

        protected void indentation(Parse obj) throws IOException
        {
                String separator = "    ";
                for ( int i = 0 ; i<=obj.getIntLevel();i++ )
                {
                        out.write (separator);
                }
        }
}
