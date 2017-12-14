package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * 班级Server  
 * */
public class bjServer {
	Connection coon;
	public bjServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * 获取所有班级信息
	 * bjId 班级编号 bjName 班级姓名 xyName 学院名称 zyName 专业名称
	 * */
	public JsonArray getAllBjInfo() throws SQLException {
		
		JsonArray arr=new JsonArray(); //存放json数组
		ResultSet rs=null;
		
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("Select bjId,bjName,xyName,zyName from bj,xy,zy where zy.xyId=xy.xyId and bj.zyId=zy.zyId");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("bjId",rs.getString("bjId"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
		
	}
	
	/*
	 * 获取一个学院所有班级信息 xyId
	 * bjId 班级编号 bjName 班级姓名 xyName 学院名称 zyName 专业名称
	 * */
	public JsonArray getBjInfoByXyId(int xyId) throws SQLException {
		JsonArray arr=new JsonArray(); //存放json数组
		ResultSet rs=null;
		
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("Select bjId,bjName,xyName,zyName from bj,xy,zy where zy.xyId=xy.xyId and bj.zyId=zy.zyId and xy.xyId='"+xyId+"'");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("bjId",rs.getString("bjId"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
	}
	/*
	 * 用zyId获取班级信息  zyId
	 * bjId 班级编号 bjName 班级姓名 xyName 学院名称 zyName 专业名称
	 * */
	public JsonArray getBjInfoByZyId(int zyId) throws SQLException {
		
		JsonArray arr=new JsonArray(); //存放json数组
		ResultSet rs=null;
		
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("Select bjId,bjName,xyName,zyName from bj,xy,zy where zy.xyId=xy.xyId and bj.zyId=zy.zyId and zy.zyId='"+zyId+"'");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("bjId",rs.getString("bjId"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
		
	}
	
	/*
	 * 获取班级信息 bjId
	 * bjId 班级编号 bjName 班级姓名 xyName 学院名称 zyName 专业名称
	 * */
	public JsonArray getBjInfoByBjId(int bjId) throws SQLException {
		JsonArray arr=new JsonArray(); //存放json数组
		ResultSet rs=null;
		
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("Select bjId,bjName,xyName,zyName from bj,xy,zy where zy.xyId=xy.xyId and bj.zyId=zy.zyId and bj.bjId='"+bjId+"'");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("bjId",rs.getString("bjId"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
	}
	/*
	 * 添加班级
	 * zyId 专业编号 bjName 班级名称 
	 * 
	 * */
	
	public String addBj(int zyId,String bjName)  {
		String sql="insert into bj values(?,?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setInt(1,zyId);
			ps.setString(2,bjName);
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
			return "添加失败";
		}
		
		
	}
	
}
