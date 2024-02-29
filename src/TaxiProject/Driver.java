package TaxiProject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Driver extends User {
	static Scanner sc = new Scanner(System.in);
	int DriverId;
	String License;

	Driver(String name, String phonenumber, String password, int DriverId, String License,ArrayList<Ride> rides) {
		super(name, phonenumber, password,rides);
		// this.name=name;
		// this.phonenumber=phonenumber;
		this.DriverId = DriverId;
		this.License = License;
//		this.password=password;
	}

	static void Bookings(int driverId) {
		boolean b = false;
		for (int i = 0; i < Main.pickedrides.size(); i++) {
			if (Main.pickedrides.get(i).driver.DriverId == driverId) {
				b = true;
				Ride obj = Main.pickedrides.get(i);
				System.out.println("RideId\tCustomerName\tCustomermobilebnumber\tpickuplocation\tdroplocation\tFare\tRide status\tPayment status");
				System.out.print(obj.RideId+"\t");
				System.out.print(obj.user.name+"\t");
				System.out.print(obj.user.phonenumber+"\t");
				System.out.print(obj.pickuplocation+"\t");
				System.out.print(obj.droplocation+"\t");
				System.out.print(obj.fare+"\t");
				System.out.print(obj.status+"\t");
				if(obj.payment.status)
					System.out.print("Paid");
				else
					System.out.print("Not paid");
			
			}
		}
		if (!b) {
			System.out.println("No bookings");
		}

	}

	public static void changestatus(Driver driver) {
		for (Map.Entry<Cab, Driver> values : Main.cabs.entrySet()) {
			if (values.getValue().DriverId == driver.DriverId) {
				Driver obj = values.getValue();
				if (values.getKey().Availability.equals("Available")) {
					System.out.println("Would you like to change your status from available to unavailable");
					System.out.println("1.Yes\n2.No");
					int input = sc.nextInt();
					if (input == 1) {
						values.getKey().Availability = "Unavailable";

					}
				} else {
					System.out.println("Would you like to change your status from unavailable to available");
					System.out.println("1.Yes\n2.No");
					int input = sc.nextInt();
					if (input == 1) {
						values.getKey().Availability = "Available";
					}

				}
				System.out.println("Status updated");
			}
		}
	}

	public static void Availablerides(int DriverId) {
		ArrayList<Ride> rides = new ArrayList<>();
		Ride obj = null;
		for (int i = 0; i < Main.bookedrides.size(); i++) {
			obj = Main.bookedrides.get(i);
			for (Map.Entry<Cab, Driver> values : Main.cabs.entrySet()) {
				Cab cab = values.getKey();
				if (cab.CurrentLocation.equals(obj.pickuplocation) && (cab.Availability.equals("Available"))) {
					if (values.getValue().DriverId == DriverId)
						rides.add(obj);
				}
			}
		}
		if (!rides.isEmpty()) {
			System.out.println("Rideid\tUser name\t phone number\t pickuplocataion\t droplocation\tAmount");
			for (int j = 0; j < rides.size(); j++) {
				System.out.print(rides.get(j).RideId + "\t");
				System.out.print(rides.get(j).user.name + "\t");
				System.out.print(rides.get(j).user.phonenumber + "\t");
				System.out.print(rides.get(j).pickuplocation + "\t");
				System.out.print(rides.get(j).droplocation + "\t");
				System.out.print(rides.get(j).fare + "\t\n");
			}
			System.out.println();

			System.out.println("Would to like a pick a ride");
			System.out.println("1.yes\n2.No");
			int in = sc.nextInt();
			if (in == 1) {
				System.out.println("Enter the RideId you like to pick");
				boolean flag=true;
				int a=0;
				while(flag)
				{
				a = sc.nextInt();
				for(int i=0;i<rides.size();i++)
				{
					Ride r=rides.get(i);
					if(r.RideId==a)
					{
						flag=false;
					}
				}
				if(flag)
				System.out.println("Enter a correct RideId");
				}
				System.out.println(a);
				Ride.beginRide(a, DriverId);
			} 
			
		} else
			System.out.println("No ride available for you\n");
	}
}
