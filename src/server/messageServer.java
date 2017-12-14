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
   * 设置消息为已读
   * 参数  int messageId
   * */
	public int readMessage(int messageId) {
		String sql="update message set isRead=1 where messageId='"+messageId+"'";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			int row=ps.executeUpdate();  //执行更新操作 返回影响的行数
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
