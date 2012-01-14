/**
 *
Class GEDCOM2XML provides GEDCOM to XML convert functionality
It takes GEDCOM input, parses and converts it into XML file
 *
 * @version

1.0 14 Jan 2012
 * @author

Vinayak Kankanwadi
*/

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class GEDCOM2XML
{
	private BufferedReader m_input;
	private XMLWriter wr ;
	private Stack<Parse> m_NodeStack ;
	private String m_Root ;

	public GEDCOM2XML(String strInputFilename ) throws IOException
	{
		m_input = new BufferedReader(new FileReader(strInputFilename));
		wr = new XMLWriter();
		m_NodeStack = new Stack<Parse>();
		m_Root = "-1 GEDCOM" ;
	}

	public GEDCOM2XML(String strInputFilename , String strOutputFilename) throws IOException
	{
		m_input = new BufferedReader(new FileReader(strInputFilename));
		wr = new XMLWriter(strOutputFilename);
		m_NodeStack = new Stack<Parse>();
		m_Root = "-1 GEDCOM" ;
	}

	public BufferedReader getInput()
	{
		return m_input ;
	}

	public Stack<Parse> getNodeStack()
	{
		return m_NodeStack ;
	}

	public String getRootNode()
	{
		return m_Root ;
	}

	public void convert() throws IOException
	{
		String strLine = null;
		String strPrevLine = getRootNode();
		Parse prev = new  Parse(strPrevLine);
		Parse curr = new  Parse(strPrevLine);
		while (true) 
		{
			strLine = getInput().readLine() ;
			curr = new  Parse(strLine);
		 	if ( strLine  == null )
			{
				wr.childNode(prev);
				break;
			}
			if ( getNodeStack().empty() 
				|| prev.getIntLevel() < curr.getIntLevel() )
			{
				wr.createNode(prev);
				getNodeStack().push(prev);
			}
			else
			if ( prev.getIntLevel() == curr.getIntLevel() )
			{
				wr.childNode(prev);
			}
			else
			if ( prev.getIntLevel() > curr.getIntLevel() )
			{
				wr.childNode(prev);
				while ( (!getNodeStack().empty() && (curr.getIntLevel() 
						<= getNodeStack().peek().getIntLevel())) )
				{
						wr.endNode(getNodeStack().pop());
				}
			}
			prev = curr ;
		}
		while(!getNodeStack().empty())
		{
			wr.endNode(getNodeStack().pop());
		}
	}
}
