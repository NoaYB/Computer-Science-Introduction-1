
import java.util.Scanner;

public class Task4f {

    public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        
		//System.out.println("Enter a value for variable n :"); 
		int n = scanner.nextInt();
		
		//System.out.println("Enter a value for variable s :"); 
		int s = scanner.nextInt();
		
		//System.out.println("Enter a value for variable d :"); 
		int d = scanner.nextInt();
		
		//System.out.println("Enter a value for variable k :"); 
		int k = scanner.nextInt();
		
		boolean Continue1 = true;
		
		for (int k1 = k; (k1 > 0) & (Continue1); k1 = k1 - 1) // Repeats k times to reduce the probability
		{		
			int range = ((n - 1) - 2) + 1;
			int b = (int) (Math.random() * range) + 2; // Brings a random number in the field
									
			int sum1 = 1;
					
			for (int j = d; j > 0; j = j - 1) // Finding the value of b^d (sum1)
			{
				sum1 = sum1 * b;
				if (sum1 > n) {sum1 = sum1 % n;}
			}
														
			int sum2 = 1;
			
			boolean Continue2 = true;
				 						
			for (int i = 0; (i <= (s - 1)) & (Continue2); i = i + 1) // Checking conditions for all (i) in the field
			{
				int TwoPowerI = 1;
			
				for (int j = i; j > 0; j = j - 1)// Finding the value of 2^i (TwoPowerI)
				{
					TwoPowerI = TwoPowerI * 2;
				}
								
				int count2 = TwoPowerI * d; // Gets the value of (2^i)*d 
								
				sum2 = 1;
						
				for (int j = count2; j > 0; j = j - 1) // Finding the value of b^((2^i)*d) (sum2)
				{
					sum2 = sum2 * b;
					if (sum2 > n) {sum2 = sum2 % n;}
				}
							
				if (sum2 == (n - 1)) {Continue2 = false;}			
			}
																
			if ((sum1 != 1) & (sum2 != (n - 1))) {ans = false; Continue1 = false;} // composite
			else {ans = true;} // prime
		}

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}