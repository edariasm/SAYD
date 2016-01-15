import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class conectorSql {
	
	private static final String USERNAME="USER1";
	private static final String PASSWORD="test";
	private static final String CONN_STRING="jdbc:sqlserver://WKSWUSJO0954;databaseName=ODK";
	private static final String QueryTodo = "select * from Alumnos";
	
	private static ResultSet rs;
	private static Statement st;
	private static String query = "Select * from Alumnos";  

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD); // esta linea de codigo conecta a la base de datos
			System.out.println("Connected!");
			
			Statement stmt = conn.createStatement(); // Esta linea crea una variable statement para hacer una consulta
			ResultSet rs = stmt.executeQuery(query); // esta linea crea un result set con el query que definimos en la variable query
			
			// el siguiente bloque while presenta los resultados del query almacenado en el result set y lo imprime en consola 
			while (rs.next()) {
		        String Nombre = rs.getString("Nombre"); // crea el string nombre y le asigna el valor del result set que tiene la columna nombre
		        String Apellido1 = rs.getString("Apellido 1");
		        String Mensualidad = rs.getString("Mensualidad");
		         System.out.println(Nombre + " " + Apellido1 + " " + "Mensualidad: " + " " + Mensualidad);
		    }
			
			
						
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (conn!= null) {
				conn.close();
			} 
		}
				
	}

} // end of class


