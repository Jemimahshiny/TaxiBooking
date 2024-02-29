package TaxiProject;
import java.util.Scanner;
public class Util {

	static Scanner sc=new Scanner(System.in);
	
	static int getInteger()
	{
		
		while(true) {
		
		if(sc.hasNextInt())
		{
			int option=sc.nextInt();
			return option;
			
		}
		else
		{
			System.out.println("Enter a valid Input");
			sc.nextLine();
		}
	             }
	}
	
	
}
