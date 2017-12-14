package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.StampedLock;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * �γ�server
 * */
public class projectServer {
	private Connection coon;
	public projectServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * ��ӿγ�
	 * prName  prInfo
	 * */
	public String addProject(String prName,String prInfo) {
		String sql="insert into project values(?,?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,prName);
			ps.setString(2,prInfo);
			
			int row=ps.executeUpdate();  //ִ�и��²��� ����Ӱ�������
			if(row>0) {
				return "��ӳɹ�";
			}
			ps.close();
			coon.close();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "���ʧ��";
		}
	}
	/*
	 * ��ȡ���пγ���Ϣ prName  prInfo
	 * */
	public JsonArray getAllProject() {
		JsonArray arr=new JsonArray();
		try {
			Statement stm=coon.createStatement();
			ResultSet rs=stm.executeQuery("select * from project");
			while(rs.next()) {
				JsonObject obj=new JsonObject();
				obj.addProperty("prId", rs.getString("prId"));
				obj.addProperty("prName", rs.getString("prName"));
				obj.addProperty("prInfo", rs.getString("prInfo"));
				arr.add(obj);
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return arr;
		}
		
	}
}
