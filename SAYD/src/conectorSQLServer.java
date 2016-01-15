import java.sql.*;

public class conectorSQLServer {
	private static final String USERNAME="USER1";
	private static final String PASSWORD="test";
	private static final String CONN_STRING="jdbc:sqlserver://WKSWUSJO0954;databaseName=ODK";
	private static final String Nombre = null;
	
	
	private Connection con; // to connect to sql db
	private Statement st; // to execute query
	private ResultSet rs; // to return the query result
	
	
	public void connectorSQLServer(){
		
		Connection con = null;
		try {
			
				
			con = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("Connected!");
			st = con.createStatement();
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	} //end of constructor
	
	
	//clase para hacer el query
	
			public void getData (){
				
				try {
					System.out.println("Entro al try");
					
					String query = "select * from alumnos";
					System.out.println("Asigno el query" + query);
					rs = st.executeQuery(query);
					System.out.println("Asigno el query a la variable rs " + rs);
					/*
					System.out.println("Registros de la base de datos");
					
					while (rs.next()) {
						String name = rs.getString(Nombre);
						System.out.println("Nombre: " + name);
						
					} //end of while 
					*/
					
				} catch (Exception e) {
					System.out.println("Problema con el query " + e);
				}
				
			} //end of getData
			
	
	

} // end of class
