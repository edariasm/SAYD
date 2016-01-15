import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class conectionTest {

	
	private static final String USERNAME="USER1";
	private static final String PASSWORD="test";
	private static final String CONN_STRING="jdbc:sqlserver://WKSWUSJO0954;databaseName=ODK";
	public String nombreatl;
	
	
	
	public static Connection conectaraDB() {
		
		Connection conn = null;
	try {
		conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD); // esta linea de codigo conecta a la base de datos
		System.out.println("Connected!");
					
		return conn;
	
	
	} // end of try 
	catch (SQLException e) {
		System.err.println(e);
	} // end of catch 
	return null;

	} // end of conectaraDB
	} // end of class
	


