import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengersDatabase {
 
	
	public static void AddPassenger(Passenger p ,Database database ) throws SQLException {
		String insert =" INSERT INTO `Passengers`(`ID`, `Name`, `Email`, `Tel`) VALUES ('"+p.getID()+"','"+p.getName()+"','"
	+p.getEmail()+"','"+p.getTel()+"');";
		database.getStatement().execute(insert);
	}
	public static int getNextID(Database database) throws SQLException {
		int id = 0;
		ArrayList<Passenger> passengers =getAllPassengers(database);
		int size = passengers.size();
		if(size!=0) {
			id =passengers.get(size-1).getID() + 1;
		}
		return id;
		
	}
	
	
	
	public static ArrayList<Passenger> getAllPassengers(Database database) throws SQLException{
	 ArrayList<Passenger>	passengers=new ArrayList<>();
	 String select=" SELECT * FROM `Passengers`;";
	 ResultSet rs = database.getStatement().executeQuery(select);
	  while (rs.next()) {
          Passenger p = new Passenger();
          p.setID(rs.getInt("ID"));
          p.setName(rs.getString("Name"));
          p.setEmail(rs.getString("Email"));
          p.setTel(rs.getString("Tel"));
          passengers.add(p);
      }
	return passengers;
	}
	
	
	public static String[] getIDs(Database database) throws SQLException {
		ArrayList<Passenger> passengers =getAllPassengers(database);
		String[] array =new String[passengers.size()];
		for (int i=0;i<passengers.size();i++) {
			array[i] =String.valueOf(passengers.get(i).getID());
		}
		return array;
		
	}
	 public static Passenger getPassenger(String id, Database database) throws SQLException {
	        Passenger p = new Passenger();
	        String select = "SELECT `ID`, `Name`, `Email`, `Tel` FROM `Passengers` WHERE `ID` = '" + id + "';";
	        System.out.println("Executing query: " + select);  // Debugging statement
	        ResultSet rs = database.getStatement().executeQuery(select);

	        if (rs.next()) {
	            p.setID(rs.getInt("ID"));
	            p.setName(rs.getString("Name"));
	            p.setEmail(rs.getString("Email"));
	            p.setTel(rs.getString("Tel"));
	            System.out.println("Passenger found: " + p.getName());  // Debugging statement
	        } else {
	            System.out.println("No passenger found with ID: " + id);  // Debugging statement
	        }
	        
	        return p;
	    }
	 public static void EditPassenger(Passenger p , Database database) throws SQLException {
		    String update = "UPDATE `Passengers` SET `Name` = '" + p.getName() + "', `Email` = '" + p.getEmail() + "', `Tel` = '" + p.getTel() + "' WHERE `ID` = " + p.getID() + ";";
		    database.getStatement().execute(update);
		}

	 
	 
	 public static void DeletePassenger(String id ,Database database) throws SQLException {
		 String delete=" DELETE FROM `passengers` WHERE 'ID'   =  "+id+" ;";
		 database.getStatement().execute(delete);
	 }
	 
	 
	 public static String[] getPassengersNames(Database database) throws SQLException {
		 ArrayList<Passenger> passengers = getAllPassengers(database);
		 String[] array =new String[passengers.size()];
		 for (int i=0; i<passengers.size(); i++) {
			 array[i] =passengers.get(i).getName();
		 }
		return array;
		 
	 }
	public static Passenger getPassengerByName(String name ,Database database) throws SQLException {
		Passenger p =new Passenger();
		String select = "SELECT `ID`, `Name`, `Email`, `Tel` FROM `passengers` WHERE `Name` = '"+name+"' ;";
		ResultSet rs= database.getStatement().executeQuery(select);
		  if (rs.next()) {
		        p = new Passenger();  // Only create a Passenger object if the record exists
		        p.setID(rs.getInt("ID"));
		        p.setName(rs.getString("Name"));
		        p.setTel(rs.getString("Tel"));
		        p.setEmail(rs.getString("Email"));
		    }
		return p;
		
	}
	}
