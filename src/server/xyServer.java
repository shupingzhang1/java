package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * ѧԺserver
 * */
public class xyServer {
	private Connection coon;
	//	���캯��
	public xyServer () {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * getAllXy  ��ȡ����ѧԺ��Ϣ  ѧԺ���xyId  ѧԺ����xyName  ѧԺ��� xyInfo
	 * ����json��ʽ���� 
	 */
	public JsonArray getAllXy() {
		ResultSet rs=null;
		JsonArray jsonarray = new JsonArray();  
		try {
			Statement stmt=coon.createStatement();
			 rs=stmt.executeQuery("select * from xy  ");
			//System.out.println("aaaaaa"+rs+"aaaaaaaaaaaaaaaaaaaaa");
			 while(rs.next()) {
				 JsonObject jsonobj = new JsonObject(); 
				jsonobj.addProperty("xyId",rs.getString("xyId"));  
				jsonobj.addProperty("xyName", rs.getString("xyName"));  
				jsonobj.addProperty("xyInfo", rs.getString("xyInfo"));
				//System.out.print(rs.getString("xyInfo"));
				
				                     
				jsonarray.add(jsonobj);   
			}
			stmt.close();
			rs.close();
			coon.close();
//			System.out.print(jsonarray);
			return jsonarray;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jsonarray;   
		}
	}
	/*
	 * ���ѧԺ
	 * xyName:ѧԺ����  xyInfo��ѧԺ����
	 * */
	public String addXy(String xyName,String xyInfo) {
		
		try {
			String sql="insert into xy values(?,?)";
			PreparedStatement ps=coon.prepareStatement(sql);
			ps.setString(1,xyName);
			ps.setString(2,xyInfo);
			
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
			return "���ʧ�ܣ�";   
		}
		
	}
	
	/*
	 * ɾ��ѧԺ
	 * int xyId
	 * */
	
	public String deleteXy(int xyId) {
		try {
			String sql="delete from xy where xyId='"+xyId+"'";
			PreparedStatement ps=coon.prepareStatement(sql);
		
			
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
			return "ɾ��ʧ�ܣ�";   
		}
	}
	
}
