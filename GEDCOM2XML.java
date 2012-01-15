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
	private BufferedReader m_input;		// input file
	private XMLWriter wr ;			// output xml file
	private Stack<Parse> m_NodeStack ;	// stack use to store nodes
	private String m_Root ;			// Root node

	public GEDCOM2XML(String strInputFilename ) throws IOException
	{
		m_input = new BufferedReader(new FileReader(strInputFilename));

		// support for standard given and GEDCOME inputfile
		wr = new XMLWriter();

		m_NodeStack = new Stack<Parse>();
		m_Root = "-1 GEDCOM" ;
	}

	public GEDCOM2XML(String strInputFilename , String strOutputFilename) throws IOException
	{
		m_input = new BufferedReader(new FileReader(strInputFilename));

		// support for xml file output given and GEDCOME inputfile
		wr = new XMLWriter(strOutputFilename);

		m_NodeStack = new Stack<Parse>();
		m_Root = "-1 GEDCOM" ;
	}


	// Getters as required for access
	// No Setters requires at this point

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

	// method which does the actual conversion

	public void convert() throws IOException
	{
		String strLine = null;

		// Root Node which is gedcom here this 
		// will become your previous line
		String strPrevLine = getRootNode();
		Parse prev = new  Parse(strPrevLine);

		Parse curr = new  Parse(strPrevLine);
		while (true) 
		{

			// Step 1: Get your current line from input file
			strLine = getInput().readLine() ;

			if ( strLine !=null && strLine.trim().compareTo("") == 0 )
			{
				// This handles blank lines in the file
				continue ;
			}

			// Step 2: Parse the Previous line to get Node elements
			curr = new  Parse(strLine);

		 	if ( strLine  == null )
			{
				// Your end condition to complete the current node and exit
				wr.childNode(prev);
				break;
			}

			// Step 3a : Case 1:  Previous Level is < Current Level
			if ( getNodeStack().empty() 
				|| prev.getIntLevel() < curr.getIntLevel() )
			{
				// Create the node and push on to stack as we need to close later
				wr.createNode(prev);
				getNodeStack().push(prev);
			}
			else
			if ( prev.getIntLevel() == curr.getIntLevel() )
			{
			// Step 3b : Case 2:  Previous Level is == Current Level
				// Process the child Node
				wr.childNode(prev);
			}
			else
			if ( prev.getIntLevel() > curr.getIntLevel() )
			{
			// Step 3c : Case 3:  Previous Level is > Current Level

				// Process the child Node
				wr.childNode(prev);

				// Process the end Nodes on the stack till Current Level
				// is <= Level of node in stack 

				while ( (!getNodeStack().empty() && (curr.getIntLevel() 
						<= getNodeStack().peek().getIntLevel())) )
				{
						wr.endNode(getNodeStack().pop());
				}
			}

			// Assign current line to previous 
			prev = curr ;
		}

		// Process any Nodes in the stack which require closure
		while(!getNodeStack().empty())
		{
			wr.endNode(getNodeStack().pop());
		}
	}
}
