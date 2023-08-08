
import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
              
		//System.out.println("Enter a value for variable n :");
		int n = scanner.nextInt();
		
		//System.out.println("Enter a value for variable b :"); 
		int b = scanner.nextInt();
		
		//System.out.println("Enter a value for variable s :"); 
		int s = scanner.nextInt();
		
		//System.out.println("Enter a value for variable d :"); 
		int d = scanner.nextInt();
				
		int count1 = d;
		int sum1 = 1;
		
		while (count1 > 0) // Finding the value of b^d (sum1)
		{
			sum1 = sum1 * b;
			if (sum1 > n) {sum1 = sum1 % n;}
			count1 = count1 - 1;
		}
										
		int sum2 = 1;
		
		boolean Continue = true;
			 						
		for (int i = 0; (i <= (s - 1)) & Continue; i = i + 1) // Checking conditions for all (i) in the field
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
				
			if (sum2 == (n - 1)) {Continue = false;}
		}
												
		if ((sum1 != 1) & (sum2 != (n - 1))) // composite
		{
			ans1 = false;
			ans2 = b;
		}
		else // prime
		{
			ans1 = true;
			ans2 = -1;
		}
															
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
		scanner.close();
    }
}