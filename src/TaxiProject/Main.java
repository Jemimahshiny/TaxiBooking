package TaxiProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Map<Cab, Driver> cabs = new HashMap<>();
	static List<Ride> bookedrides = new ArrayList<>();
	static List<User> users = new ArrayList<>();
	static List<Driver> driver = new ArrayList<>();
	static List<Ride> pickedrides = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		driver.add(new Driver("Raj", "9012345678", "hello", 101, "TN 76 202300005588", new ArrayList<Ride>()));
		driver.add(new Driver("Sukumar", "9012345679", "hello", 102, "TN 76 202300005589", new ArrayList<Ride>()));
		driver.add(new Driver("Sakthi", "9012345634", "hello", 103, "TN 76 202300005590", new ArrayList<Ride>()));
		driver.add(new Driver("Partha", "9360479649", "hello", 104, "TN 76 202300005591", new ArrayList<Ride>()));
		driver.add(new Driver("Prabakar", "8903257733", "hello", 105, "TN 76 202300005592", new ArrayList<Ride>()));
		driver.add(new Driver("Arunachalam", "8922323123", "hello", 106, "TN 76 202300005593", new ArrayList<Ride>()));

		cabs.put(new Cab(1, "A", "Available"), driver.get(0));
		cabs.put(new Cab(2, "A", "Available"), driver.get(1));
		cabs.put(new Cab(3, "B", "Available"), driver.get(2));
		cabs.put(new Cab(4, "B", "Available"), driver.get(3));
		cabs.put(new Cab(5, "C", "Available"), driver.get(4));
		cabs.put(new Cab(6, "D", "Available"), driver.get(5));

		users.add(new User("Saro", "9123571811", "hello", new ArrayList<Ride>()));
		users.add(new User("Raji", "9012357181", "hello", new ArrayList<Ride>()));
		users.add(new User("Uma", "9001235718", "hello", new ArrayList<Ride>()));

		users.add(driver.get(0));
		users.add(driver.get(1));
		users.add(driver.get(2));
		users.add(driver.get(3));
		users.add(driver.get(4));
		users.add(driver.get(5));

		// Admin
		users.add(new User("Jemimah", "9489753902", "admin", new ArrayList<Ride>()));
		System.out.println("Taxi Booking....");
		load();
	}
		public  static void load()
		{
		int option = 0;
		boolean flag = true;
	     while (flag) {
			System.out.println("1.SignIn\n2.SignUp\n3.Exit");

			option = Util.getInteger();

			switch (option) {

			case 1 -> {
				Object obj = SignIn();
				if (obj == null) {
					System.out.println("Your account is not available.SignUp to continue");
					sc.nextLine();
					obj = SignUp();
					menu(obj);
					} else {
                    menu(obj);
                    }
			}
			case 2 -> {

				User user = SignUp();
				menu(user);

			}
			case 3 -> {
				flag = false;
			}
			default -> System.out.println("choose correct option");
			}

		}
	}

	public static User SignUp() {

		System.out.println("Enter your name");
		String name;
		String nameregex="^[A-Za-z\\s'-]+$";
			while(true)
		{
			name=sc.nextLine();
			if(name.matches(nameregex))
				break;
			System.out.println("Enter a valid name");
				
		}
		System.out.println("Enter your Phonenumber");
		String number;
		String regex="^\\d{10}";
		while(true)
		{
			number=sc.nextLine();
			if(number.matches(regex))
				break;
			System.out.println("Enter a valid phone number");
		}
		System.out.println("Create a password");
		String password = sc.nextLine();
		for(int i=0;i<users.size();i++)
			if(users.get(i).phonenumber.equals(number)&&users.get(i).password.equals(password))
			{
				System.out.println("Your account already existed.SignIn to continue...");
				SignIn();
			}
		User user = new User(name, number, password, new ArrayList<Ride>());
		users.add(user);
		return user;
	}

	public static Object SignIn() {

		System.out.println("Enter your Phonenumber");
		String number ;
		String regex="^\\d{10}";
		while(true)
		{
			number=sc.nextLine();
			if(number.matches(regex))
				break;
			System.out.println("Enter a valid phone number");
		}
		System.out.println("Enter your Password");
		String password;
		while(true)
		{
			password=sc.nextLine();
			if(password!="")
				break;
			System.out.println("Enter a valid password");
		}
		
		
	Object obj = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).phonenumber.equals(number)) {
				
				if(!users.get(i).password.equals(password))
				{
					String pass=users.get(i).password;
					while(!pass.equals(password))
					{
						System.out.println("ENter a correct password");
						password=sc.nextLine();
						
					}
				
				for (int j = 0; j < driver.size(); j++) {
					if (users.get(i).phonenumber.equals(driver.get(j).phonenumber)) {
						Driver d = driver.get(j);
						return d;
					}
				}

				
				}
				return users.get(i);
			}
		}

		return obj;
	}


	public static void menu(Object obj) {

		User u = (User) obj;

		for (int i = 0; i < u.rides.size(); i++) {
		    Ride r=u.rides.get(i);
		if(r.status.equals("Ride-Ended")&&(!r.payment.status))
		{
			System.out.println("You have not completed the payment for your previous ride.\n");
			System.out.print(r.RideId+"\t");
			System.out.print(r.driver.name+"\t");
			System.out.print(r.driver.phonenumber+"\t");
			System.out.print(r.pickuplocation+"\t");
			System.out.print(r.droplocation+"\t");
			System.out.print(r.fare+"\t\n");
			
			System.out.println("Do you want to pay now!!");
			System.out.println("1.Yes\n2.No\n");
			int option=Util.getInteger();
			while(true)
			{
				if(option==1)
				{
					System.out.println("Pay here!!!");
					Payment.getMode(r);
					break;
				}
				else if(option==2)
				{
					break;
				}
				else
				{
					System.out.println("Choose either 1 or 2");
				}
				
			}
			
			
		}
		}
		
		
		if (u.phonenumber.equals("9489753902") && u.password.equals("admin")) {
			boolean admin = true;
			while (admin) {
				System.out.println(
						"1.Add Cab\n2.Remove Cab\n3.All Rides\n4.Book Cab\n5.Cancel Ride\n6.My bookings\n7.Exit");

				int option = Util.getInteger();
				switch (option) {
				case 4 -> {
					Cab.Book(obj);

				}
				case 1 -> {
					CabManagement.AddCab();

				}
				case 2 -> {
					System.out.println("Enter the cabId to be removed");
					int cabid = 0;
					do {
						cabid = Util.getInteger();
						if (cabid >= 1 && cabid <= CabManagement.CabId)
							break;
						System.out.println("Choose a valid cabId");

					} while (option < 1 || option > CabManagement.CabId);
					CabManagement.removeCab(cabid);

				}
				case 6 -> {
					User.Bookings(u);

				}
				case 3 -> {
					Ride.AllRides();
				}
				case 7 -> {
					admin = false;

				}
				case 5 -> {

					Ride.Cancel(u);

				}
				default -> {
					System.out.println("Choose a correct option");
				}

				}

			}
		} else if (!(obj instanceof Driver)) {
			
			User user = (User) obj;
			boolean customer = true;
			while (customer) {
				System.out.println("1.Book Cab\n2.Cancel Ride\n3.My Rides\n4.Back");
				
				int choice = Util.getInteger();

				switch (choice) {

				case 1 -> {
					Cab.Book(obj);
				}
				case 3 -> {
					User.Bookings(user);
				}
				case 2 -> {
					Ride.Cancel(user);

				}
				case 4 -> {
					customer = false;
				}

				default -> System.out.println("Choose a correct option");

				}

			}
		} else {
			Driver d = (Driver) obj;
			for(int i=0;i<Ride.unpaidrides.size();i++)
			{
				Ride r=Ride.unpaidrides.get(i);
				if(r.driver.DriverId==d.DriverId&&(r.payment.status))
				{
				    Ride.unpaidrides.remove(i);
					System.out.println("You have successfully received a Rs."+r.fare+" for your previous ride");
					System.out.println("Press enter to continue");
					
					sc.nextLine();
					break;
				}
				
			}
			boolean dd = true;
			for (int i = 0; i < pickedrides.size(); i++) {
				if ((d.DriverId == pickedrides.get(i).driver.DriverId) && pickedrides.get(i).status == "On-Going") {
					
					Ride r = pickedrides.get(i);
					boolean driver = true;
					System.out.println("coming again");
					while (driver) {
						System.out.println("1.End Ride\n2.Exit");
                        int choice = Util.getInteger();
						switch (choice) {
						case 1 -> {
							Ride.EndRide(r);
							menu(obj);
                          }
						case 2 -> {
							driver=false;
							load();
							
						}
						default -> System.out.println("choose a correct option");
						}

					}
					break;
				}

			}
       while (dd) {
				System.out.println(
						"1.Customer request\n2.Change Availability\n3.My Services\n4.Book cab\n5.Cancel Ride\n6.My rides\n7.Back");

				int choice = Util.getInteger();
				switch (choice) {
				case 4 -> {
					Cab.Book(obj);

				}
				case 1 -> {
					Driver.Availablerides(d.DriverId);

				}
				case 2 -> {
					Driver.changestatus(d);

				}
				case 3 -> {
					Driver.Bookings(d.DriverId);

				}
				case 5 -> {
					Ride.Cancel(obj);

				}
				case 6 -> {
					User.Bookings((User) d);

				}
				case 7 -> {dd = false;
							load();}
				default -> {
					System.out.println("Choose a correct option");
				}
				}

			}
		}
	}

}
