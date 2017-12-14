package dataconn;
import java.sql.Connection;
import java.sql.DriverManager;;
public class coon {
	public Connection getConn() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectDB="" //你的数据库;
			String user=""  //数据库用户名;                                    
	        String password=""  //数据库密码;
	        Connection con=DriverManager.getConnection(connectDB,user,password);
	        //System.out.print(con);
	        return con;
		} catch (Exception e) {
			
		}
		
		return null;
	}
}
