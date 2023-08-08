/*
I, <Tzur Shalom> (<207107038>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit>
{
    private int numberOfOnes;

    // Do not change the constructor
    public BitList()
    {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes()
    {
        return numberOfOnes;
    }

//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) 
    {
    	if (element.equals(null) | this.equals(null)) { throw new IllegalArgumentException("null");}
    	else if (element.equals(Bit.ONE)) {numberOfOnes++;}
    	super.addLast(element);
    }

    public void addFirst(Bit element)
    {
    	if (element.equals(null) | this.equals(null)) { throw new IllegalArgumentException("null");}
    	else if (element.equals(Bit.ONE)) {numberOfOnes++;}
    	super.addFirst(element);
    }

    public Bit removeLast()
    {
    	if (this.equals(null)) { throw new IllegalArgumentException("null");}
    	if (this.isEmpty()) {throw new IllegalArgumentException("empty");}
    	Bit element = super.getLast();
    	if (element.equals(Bit.ONE)) {numberOfOnes--;}
    	super.removeLast();
    	return element;
    }

    public Bit removeFirst()
    {
    	if (this.equals(null)) { throw new IllegalArgumentException("null");}
    	if (this.isEmpty()) {throw new IllegalArgumentException("empty");}
    	Bit element = super.getFirst();
    	if (element.equals(Bit.ONE)) {numberOfOnes--;}
    	super.removeFirst();
    	return element;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString()
    {
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
    	String ans = "";
    	Iterator<Bit> iter = super.iterator();
    	while(iter.hasNext())
    	{
    		ans = iter.next() + ans;
    	}
    	ans = "<" + ans + ">";
    	return ans;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other)
    {
    	if (other.equals(null) | this.equals(null)) {throw new IllegalArgumentException("null");}
    	Iterator<Bit> iter = other.iterator();
        while(iter.hasNext())
        {
     	   addLast(iter.next());
        }  	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber()
    {
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
    	boolean ans = true;
    	Iterator<Bit> iter = super.iterator();
    	if (!iter.hasNext()) {ans = false;}
    	else
    	{
    		if (super.getLast().equals(Bit.ONE) & getNumberOfOnes() == 1) {ans = false;}
    	}
    	return ans;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced()
    {
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
    	boolean ans = false;
    	
    	if (isNumber())
    	{	    	  		
			if ((size()) >= 3)
			{								
		    	Bit b1 = super.getLast();
		    	Bit b2 = super.get(size() - 2);
		    	
				if (b1.equals(Bit.ONE) & b2.equals(Bit.ONE) & getNumberOfOnes() == 2) {ans = true;}
		    	if (((b1.equals(Bit.ONE) & b2.equals(Bit.ZERO))
		    			| (b1.equals(Bit.ZERO) & b2.equals(Bit.ONE)))) {ans = true;}
			}
			
			if (!ans)
			{
	    		BitList bits1 = new BitList();   	    
	    		bits1.addFirst(Bit.ZERO);  		
	    		if (super.equals(bits1)) {ans = true;}
	    		
	    		bits1.addFirst(Bit.ONE); 
	    		if (super.equals(bits1)) {ans = true;}
	        		
	    		BitList bits2 = new BitList();
	    		bits2.addFirst(Bit.ONE); 
	    		bits2.addFirst(Bit.ONE); 
	    		if (super.equals(bits2)) {ans = true;}
			}
    	}
    			
    	return ans;
    }

    public void reduce()
    {
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
        while(!isReduced()) {removeLast();}
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement()
    {
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
    	BitList newList = new BitList();
    	Iterator<Bit> iter = super.iterator();
        while(iter.hasNext())
        {
        	if (iter.next().equals(Bit.ZERO)) {newList.addLast(Bit.ONE);}
        	else {newList.addLast(Bit.ZERO);}
        }  	
        return newList;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() 
    {
        return removeFirst();
    }

    public void shiftLeft()
    {
    	addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) 
    {
    	if (newLength < 0) {throw new IllegalArgumentException("illegal length");}
    	if (this.equals(null)) {throw new IllegalArgumentException("null");}
    	if (this.isEmpty()) {throw new IllegalArgumentException("empty");}
    	
    	Bit b = Bit.ONE;
    	if(getLast().equals(Bit.ZERO)) {b = Bit.ZERO;}
    		
    	while (size() < newLength)
    	{ 		
			addLast(b);
    	}
    }

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}