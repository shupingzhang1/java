package dataconn;
import java.sql.Connection;
import java.sql.DriverManager;;
public class coon {
	public Connection getConn() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectDB="" //������ݿ�;
			String user=""  //���ݿ��û���;                                    
	        String password=""  //���ݿ�����;
	        Connection con=DriverManager.getConnection(connectDB,user,password);
	        //System.out.print(con);
	        return con;
		} catch (Exception e) {
			
		}
		
		return null;
	}
}
