package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class messageServer {
	private Connection coon;
	public messageServer() {
		coon=new dataconn.coon().getConn();
	}
	
  /*
   * ������ϢΪ�Ѷ�
   * ����  int messageId
   * */
	public int readMessage(int messageId) {
		String sql="update message set isRead=1 where messageId='"+messageId+"'";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			int row=ps.executeUpdate();  //ִ�и��²��� ����Ӱ�������
			if(row>0) {
				return 1;
			}
			ps.close();
			coon.close();
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
