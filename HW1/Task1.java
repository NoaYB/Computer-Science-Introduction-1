
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        
		//System.out.println("Enter a value for variable a :");
		int a = scanner.nextInt();
		
		//System.out.println("Enter a value for variable b :");
		int b = scanner.nextInt();
		
		//System.out.println("Enter a value for variable q :");
		int q = scanner.nextInt();
		
		//System.out.println("Enter a value for variable r :");
		int r = scanner.nextInt();
		
		if (!((r < b) & (b != 0) & (a == (q * b) + r))) {ans = false;}

        //---------------write your code ABOVE this line only!--------------
		
        System.out.println(ans);
		scanner.close();
    }
}