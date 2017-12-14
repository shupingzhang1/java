package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * 学院server
 * */
public class xyServer {
	private Connection coon;
	//	构造函数
	public xyServer () {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * getAllXy  获取所有学院信息  学院编号xyId  学院名称xyName  学院简介 xyInfo
	 * 返回json格式数据 
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
	 * 添加学院
	 * xyName:学院名称  xyInfo：学院介绍
	 * */
	public String addXy(String xyName,String xyInfo) {
		
		try {
			String sql="insert into xy values(?,?)";
			PreparedStatement ps=coon.prepareStatement(sql);
			ps.setString(1,xyName);
			ps.setString(2,xyInfo);
			
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
	 * 删除学院
	 * int xyId
	 * */
	
	public String deleteXy(int xyId) {
		try {
			String sql="delete from xy where xyId='"+xyId+"'";
			PreparedStatement ps=coon.prepareStatement(sql);
		
			
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
			return "删除失败！";   
		}
	}
	
}
