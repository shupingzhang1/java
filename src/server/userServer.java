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
 * server类 数据库交互
 * */
public class userServer {
	private Connection coon;
	public userServer() {
		coon=new dataconn.coon().getConn();
	}
	
	/*验证用户是否合法  是否在线
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
						return -4;  //用户已经在线
					}else {
						setUserLoginStats(name,1);
						return userType;
					}
					
				}else {
					return -1;  //密码错误
				}
			}else {
				return -2;  //用户不存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;   //登录失败
		}
		
	}
	/*判断用户的类型
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
				return -2;  //用户不存在
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;   //登录失败
		}
		
	}
	/*
	 * 改变用户的在线状态  stats=1:在线
	 * */
	public String setUserLoginStats(String userId,int stats) {
		try {
			String sql="update login set isLogin='"+stats+"' where userId='"+userId+"'";
			PreparedStatement ps=coon.prepareStatement(sql);
			
			
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
			return "添加失败！";   
		}
	}
	/*
	 * 获取所有用户信息
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
	 * 添加用户 login
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
	 * 删除用户 
	 * userId
	 * */
	public String deleteUser(String userId) {
		String sql="DELETE FROM login WHERE userId = ?";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,userId);
			int row=ps.executeUpdate();  //执行更新操作 返回影响的行数
			if(row>0) {
				return "删除成功";
			}
			ps.close();
			coon.close();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败";
		}
	}
	/*修改密码
	 * userId oldPassWd  newPassWd
	 * */
	public String changeUserPassWord(String userId,String oldPassWd,String newPassWd) {
		int a=valiUser(userId,oldPassWd);
		if(a==-1) {
			return "旧密码错误";
		}else {
			String sql="update login set passWord='"+newPassWd+"' where userId='"+userId+"'";
			PreparedStatement ps;
			try {
				ps = coon.prepareStatement(sql);
				
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
				return "修改失败";
			}
		}
	}
	
	
}
