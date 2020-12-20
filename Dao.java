import java.sql.*;

class Dao
{
	public static Connection createconnection()
	{
		Connection con = null;

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("com.mysql.jdbc.Driver");
		
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","piyush","career");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306:xe","root","root");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return con;
	}
}