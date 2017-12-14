package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//ѧ��server
public class semesterServer {
	private Connection coon;
	public semesterServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * ��ȡ����ѧ����Ϣ
	 * 
	 * */
	public JsonArray getAllSemester() throws SQLException {
		JsonArray arr=new JsonArray(); //���json����
		ResultSet rs=null;
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("Select * from semester");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("semesterId",rs.getInt("semesterId"));
			obj.addProperty("semesterName",rs.getString("semesterName"));
		
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj��Ϣ"+arr);
		return arr;
		
	}
	
	/*
	 * ���ѧ��
	 * semesterName
	 * 
	 * */
	
	public String addSemester(String semesterName)  {
		String sql="insert into semester values(?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,semesterName);
		
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
	 * ɾ��ѧѧ��
	 * int semesterId
	 * */
	public String deleteSemester(int semesterId) {
		String sql="delete from semester where semesterId='"+semesterId+"' ";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			
		
			int row=ps.executeUpdate();  //ִ�и��²��� ����Ӱ�������
			if(row>0) {
				return "ɾ���ɹ�";
			}
			ps.close();
			coon.close();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ɾ��ʧ��";
		}
		
	}
}
