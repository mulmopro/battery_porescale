/**
* This class is useful to identify the most relevants zones of the geometry.
*/

public class Zone
{
	private int num=50;
	private int counter=0;
	private int i=0;
	private String [] name=new String[num];
	private String [] zone=new String[num];
	
	/**
	* This method correlate an operation code with an arbitrary name.
	* @param text1 Operation code
	* @param text2 Arbitrary name
	*/
	public void define (String text1, String text2)
	{
		zone[counter]=text1; 
		name[counter]=text2; 
		counter+=1;
	}
	
	public void replace (String text1, String text2)
	{
		for(i=0; i<num; i++) 
		{
			if(name[i]==text1) 
			{
				zone[i]=text2;
			}
		}
	}
	
	public String select (String text) 
	{
		for(i=0; i<num; i++) 
		{
			if(name[i]==text) 
			{
				return zone[i];
			}
		}
		return "error";
	}
}
