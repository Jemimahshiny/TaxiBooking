package TaxiProject;
import java.util.Map;
import java.util.Scanner;
public class Cab {
static int rideId=1;
	static Scanner sc=new Scanner(System.in);
	int CabId;
    String CurrentLocation;
	String Availability;
	
	Cab(int CabId,String CurrentLocation,String Availability)
	{
		this.CabId=CabId;
		this.CurrentLocation=CurrentLocation;
		this.Availability=Availability;
	}
	
	static void Book(Object user)
	{
		System.out.println("A---B---C---D");
		String locations="ABCD";
	   
		System.out.println("Enter pickuplocation");
		String pickuppoint="";
				do {
		     pickuppoint=sc.nextLine().toUpperCase();
		if(pickuppoint.equals("A")||pickuppoint.equals("B")||pickuppoint.equals("C")||pickuppoint.equals("D"))
			break;
		System.out.println("Enter a correct pickup point");
		}while(true);
		System.out.println("Enter drop location");
		String droppoint;
		do {
		     droppoint=sc.nextLine().toUpperCase();
		     
		if(((droppoint.equals("A")&&(!droppoint.equals(pickuppoint)))||droppoint.equals("B")&&(!droppoint.equals(pickuppoint))||
				droppoint.equals("C")&&(!droppoint.equals(pickuppoint))||droppoint.equals("D")&&(!droppoint.equals(pickuppoint)))
				&&droppoint!=pickuppoint)
			break;
		if(droppoint.equals(pickuppoint))
		{
			System.out.println("Both pickup and drop locations are same.Enter correct Droplocation");
		}
		else
		System.out.println("Enter a correct drop point");
		}while(true);
		
		
		boolean c=true;
		for (Map.Entry<Cab, Driver> values : Main.cabs.entrySet()) {
			Cab cab = values.getKey();
			if(cab.CurrentLocation.equals(pickuppoint))
			{
				c=false;
				break;
			}
		}
		int a=locations.indexOf(pickuppoint);
		int b=locations.indexOf(droppoint);
		int distance=Math.abs(a-b);
		int Amount=distance*160;
		if(c)
		{
			System.out.println("No cab Available for you at the moment\n");
			Main.pickedrides.add(new Ride(rideId++,(User)user,null,null,pickuppoint,droppoint,Amount,"NO AVAILABLE CABS",null));
			return;
		}
		
		
		User U=(User)user;
		Ride r=new Ride(rideId++,(User)user,null,null,pickuppoint,droppoint,Amount,"not-picked",new Payment(Amount,"",false));
		Main.bookedrides.add(r);
		U.rides.add(r);
		System.out.println("PickupLocation\tDropLocation\tAmount\tStatus");
		System.out.println(pickuppoint+"\t"+droppoint+"\t"+Amount+"\t"+"Not-Picked\n");
		System.out.println("Wait for few minutes for your ride to be picked\n");
	}
}
	