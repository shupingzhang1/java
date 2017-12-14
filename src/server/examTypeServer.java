package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*��������
 * */
public class examTypeServer {
	private Connection coon;
	public examTypeServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * ��ȡ���п���������Ϣ
	 * examTypeId examTypeName examTypeProportion
	 * */
	public JsonArray getAllExamType() throws SQLException {
		JsonArray arr=new JsonArray(); //���json����
		ResultSet rs=null;
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("Select * from examType");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("examTypeId",rs.getInt("examTypeId"));
			obj.addProperty("examTypeName",rs.getString("examTypeName"));
			
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
	 * ��ӿ�������
	 * examTypeName examTypeProportion
	 * 
	 * */
	
	public String addexamType(String examTypeName )  {
		String sql="insert into examType values(?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,examTypeName);
		
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
	 * ɾ����������
	 * int examTypeId 
	 * */
	public String deleteExamType(int examTypeId) {
		String sql="delete from examType where examTypeId='"+examTypeId+"'";
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
