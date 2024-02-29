package TaxiProject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
public class CabManagement {
	static int CabId=7;
	static int DriverId=107;
static Scanner sc=new Scanner(System.in);
	public static void  AddCab()
	{
		System.out.println("\t\tEnter Cab Details");
	    System.out.println("Enter currentLocation");
	    char cl;
		while(true) {
			cl=sc.nextLine().charAt(0);
			if(cl=='A'||cl=='B'||cl=='C'||cl=='D')
				break;
			System.out.println("Enter a valid location");
		}
		Cab c=new Cab(CabId++,cl+"","Available");
		System.out.println("\t\tEnter Driver Details");
		System.out.println("Enter name");
		String name=sc.nextLine();
		System.out.println("Enter phone number");
		String phone="";
		String regex = "^\\d{10}$";

		while(true) {
			phone=sc.nextLine();	
			if(phone.matches(regex))
					break;
			System.out.println("Enter a valid Phonenumber");
		}
		System.out.println("Enter password");
		String pass=sc.nextLine();
		
		System.out.println("Enter LicenseId");
		String license;
		String regexx = "^TN\\s\\d{2}\\s\\d{2}\\s\\d{4}$";

		while(true)
		{
		license=sc.nextLine();
		if(license.matches(regexx))
			break;
		System.out.println("Enter a valid license Id");
		}
		Driver d=new Driver(name,phone,pass,DriverId++,license,new ArrayList<>());
		Main.users.add(new Driver(name,phone,pass,DriverId++,license,new ArrayList<>()));
		Main.driver.add(new Driver(name,phone,pass,DriverId++,license,new ArrayList<>()));
		Main.cabs.put(c, d);
		
	}
   public static void removeCab(int cabId)
   {
	   for(Map.Entry<Cab, Driver> values:Main.cabs.entrySet())
       {
       if(values.getKey().CabId==cabId&&(values.getKey().Availability=="Available"||
    		   values.getKey().Availability=="Unavailable"))
       {
    	 Main.cabs.remove(values.getKey());
    	  Main.driver.remove(values.getValue());
    	  Main.users.remove(values.getValue());
    	 break;
       }
       }
   }
	
}
