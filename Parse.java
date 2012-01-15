/**
 *  
Class Parse provides Parsing functionality 
It takes GEDCOM input line and converts it into
LEVEL, TAG or ID , VALUE and or DATA
 *
 * @version      
        
1.0 14 Jan 2012
 * @author          
        
Vinayak Kankanwadi
*/

public class Parse
{
	protected String m_strId;
	protected String m_strTag;
	protected String m_strValue;
	protected String m_strLevel;
	protected String m_strLine;

	public Parse(String strLine)
	{
		m_strLine = m_strId = m_strTag = m_strValue = null;
		if (strLine != null )
		{
			m_strLine = strLine ;
			initIdTagValue();
		}
	}
	
	protected void initIdTagValue()
	{
		String[] tokens = m_strLine.split("\\s+", 3);

		m_strLevel = tokens[0];
		if(tokens[1].startsWith("@") && tokens[1].endsWith("@"))
		{
			m_strId = tokens[1];
			m_strTag = tokens[2].toLowerCase();
		}
		else if(tokens.length == 3)
		{
			m_strTag = tokens[1].toLowerCase();
			m_strValue = tokens[2];
		}
		else
		{
			m_strTag = tokens[1].toLowerCase();
		}
	}

	public String getStrId()
	{
		return m_strId;
	}

	public String getStrLevel()
	{
		return m_strLevel;
	}

	public int getIntLevel()
	{
		return Integer.parseInt(m_strLevel);
	}

	public String getStrTag()
	{
		return m_strTag;
	}

	public String getStrValue()
	{
		return m_strValue;
	}
}
