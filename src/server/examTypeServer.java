package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*考试类型
 * */
public class examTypeServer {
	private Connection coon;
	public examTypeServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * 获取所有考试类型信息
	 * examTypeId examTypeName examTypeProportion
	 * */
	public JsonArray getAllExamType() throws SQLException {
		JsonArray arr=new JsonArray(); //存放json数组
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
		//System.out.println("bj信息"+arr);
		return arr;
		
	}
	
	/*
	 * 添加考试类型
	 * examTypeName examTypeProportion
	 * 
	 * */
	
	public String addexamType(String examTypeName )  {
		String sql="insert into examType values(?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,examTypeName);
		
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
	 * 删除考试类型
	 * int examTypeId 
	 * */
	public String deleteExamType(int examTypeId) {
		String sql="delete from examType where examTypeId='"+examTypeId+"'";
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
