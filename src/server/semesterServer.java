package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//学期server
public class semesterServer {
	private Connection coon;
	public semesterServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * 获取所有学期信息
	 * 
	 * */
	public JsonArray getAllSemester() throws SQLException {
		JsonArray arr=new JsonArray(); //存放json数组
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
		//System.out.println("bj信息"+arr);
		return arr;
		
	}
	
	/*
	 * 添加学期
	 * semesterName
	 * 
	 * */
	
	public String addSemester(String semesterName)  {
		String sql="insert into semester values(?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,semesterName);
		
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
	/*
	 * 删除学学期
	 * int semesterId
	 * */
	public String deleteSemester(int semesterId) {
		String sql="delete from semester where semesterId='"+semesterId+"' ";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			
		
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
}
