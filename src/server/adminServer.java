package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/*
 * ����Աserver 
 * 
 * */
public class adminServer {
	private Connection coon;
	//	���캯��
	public adminServer() {
		coon=new dataconn.coon().getConn();
	}
	
	/*��ȡ����Ա��Ϣ
	 * @parms  userId
	 * */
	public ResultSet getAdminInfo(String userId) {
		ResultSet rs=null;
		try {
			
			Statement stmt=coon.createStatement();
			 rs=stmt.executeQuery("select * from admin  where userId='"+userId+"'");
			//System.out.print("_________"+userId);
			if(rs.next()) {
				return rs;
			}else {
				return rs;  //�û�������
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;   //��¼ʧ��
		}
		
	}
	
	
	
	/*
	 * ��ӹ���Ա��Ϣ
	 * */
	public String addAdminInfo(String userId,String name,int sex) {
		
		try {
			String sql="insert into admin values(?,?,?)";
			PreparedStatement ps=coon.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,name);
			ps.setInt(3,sex);
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
			return "���ʧ�ܣ�";   //��¼ʧ��
		}
		
	}
	
	/*
	 * ��ѯ���й���Ա��Ϣ  ����Աid ����  �û��� �Ա� email 
	 * ����json��ʽ����
	 * */
	public JsonArray getAllAdminInfo() {
		
		ResultSet rs=null;
		 JsonArray jsonarray = new JsonArray();  
		 
		try {
			Statement stmt=coon.createStatement();
			 rs=stmt.executeQuery("select  adminId,login.userId,name,sex,email from admin,login where login.userId=admin.userId ");
//			System.out.println("aaaaaa"+rs+"aaaaaaaaaaaaaaaaaaaaa");
			 while(rs.next()) {
				 JsonObject jsonobj = new JsonObject(); 
				jsonobj.addProperty("adminId",rs.getString("adminId"));  
				jsonobj.addProperty("userId", rs.getString("userId"));  
				jsonobj.addProperty("name", rs.getString("name"));
				jsonobj.addProperty("sex", rs.getInt("sex")==0?"��":"Ů");
				jsonobj.addProperty("email", rs.getString("email"));
				                     
				jsonarray.add(jsonobj);   
			}
			stmt.close();
			rs.close();
			coon.close();
			return jsonarray;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jsonarray;   
		}
	}
	
	/*
	 * �޸Ĺ���Ա����
	 * userId newpassword oldpassowrd
	 * */
	public String setAdminPossword(String userId,String newPawd,String oldPawd) throws SQLException {
		String pswd=getAdminInfo(userId).getString("password");
		if(oldPawd==pswd) {
			try {
				String sql="update login set passWord='+newPawd+' where userId='+userId+' ";
				PreparedStatement ps=coon.prepareStatement(sql);
				
				int row=ps.executeUpdate();  //ִ�и��²��� ����Ӱ�������
				if(row>0) {
					return "�޸ĳɹ�";
				}
				
				ps.close();
				coon.close();
				return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "�޸�ʧ�ܣ�";   //��¼ʧ��
			}
		}else {
			return "�����벻��ȷ���޸�ʧ�ܣ����������룡";
		}
		
	}
	
	
	/*
	 * ɾ������Ա
	 * */
	public String deleteAdmin(String userId) {
		try {
			String sql2="delete from login where userId='"+userId+"'";
			PreparedStatement ps2=coon.prepareStatement(sql2);
			String sql1="delete from admin where userId='"+userId+"'";
			PreparedStatement ps1=coon.prepareStatement(sql1);
			
			
			int row1=ps1.executeUpdate();  //ִ�и��²��� ����Ӱ�������
			int row2=ps2.executeUpdate();
			if(row1>0&&row2>0) {
				return "ɾ���ɹ�";
			}
			
			ps1.close();
			ps2.close();
			coon.close();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ɾ��ʧ�ܣ�";   //��¼ʧ��
		}
	
	}

	
	
}
