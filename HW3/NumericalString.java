public class NumericalString
{
	
    public static int toInt(char c)
    {
    	return "0123456789".indexOf(c);
    }
    
    public static boolean legalNumericString(String s, int b)
    {
        boolean ans = true;
        
        if (b < 2 | b > 10) {throw new IllegalArgumentException("The base is out of range");}
        else if ((s == null) || (s == "") || (s.charAt(s.length() - 1) == '0')) {ans = false;}     
       
        for (int i = 0; i < s.length() & ans; i++)
        {
        	if (s.charAt(i) < '0' | s.charAt(i) > '9') {ans = false;}
        	else if (toInt(s.charAt(i)) >= b) {ans = false;}     	
    	}  
              
        return ans;
    }
    
	public static String decimalIncrement(String s)
	{		
		if(!legalNumericString(s,10)) {throw new IllegalArgumentException("Not in base 10");} 
		String ans = decimalIncrement(s, true);
		return ans;
	}

    public static String decimalIncrement(String s, boolean add)
    {
    	String ans = "";
    	String tmp = "";
    	if(s.length() == 0)
    	{
    		ans = "";
    	}
    	else
    	{
    		if(add)
    		{			
    			int value = toInt(s.charAt(0));    
    			
    			if ((s.length() == 1) & (value == 9))
    			{
    				tmp = "01";
				}   			
    			else if (value >= 9)
    			{
    				tmp = "0";
				}
    			else
    			{
    				value = value + 1;
    				tmp = (char) (value + '0') + "";
    				add = false;   				
				}	
    		   			
    			ans = tmp + decimalIncrement(s.substring(1),add);
    		}
    		else
    		{
    			ans = s.charAt(0) + decimalIncrement(s.substring(1),add);
    		}   		
    	}
    	return ans; 	
    }   

    public static String decimalDouble(String s)
    {
        if(!legalNumericString(s,10)) {throw new IllegalArgumentException("Not in base 10");} 
        String ans = decimalDouble(s,false);
        return ans;
    }
    
    public static String decimalDouble(String s,boolean correntAdd)
    {
    	String ans = "";
    	String tmp = "";
    	boolean nextAdd;
    	
    	if(s.length() == 0)
    	{
    		ans = "";
    	}
    	else
    	{ 		 		   		
			int value = toInt(s.charAt(0));
			value = value * 2;
			int remainder;
			
			if (value > 9)
			{
				remainder = value - 10;
				nextAdd = true;
			}
			else 
			{
				remainder = value;
				nextAdd = false;
			} 			
			if (correntAdd)
			{
				remainder = remainder + 1;
			}				
    		if ((s.length() == 1) & (value > 9))
    		{
    			tmp = (char) (remainder + '0') + "1";
			}   		
    		else 
    		{
    			tmp = (char) (remainder + '0') + "";
			}		
    		
			ans = tmp + decimalDouble(s.substring(1),nextAdd);
    	}
    	return ans; 
    }

    public static String binary2Decimal(String s)
    {
        if(!legalNumericString(s,2)) {throw new IllegalArgumentException("Not in base 2");} 
        return RecursiveBinary2Decimal(s);
    }
    
    public static String RecursiveBinary2Decimal(String s)
    {      	
    	String ans = "";
    	if(s.length() == 1)
    	{
    		ans = s;
    	}
    	else
    	{ 		 		
    		ans = RecursiveBinary2Decimal(s.substring(1));
    		
    		if(s.charAt(0) == '0')
    		{
    			ans = decimalDouble(ans);
    		}
    		else if(s.charAt(0) == '1')
    		{
    			ans = decimalDouble(ans);
    			ans = decimalIncrement(ans);
    		}
    	}
    	return ans;
    }

    public static void main(String[] args) {
		BitVector number = new BitVector("1011");
		System.out.println(number.toString());
    }
}
