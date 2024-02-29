package TaxiProject;

import java.util.ArrayList;

public class User {

	String name;
	String phonenumber;
	String password;
	ArrayList<Ride> rides;

	
	User(String name, String phonenumber, String password,ArrayList<Ride> rides) {
		this.name = name;
		this.password = password;
		this.phonenumber = phonenumber;
		this.rides=rides;
	}

	/*
	 * static void Add(String name,Long phonenumber,String password) {
	 * Main.users.add(new User(name,phonenumber,password)); }
	 * 
	 * static void Add(String name,Long phonenumber,String password,String
	 * License,int Id) { Main.drivers.add(new
	 * Driver(name,phonenumber,password,Id,License)); }
	 */
	static void Bookings(User user) {
		
		System.out.println("\t\t"
				+"UnPicked rides......");
		
		
		boolean b=false;
		System.out.println("Rideid\tpickuplocation\tdroplocation\tstatus\tAmount\n");
		for(int i=0;i<user.rides.size();i++)
		{
			Ride r=user.rides.get(i);
			if(r.status.equals("not-picked"))
			{
			    b=true;	
				System.out.print(r.RideId+"\t");
				System.out.print(r.pickuplocation+"\t");
				System.out.print(r.droplocation+"\t");
				System.out.print(r.status+"\t");
				System.out.print(r.fare+"\t\n");
			}
			
		}
		if(!b)
			System.out.println("NO Unpicked rides to display\n");
		
		System.out.println("\t\tCompleted Rides....");
		boolean a= false;
		System.out.println("RideId\tDriverName\tDrivermobilebnumber\tPickuplocation\tDroplocation\tRide status\tFare\tPayemntstatus\n");
		for (int i = 0; i < user.rides.size(); i++) {
			
			Ride r=user.rides.get(i);
			if(r.status.equals("Ride-Ended")&&(r.payment.status))
			{
				a=true;
				System.out.print(r.RideId+"\t");
				System.out.print(r.driver.name+"\t");
				System.out.print(r.user.phonenumber+"\t");
				System.out.print(r.pickuplocation+"\t");
				System.out.print(r.droplocation+"\t");
				System.out.print(r.status+"\t");
				System.out.print(r.fare+"\t");
				System.out.print("Paid"+"\t\n");
			}
			}
		
		
		}
		
		
	}


