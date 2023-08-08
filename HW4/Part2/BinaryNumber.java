/*
I, <Tzur Shalom> (<207107038>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>
{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE  = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not change this constructor
    public BinaryNumber(BinaryNumber number)
    {
        bits = new BitList(number.bits);
    }

    //Do not change this constructor
    private BinaryNumber(int i)
    {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length()
    {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() 
    {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c)
    {
    	if (c > '9' | c < '0') {throw new IllegalArgumentException("Illegal char");}
    	
    	int cVal = c -'0';
    	bits = new BitList();
    	while (cVal != 0)
    	{
    		int digit = cVal % 2;
    		bits.addLast(new Bit(digit));
    		cVal = cVal / 2;
    	}
    	bits.addLast(Bit.ZERO);
    }
           
  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString()
    {
        // Do not remove or change the next two lines
        if (!isLegal())
        	// Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        
        String ans = "";
        Iterator<Bit> iter =  bits.iterator();
        while(iter.hasNext())
        {
     	   ans = iter.next() + ans;
        }
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other)
    {   	
    	boolean equals = true;
        if (other instanceof BinaryNumber)
        {
        	BinaryNumber otherBinaryNumber = (BinaryNumber) other;
        	if (!otherBinaryNumber.bits.isReduced()) otherBinaryNumber.bits.reduce();
        	if(length() != otherBinaryNumber.length()) {equals = false;}
            else
            { 	
            	Iterator<Bit> iter1 = bits.iterator();
            	Iterator<Bit> iter2 = otherBinaryNumber.bits.iterator();
            	   	   	
                while (iter1.hasNext() & equals)
                {       	
                	if (!iter1.next().equals(iter2.next())) {equals = false;}                	    	
                } 
            }
    	}
        else {equals = false;}
     
        return equals;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe)
    {    	  	   	
    	if (addMe.equals(null) | this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (!(addMe.isLegal() & this.isLegal())) throw new RuntimeException("illegal BinaryNumber");
    	
    	BinaryNumber bn1 = new BinaryNumber(this);
    	BinaryNumber bn2 = new BinaryNumber(addMe);
 	
    	Bit b1 = Bit.ZERO;
    	Bit b2 = Bit.ZERO;
    	Bit b3 = Bit.ZERO;
    	
    	if (bn1.length() > bn2.length()) {bn2.bits.padding(bn1.length());}
    	else if (bn1.length() < bn2.length()) {bn1.bits.padding(bn2.length());}
    	
    	BitList bits1 = bn2.bits; 	
    	BitList bits2 = new BitList(); 	
    	Iterator<Bit> iter1 = bits1.iterator();
    	Iterator<Bit> iter2 = bn1.bits.iterator();
    	   	   	
        while (iter1.hasNext())
        {       	
        	b1 = iter1.next();       	
        	b2 = iter2.next();
        	       	   	
        	bits2.addLast(Bit.fullAdderSum(b1, b2, b3));       	
        	b3 = Bit.fullAdderCarry(b1, b2, b3);      	
        }
        
        if (bn1.bits.getLast().equals(bits1.getLast())) {bits2.addLast(b3);}                         
        if (!bits2.isReduced()) {bits2.reduce();}
                      
        BinaryNumber bn = new BinaryNumber(0);
        bn.bits = bits2;
        
        return bn;      
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate()
    {   	
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (!this.isLegal()) throw new RuntimeException("illegal BinaryNumber");
    	
    	BitList newBits = new BitList(); 
    	Iterator<Bit> iter = bits.iterator();
    	Bit b;
    	boolean theFirstOne = false;
    	
    	while (iter.hasNext())
    	{
    		b = iter.next();
    		if (theFirstOne) {newBits.addLast(b.negate());}
    		else {newBits.addLast(b);}
    		
    		if (b.equals(Bit.ONE) & (!theFirstOne)) {theFirstOne = true;}
    	}
    	
    	if (isLegal() & newBits.getLast().equals(Bit.ZERO)) {newBits.addLast(Bit.ZERO); newBits.reduce();}
    	else if (isLegal() & newBits.getLast().equals(Bit.ONE)) {newBits.addLast(Bit.ONE); newBits.reduce();}
    	
        BinaryNumber bn = new BinaryNumber(0);
        bn.bits = newBits;        
        return bn;   
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe)
    {    	     
    	if (subtractMe.equals(null) | this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (!(subtractMe.isLegal() & this.isLegal())) throw new RuntimeException("illegal BinaryNumber");

		return add(subtractMe.negate());    	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum()
    {
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (!this.isLegal()) throw new RuntimeException("illegal BinaryNumber");
    	
    	int i = 0;
        if (bits.getLast().equals(Bit.ONE)) {i = -1;}
        else if (bits.getLast().equals(Bit.ZERO) & length() > 1) {i = 1;}
        return i;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other)
    {
    	if (other.equals(null) | this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (!(other.isLegal() & this.isLegal())) throw new RuntimeException("illegal BinaryNumber");
    	
    	if (other.equals(null)) {throw new IllegalArgumentException("null");}
    	return subtract(other).signum();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt()
    {   	
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        
        if (this.equals(null)) {throw new IllegalArgumentException("null");}       
        if (length() > 32 ) {throw new IllegalArgumentException("out of range");}
        
        int ans = 0;
        int power = 1;
        int signum = signum(); 
        
        Iterator<Bit> iter =  bits.iterator();      
        if (signum == -1) { iter = negate().bits.iterator();}
               
        while(iter.hasNext())
        {
     	   ans = iter.next().toInt() * power + ans;
     	   power = power * 2;
        }
        
        ans = signum * ans;
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe)
    {
    	if (multiplyMe.equals(null) | this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (!(multiplyMe.isLegal() & this.isLegal())) throw new RuntimeException("illegal BinaryNumber");
    	
    	BinaryNumber bn1 = new BinaryNumber(this);
    	BinaryNumber bn2 = new BinaryNumber(multiplyMe);
    	BinaryNumber bn = ZERO;
    	
    	int negative = bn1.signum() - bn2.signum();   	
    	if (bn1.signum() == -1) {bn1 = bn1.negate();}  			
    	if (bn2.signum() == -1) {bn2 = bn2.negate();}    
    	if (bn1.equals(ONE)) {bn = bn2;}
    	else if (bn2.equals(ONE)) {bn = bn1;}
    	else if (bn1.equals(ZERO) | bn2.equals(ZERO)) {bn = ZERO;}
    	else {bn = bn1.multiplyPositive(bn2);}  	
    	if (negative == -2) {bn = bn.negate();}
    	return bn;
    }

    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe)
    { 	    	
    	return RecursiveMultiplyPositive(this, multiplyMe);
    }
        
    private BinaryNumber RecursiveMultiplyPositive(BinaryNumber bn1, BinaryNumber bn2)
    {
    	BinaryNumber ans = ZERO;
    	if (bn1.equals(ONE))
    	{
    		ans = bn2;
    	}
    	else
    	{
    		boolean odd = false;
    		if (bn1.bits.getFirst().toInt() == 1) {odd = true;}
    		ans = RecursiveMultiplyPositive(bn1.divideBy2(), bn2).multiplyBy2();  
    		if (odd) {ans = ans.add(bn2);}
    	}
    	return ans;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor)
    {
    	if (divisor.equals(null) | this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (!(divisor.isLegal() & this.isLegal())) throw new RuntimeException("illegal BinaryNumber");
    	
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line   
    	
    	BinaryNumber bn1 = new BinaryNumber(this);
    	BinaryNumber bn2 = new BinaryNumber(divisor);
    	BinaryNumber bn = ZERO;
    	
    	int negative = bn1.signum() - bn2.signum();   	
    	if (bn1.signum() == -1) {bn1 = bn1.negate();}  			
    	if (bn2.signum() == -1) {bn2 = bn2.negate();}    
    	else if (bn1.equals(ZERO)) {bn = ZERO;}
    	else {bn = bn1.dividePositive(bn2);}  	
    	if (negative == -2) {bn = bn.negate();}
    	return bn;  	
    }

    private BinaryNumber dividePositive(BinaryNumber divisor)
    {   	
    	BinaryNumber[] sub = {ZERO};
    	BinaryNumber bn = RecursiveDividePositive(this,divisor,sub);
    	while (((this.compareTo(sub[0]) >= 0) & (this.subtract(sub[0]).compareTo(divisor) >= 0)))
    	{
    		bn = bn.add(ONE);
    		sub[0] = sub[0].add(divisor);
    	}
    	
    	return bn;
    }
    
    private BinaryNumber RecursiveDividePositive(BinaryNumber bn1, BinaryNumber bn2, BinaryNumber[] sub)
    {
    	BinaryNumber ans = ZERO;
    	if (bn1.compareTo(bn2) == 0)
    	{
    		sub[0] = bn1;
    		ans = ONE;
    	}
    	else if (bn1.compareTo(bn2) == -1)
    	{
    		sub[0] = bn1;
    		ans = ZERO;
    	}
    	else
    	{   	   		
    		ans = RecursiveDividePositive(bn1.divideBy2(), bn2, sub);
    		if (ans.equals(ZERO)) {ans = ONE;}
    		else {ans = ans.multiplyBy2();}
    		sub[0] = sub[0].multiplyBy2();
    	}
    	return ans;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s)
    {
    	if (s.equals("")) {throw new IllegalArgumentException("empty string");}
    	if (s.equals(null)) {throw new IllegalArgumentException("null");}
    	
    	boolean M = false;
    	if (s.charAt(0) == '-') {s = s.substring(1); M = true;}
    	
    	BinaryNumber originalScale = ONE.add(new BinaryNumber('9'));
    	BinaryNumber scale = ONE;   	
    	BinaryNumber ans = ZERO;
    	BinaryNumber tmp = ZERO;
    	
    	for (int i = s.length() - 1; i >= 0; i--)
    	{
    		if (s.charAt(i) > '9' | s.charAt(i) < '0') {throw new IllegalArgumentException("Illegal char");}
    		tmp = (new BinaryNumber(s.charAt(i))).multiply(scale);
    		ans = ans.add(tmp);
    		scale = scale.multiply(originalScale);
    	}
    	
    	if (M) {ans = ans.negate();}
    	
    	bits = ans.bits;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString()
    {   	
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        
        if (this.equals(null)) {throw new IllegalArgumentException("null");}  
        
        BinaryNumber bn = new BinaryNumber(this);
        boolean M = false; 
        if (signum() == -1) {bn = bn.negate(); M = true;} 
        
        String s = "";
        Iterator<Bit> iter =  bn.bits.iterator();
        while(iter.hasNext()) {s = iter.next() + s;}
              
        String s1 = "";       
        for (int i = 0; i < s.length(); i++) {s1 = s.charAt(i) + s1;}        
        s1 = binary2Decimal(s1);
              
        String s2 = "";
        for (int j = 0; j < s1.length(); j++) {s2 = s1.charAt(j) + s2;}             
        if (M) {s2 = '-' + s2;}       
    	return s2;      
    }
    
    public static int toInt(char c)
    {
    	return "0123456789".indexOf(c);
    }
    
	public static String decimalIncrement(String s)
	{		
		return decimalIncrement(s, true);
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
    			
    			if ((s.length() == 1) & (value == 9)) {tmp = "01";} 			
    			else if (value >= 9) {tmp = "0";}
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
        return decimalDouble(s,false);
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
       
    // Returns this * 2
    public BinaryNumber multiplyBy2()
    {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returns this / 2;
    public BinaryNumber divideBy2()
    {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
}