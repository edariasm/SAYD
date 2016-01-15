import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class connectiontest {
	
	private static final String USERNAME="USER1";
	private static final String PASSWORD="test";
	private static final String CONN_STRING="jdbc:sqlserver://WKSWUSJO0954;databaseName=ODK";
											 
		
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("Connected!");
			
			
			
			Statement s1 = conn.createStatement();
            ResultSet rs = s1.executeQuery("SELECT nombre FROM Alumnos");
            String[] result = new String[2];
            if(rs!=null){
                while (rs.next()){
                    for(int i = 1; i <result.length ;i++)
                    {
                        for(int j = 1; j <result.length;j++)
                        {
                            result[j]=rs.getString(i);
                        System.out.println(result[j]);
                    }
                    }
                }
            }
			
			
			
			
			
						
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			
			if (conn!= null) {
				conn.close();
			} 
		}
		
	}

}
