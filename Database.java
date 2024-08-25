
import java.sql.Statement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
 
public class Database {

	
	
	
	private String user ="root";
	private String pass= "";
	private String url = "jdbc:mysql://localhost:3306/trainallocationsystem";

	private Statement statement;
	
	
	public Database() throws SQLException {
		
		
	           Connection connection  =DriverManager.getConnection(url, user, pass);
		    statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	}
	public Statement getStatement() {
		return statement;
	}
	
	
	
	

	
}
