public class BitVector 
{
	
    private Bit[] bits;
    
    public BitVector(String s) 
    {
    	boolean thereIsOne = false;
        if (s == null) {throw new IllegalArgumentException("s is null");}
        else if (s == "") {throw new IllegalArgumentException("s is empty");}
        
        Bit value;
    	Bit[] array = new Bit[s.length()];
   	
        for (int i = 0; i < s.length(); i++)
        {        	
        	if(toInt(s.charAt(i)) >= 2) {throw new IllegalArgumentException("not in base 2");}
        	else if(s.charAt(i) != '1' & s.charAt(i) != '0') {throw new IllegalArgumentException("Illegal character");}
        	
        	if(s.charAt(i) == '1') {value = new Bit(true); thereIsOne = true;}
        	else {value = new Bit(false);}      	
        	array[i] = value;
        }
        
        this.bits = array; 
        if((thereIsOne) & (bits[s.length() - 1].toInt() == 0)) {throw new IllegalArgumentException("Illegal representation");}
    }

    public String toString()
    {
        String ans = "";
        String output = "";
        for(Bit i : this.bits)
        {
        	ans = ans + i.toString();
        }
        
        ans = NumericalString.binary2Decimal(ans);
        
        for(int j = ans.length() - 1; j >= 0; j--)
        {
        	output = output + ans.charAt(j);
        }
        return output;
    }
    
    public static int toInt(char c)
    {
    	return "0123456789".indexOf(c);
    } 
   

}
