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
 * 管理员server 
 * 
 * */
public class adminServer {
	private Connection coon;
	//	构造函数
	public adminServer() {
		coon=new dataconn.coon().getConn();
	}
	
	/*获取管理员信息
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
				return rs;  //用户不存在
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;   //登录失败
		}
		
	}
	
	
	
	/*
	 * 添加管理员信息
	 * */
	public String addAdminInfo(String userId,String name,int sex) {
		
		try {
			String sql="insert into admin values(?,?,?)";
			PreparedStatement ps=coon.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,name);
			ps.setInt(3,sex);
			int row=ps.executeUpdate();  //执行更新操作 返回影响的行数
			if(row>0) {
				return "添加成功";
			}
			
			ps.close();
			coon.close();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "添加失败！";   //登录失败
		}
		
	}
	
	/*
	 * 查询所有管理员信息  管理员id 姓名  用户名 性别 email 
	 * 返回json格式数据
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
				jsonobj.addProperty("sex", rs.getInt("sex")==0?"男":"女");
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
	 * 修改管理员密码
	 * userId newpassword oldpassowrd
	 * */
	public String setAdminPossword(String userId,String newPawd,String oldPawd) throws SQLException {
		String pswd=getAdminInfo(userId).getString("password");
		if(oldPawd==pswd) {
			try {
				String sql="update login set passWord='+newPawd+' where userId='+userId+' ";
				PreparedStatement ps=coon.prepareStatement(sql);
				
				int row=ps.executeUpdate();  //执行更新操作 返回影响的行数
				if(row>0) {
					return "修改成功";
				}
				
				ps.close();
				coon.close();
				return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "修改失败！";   //登录失败
			}
		}else {
			return "旧密码不正确，修改失败，请重新输入！";
		}
		
	}
	
	
	/*
	 * 删除管理员
	 * */
	public String deleteAdmin(String userId) {
		try {
			String sql2="delete from login where userId='"+userId+"'";
			PreparedStatement ps2=coon.prepareStatement(sql2);
			String sql1="delete from admin where userId='"+userId+"'";
			PreparedStatement ps1=coon.prepareStatement(sql1);
			
			
			int row1=ps1.executeUpdate();  //执行更新操作 返回影响的行数
			int row2=ps2.executeUpdate();
			if(row1>0&&row2>0) {
				return "删除成功";
			}
			
			ps1.close();
			ps2.close();
			coon.close();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败！";   //登录失败
		}
	
	}

	
	
}
