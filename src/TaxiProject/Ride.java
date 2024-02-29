package TaxiProject;
import java.util.*;

public class Ride {
	
static Scanner sc=new Scanner(System.in);
static ArrayList<Ride> unpaidrides=new ArrayList<>();
	String pickuplocation;
	String droplocation;
	Cab cab;
	Driver driver;
	User user;
	String status;
    int RideId;
    int fare;
    Payment payment;
    
//Ride(int RideId,User user,String pickuplocation,String droplocation,String status,int fare)
//{
//	this.RideId=RideId;
//	this.user=user;
//	this.pickuplocation=pickuplocation;
//	this.droplocation=droplocation;
//	this.status=status;
//	this.fare=fare;
//}

Ride(int RideId,User user,Cab cab,Driver driver,String pickuplocation,String droplocation,int fare,String status,
		Payment payment)
{
	   this.RideId=RideId;
       this.user=user;
       this.driver=driver;
       this.cab=cab;
       this.pickuplocation=pickuplocation;
       this.droplocation=droplocation;
       this.status=status;
       this.payment=payment;
       this.fare=fare;
}



static void EndRide(Ride r)
{
//	System.out.println("RideId\tPickuplocation\tDroplocation\tStatus\n");
//    System.out.print(r.RideId+"\t");
//	System.out.print(r.pickuplocation+"\t");
//	System.out.print(r.droplocation+"\t");
//	System.out.print(r.status+"\t\n");
	System.out.println("Do you want to end the ride");
	System.out.println("1.yes\n2.No\n");
	
	boolean flag=true;
while(flag)
{
	int option=Util.getInteger();
	switch(option)
	{
	case 1->
	{
         r.cab.Availability="Available";
		 r.cab.CurrentLocation=r.droplocation;
		 r.status="Ride-Ended";
	
		 System.out.println("Your ride is ended\n");
		 unpaidrides.add(r);
		 flag=false;
	 }
     
	case 2->
   flag=false;
	default->
	System.out.println("Choose either 1 or 2");
	}
}
}
static void  beginRide(int RideId,int DriverId)
{
	Driver dri=null;
	Cab cab=null;
	Ride ride=null;
	
	for(int i=0;i<Main.bookedrides.size();i++)
	{
		if(Main.bookedrides.get(i).RideId==RideId)
		{
			ride=Main.bookedrides.get(i);
		     Main.bookedrides.remove(i);	
		}
		
	}
	
	for(Map.Entry<Cab, Driver> values:Main.cabs.entrySet())
    {
       int id=values.getValue().DriverId ;
		if(id==DriverId)
		{
		 dri=values.getValue();
		 cab=values.getKey();
		}
	}
	cab.Availability="Inride";
	ride.cab=cab;
	ride.driver=dri;
	ride.status="On-Going";
	Main.pickedrides.add(ride);
	
	Main.menu(dri);
	//System.out.println(Main.pickedrides.get(0).cab.Availability);
}

public static void AllRides()
{
	System.out.println("Unpicked Rides...\n");
	System.out.println("Username\tpickuppoint\tdroppoint\tRidestatus\tAmount");
	if(!Main.bookedrides.isEmpty()) {
	for(int i=0;i<Main.bookedrides.size();i++)
	{
		Ride r=Main.bookedrides.get(i);
		System.out.print(r.user.name+"\t");
		System.out.print(r.pickuplocation+"\t");
		System.out.print(r.droplocation+"\t");
		System.out.print(r.status+"\t");
		System.out.print(r.fare+"\t\n");
	}
   }
	else
		System.out.println("No unpicked rides to show\n");
	System.out.println("RideId\tCabId\tDriverId\tUsername\tpickuppoint\tdroppoint\tRidestatus\tFare\tPaymentmode\tPayment status");
for(int i=0;i<Main.pickedrides.size();i++)
{
	Ride r=Main.pickedrides.get(i);
	System.out.print(r.RideId+"\t");
	if(r.cab!=null)
	System.out.print(r.cab.CabId+"\t");
	else
		System.out.println("---\t");
	if(r.driver!=null)
	System.out.print(r.driver.DriverId+"\t");
	else
		System.out.print("---");
	System.out.println(r.user.name+"\t");
	System.out.print(r.pickuplocation+"\t");
	System.out.print(r.droplocation+"\t");
	System.out.print(r.status+"\t");
    if(r.payment!=null)
    	{
    	System.out.print(r.payment.Mode+"\t");
    	if(r.payment.status)
    	System.out.print("Paid\t\n");
    	else
    		System.out.println(" Not Paid\t\n");
    	}
    else
    	{System.out.print("---\t");
    	System.out.print("---\t\n");
    	}
    
    

}

}


static void Cancel(Object user)
{
	System.out.println("Rideid\tpickuplocation\tdroplocation\tstatus\tAmount");
	User U=(User)user;
	boolean b=true;
	for(int i=0;i<Main.bookedrides.size();i++)
	{
		if(Main.bookedrides.get(i).user.phonenumber.equals(U.phonenumber))
		{
			b=false;
			Ride r=Main.bookedrides.get(i);
			System.out.print(r.RideId+"\t");
			System.out.print(r.pickuplocation+"\t");
			System.out.print(r.droplocation+"\t");
			System.out.print(r.status+"\t");
			System.out.print(r.fare+"\t\n");
		}
	}
	if(!b)
	{
		System.out.println();
		System.out.println("Do you want to cancel any ride");
		System.out.println("1.yes\n2.no");
		int continuecancelling=sc.nextInt();
		while(true)
		{
		if(continuecancelling==1)
		{
			
	System.out.println("Enter the RideId you want to cancel");
	int cancelRide=sc.nextInt();
	for(int i=0;i<Main.bookedrides.size();i++)
	{
		if(Main.bookedrides.get(i).RideId==cancelRide)
		{
		Main.bookedrides.get(i).status="Cancelled";
	    Main.pickedrides.add(Main.bookedrides.get(i));
		Main.bookedrides.remove(i);
		}
		System.out.println("Your ride is cancelled successfully\n");
		break;
	}
	break;
		}
		else if(continuecancelling==2)
			break;
}
		}
	else
		System.out.println("You have no ride to cancel\n");
}

}



