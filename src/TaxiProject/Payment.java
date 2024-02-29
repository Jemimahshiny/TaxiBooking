package TaxiProject;
import java.util.Scanner;
public class Payment {

	static Scanner sc=new Scanner(System.in);
	int Amount;
	String Mode;
	boolean status;
	
	Payment(int Amount,String Mode,boolean status)
	{
		this.Amount=Amount;
		this.Mode=Mode;
		this.status=status;
	}
	
	
	static void getMode(Ride ride)
	{
		System.out.println("Enter the mode of payment you like to choose\n");
		System.out.println("1.Credit card\n2.Debit card");
		int mode=sc.nextInt();
		String Mode="";
		if(mode==1)
		{
			Mode="Credit card";
			ride.payment.Mode="CreditCard";
		}
		else if(mode==2)
		{
			Mode="Debit card";
			ride.payment.Mode="DebitCard";
		}
		String cardno="";
		System.out.println("Enter your "+Mode+" number");
	   do {
		   sc.nextLine();
		   cardno=sc.nextLine();
		    if(cardno.length()==16)
			{System.out.println("Your payment is successfully completed\n");
			ride.payment.Mode=Mode;
			ride.payment.status=true;
			break;
			}
		 System.out.println("Enter correct card no.");
	   }while(cardno.length()!=16);
	}
	
}
