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
 * server�� ���ݿ⽻��
 * */
public class userServer {
	private Connection coon;
	public userServer() {
		coon=new dataconn.coon().getConn();
	}
	
	/*��֤�û��Ƿ�Ϸ�  �Ƿ�����
	 * @parms  name   password
	 * */
	public int valiUser(String name,String password) {
		try {
			Statement stmt=coon.createStatement();
			ResultSet rs=stmt.executeQuery("select * from login  where userId='"+name+"'");
			//System.out.print("_________"+name+"_____"+password);
			if(rs.next()) {
				int userType= rs.getInt("userType");
				String passWd=rs.getString("passWord");
				int isLogin=rs.getInt("isLogin");
				//System.out.println(userType+"---"+passWd+"----"+isLogin);
				if(passWd.equals(password)) {
					if(isLogin==1) {
						return -4;  //�û��Ѿ�����
					}else {
						setUserLoginStats(name,1);
						return userType;
					}
					
				}else {
					return -1;  //�������
				}
			}else {
				return -2;  //�û�������
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;   //��¼ʧ��
		}
		
	}
	/*�ж��û�������
	 * @parms  userId
	 * */
	public int userType(String userId) {
		try {
			Statement stmt=coon.createStatement();
			ResultSet rs=stmt.executeQuery("select * from login  where userId='"+userId+"'");
			//System.out.print("_________"+name+"_____"+password);
			if(rs.next()) {
				int userType= rs.getInt("userType");
				return userType;
			}else {
				return -2;  //�û�������
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;   //��¼ʧ��
		}
		
	}
	/*
	 * �ı��û�������״̬  stats=1:����
	 * */
	public String setUserLoginStats(String userId,int stats) {
		try {
			String sql="update login set isLogin='"+stats+"' where userId='"+userId+"'";
			PreparedStatement ps=coon.prepareStatement(sql);
			
			
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
	 * ��ȡ�����û���Ϣ
	 * */
	public JsonArray getAllUserInfo() {
		JsonArray arr=new JsonArray();
		Statement stm;
		try {
			stm = coon.createStatement();
			ResultSet rs=stm.executeQuery("select * from login");
			while(rs.next()) {
				JsonObject obj=new JsonObject();
				obj.addProperty("userId", rs.getString("userId"));
				arr.add(obj);
			}
			return arr;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return arr;
		}
		
	}
	
	/*
	 * ����û� login
	 * */
	public String addUser(String userId,String email,int userType) {
		ResultSet rs=null;
		try {
			String sql="insert into login values(?,?,?,?,?)";
			PreparedStatement ps=coon.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,"123456");
			ps.setString(3,email);
			ps.setInt(4,userType);
			ps.setInt(5,0);
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
	 * ɾ���û� 
	 * userId
	 * */
	public String deleteUser(String userId) {
		String sql="DELETE FROM login WHERE userId = ?";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,userId);
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
	/*�޸�����
	 * userId oldPassWd  newPassWd
	 * */
	public String changeUserPassWord(String userId,String oldPassWd,String newPassWd) {
		int a=valiUser(userId,oldPassWd);
		if(a==-1) {
			return "���������";
		}else {
			String sql="update login set passWord='"+newPassWd+"' where userId='"+userId+"'";
			PreparedStatement ps;
			try {
				ps = coon.prepareStatement(sql);
				
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
				return "�޸�ʧ��";
			}
		}
	}
	
	
}
