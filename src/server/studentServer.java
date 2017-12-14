package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.java_cup.internal.runtime.Scanner;
/*
 * 学生  server
 * */
public class studentServer {
	private Connection coon;
	//	构造函数
	public studentServer() {
		coon=new dataconn.coon().getConn();
	}
	
	/*输入学号获取所有学生的信息
	 *  userId
	 *  studentId,userId,name,sex,idCard,rxdate,zhengzhi,bjName,zyName,xyName,jiguan,birthday 
	 *  */
	public JsonArray getStudentInfoByUserId(String userId) throws SQLException {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("select studentId,login.userId,name,sex,idCard,rxdate,zhengzhi,bj.bjName,zy.zyName,xyName,jiguan,birthday from student,xy,bj,login,zy where student.userId=login.userId and bj.bjId=student.bjId  and zy.zyId =bj.zyId and zy.xyId=xy.xyId and login.userId='"+userId+"'");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("studentId",rs.getInt("studentId"));
			obj.addProperty("userId",rs.getString("userId"));
			obj.addProperty("name",rs.getString("name"));
			obj.addProperty("sex",rs.getInt("sex")==0?"男":"女");
			obj.addProperty("idCard",rs.getString("idCard"));
			obj.addProperty("rxdate",rs.getString("rxdate"));
			obj.addProperty("zhengzhi",rs.getString("zhengzhi"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("jiguan",rs.getString("jiguan"));
			obj.addProperty("birthday",rs.getString("birthday"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
	}
	/*获取所有学生的信息
	 *  
	 *  studentId,userId,name,sex,idCard,rxdate,zhengzhi,bjName,zyName,xyName,jiguan,birthday 
	 *  */
	public JsonArray getAllStudentInfo() throws SQLException {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("select studentId,login.userId,name,sex,idCard,rxdate,zhengzhi,bj.bjName,zy.zyName,xyName,jiguan,birthday from student,xy,bj,login,zy\r\n" + 
				"where student.userId=login.userId and bj.bjId=student.bjId  and zy.zyId =bj.zyId and zy.xyId=xy.xyId");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("studentId",rs.getInt("studentId"));
			obj.addProperty("userId",rs.getString("userId"));
			obj.addProperty("name",rs.getString("name"));
			obj.addProperty("sex",rs.getInt("sex")==0?"男":"女");
			obj.addProperty("idCard",rs.getString("idCard"));
			obj.addProperty("rxdate",rs.getString("rxdate"));
			obj.addProperty("zhengzhi",rs.getString("zhengzhi"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("jiguan",rs.getString("jiguan"));
			obj.addProperty("birthday",rs.getString("birthday"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
	}
	/*获取一个学院所有学生的信息
	 *  xyId
	 *  studentId,userId,name,sex,idCard,rxdate,zhengzhi,bjName,zyName,xyName,jiguan,birthday 
	 *  */
	public JsonArray getStudentInfoByXyId(int xyId) throws SQLException {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("select studentId,login.userId,name,sex,idCard,rxdate,zhengzhi,bj.bjName,zy.zyName,xyName,jiguan,birthday from student,xy,bj,login,zy\r\n" + 
				"where student.userId=login.userId and bj.bjId=student.bjId  and zy.zyId =bj.zyId and zy.xyId=xy.xyId and xy.xyId='"+xyId+"'");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("studentId",rs.getInt("studentId"));
			obj.addProperty("userId",rs.getString("userId"));
			obj.addProperty("name",rs.getString("name"));
			obj.addProperty("sex",rs.getInt("sex")==0?"男":"女");
			obj.addProperty("idCard",rs.getString("idCard"));
			obj.addProperty("rxdate",rs.getString("rxdate"));
			obj.addProperty("zhengzhi",rs.getString("zhengzhi"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("jiguan",rs.getString("jiguan"));
			obj.addProperty("birthday",rs.getString("birthday"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
	}
	/*获取一个专业所有学生的信息
	 *  zyId
	 *  studentId,userId,name,sex,idCard,rxdate,zhengzhi,bjName,zyName,xyName,jiguan,birthday 
	 *  */
	public JsonArray getStudentInfoByZyId(int zyId) throws SQLException {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("select studentId,login.userId,name,sex,idCard,rxdate,zhengzhi,bj.bjName,zy.zyName,xyName,jiguan,birthday from student,xy,bj,login,zy\r\n" + 
				"where student.userId=login.userId and bj.bjId=student.bjId  and zy.zyId =bj.zyId and zy.xyId=xy.xyId and zy.zyId='"+zyId+"'");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("studentId",rs.getInt("studentId"));
			obj.addProperty("userId",rs.getString("userId"));
			obj.addProperty("name",rs.getString("name"));
			obj.addProperty("sex",rs.getInt("sex")==0?"男":"女");
			obj.addProperty("idCard",rs.getString("idCard"));
			obj.addProperty("rxdate",rs.getString("rxdate"));
			obj.addProperty("zhengzhi",rs.getString("zhengzhi"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("jiguan",rs.getString("jiguan"));
			obj.addProperty("birthday",rs.getString("birthday"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
	}
	/*获取一个班级所有学生的信息
	 *  bjId
	 *  studentId,userId,name,sex,idCard,rxdate,zhengzhi,bjName,zyName,xyName,jiguan,birthday 
	 *  */
	public JsonArray getStudentInfoByBjId(int bjId) throws SQLException {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		Statement stm=coon.createStatement();
		rs=stm.executeQuery("select studentId,login.userId,name,sex,idCard,rxdate,zhengzhi,bj.bjName,zy.zyName,xyName,jiguan,birthday from student,xy,bj,login,zy\r\n" + 
				"where student.userId=login.userId and bj.bjId=student.bjId  and zy.zyId =bj.zyId and zy.xyId=xy.xyId and bj.bjId='"+bjId+"'");
		while(rs.next()){
			JsonObject obj=new JsonObject();
			obj.addProperty("studentId",rs.getInt("studentId"));
			obj.addProperty("userId",rs.getString("userId"));
			obj.addProperty("name",rs.getString("name"));
			obj.addProperty("sex",rs.getInt("sex")==0?"男":"女");
			obj.addProperty("idCard",rs.getString("idCard"));
			obj.addProperty("rxdate",rs.getString("rxdate"));
			obj.addProperty("zhengzhi",rs.getString("zhengzhi"));
			obj.addProperty("bjName",rs.getString("bjName"));
			obj.addProperty("zyName",rs.getString("zyName"));
			obj.addProperty("xyName",rs.getString("xyName"));
			obj.addProperty("jiguan",rs.getString("jiguan"));
			obj.addProperty("birthday",rs.getString("birthday"));
			arr.add(obj);
		}
		stm.close();
		rs.close();
		coon.close();
		//System.out.print(arr);
		//System.out.println("bj信息"+arr);
		return arr;
	}
	
	
	/*删除学生
	 * studentId
	 * */
	public String deleteStudent(String userId) throws SQLException {
		
		String sql1="DELETE FROM student WHERE userId = '"+userId+"'";
		String sql2="DELETE FROM login WHERE userId = '"+userId+"'";
		PreparedStatement ps1,ps2;
		try {
			ps1 = coon.prepareStatement(sql1);
			ps2 = coon.prepareStatement(sql2);
			
			
			int row1=ps1.executeUpdate();  //执行更新操作 返回影响的行数
			int row2=ps2.executeUpdate();
			if(row1>0&&row2>0) {
				return "删除成功";
			}
			ps1.close();ps2.close();
			coon.close();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败";
		}
		
	}
	/*添加学生
	 * userId  name  sex idCard  rxDate zhengzhi bjId jiguan birthday
	 * */
	public String addStudent(String userId,String name,int sex,String idCard,String rxDate,String zhengzhi,int bjId,String jiguan,String birthday) {
		String sql="insert into student values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,name);
			ps.setInt(3,sex);
			ps.setString(4,idCard);
			ps.setString(5,rxDate);
			ps.setString(6,zhengzhi);
			ps.setInt(7,bjId);
			ps.setString(8,jiguan);
			ps.setString(9,birthday);
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
