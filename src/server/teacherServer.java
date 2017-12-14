package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * 教师Server
 * */
public class teacherServer {
	private Connection coon;
	public teacherServer() {
		coon=new dataconn.coon().getConn();
	}
	
	/*获取教师信息
	 * @parms  userId
	 * */
	public ResultSet getTeacherInfo(String userId) {
		ResultSet rs=null;
		try {
			
			Statement stmt=coon.createStatement();
			 rs=stmt.executeQuery("select * from teacher  where userId='"+userId+"'");
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
	 * 获取所有教师的信息
	 * teacherId name userId sex xy email
	 * */
	public JsonArray getAllTeacherInfo() {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		try {
			Statement stm =coon.createStatement();
			rs=stm.executeQuery("select teacherId,name,teacher.userId,teacher.sex,xyName,login.email from teacher,login,xy where teacher.xyId=xy.xyId and teacher.userId=login.userId");
			while(rs.next()) {
				JsonObject obj=new JsonObject();
				obj.addProperty("teacherId", rs.getInt("teacherId"));
				obj.addProperty("name", rs.getString("name"));
				obj.addProperty("userId", rs.getString("userId"));
				obj.addProperty("sex", rs.getInt("sex")==0?'男':'女');
				obj.addProperty("xyName", rs.getString("xyName"));
				obj.addProperty("email", rs.getString("email"));
				arr.add(obj);
			}
			stm.close();
			rs.close();
			coon.close();
			return arr;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return arr;
		}
	}
	/*
	 * 添加教师信息
	 * userId name sex xyId
	 * */
	public String addTeacher(String userId,String name,int sex,int xyId) {
		String sql="insert into teacher values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,name);
			ps.setInt(3,sex);
			ps.setInt(4,xyId);
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
	
	/*删除教师信息
	 * userId
	 * */
	public String deltetTeacher(String userId) {
		String sql="DELETE FROM teacher WHERE userId = '"+userId+"'";
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
	
	/*输入教师的userId查询通知消息 */
	public JsonArray getTeacherMessageByUserId(String userId) {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		try {
			Statement stm =coon.createStatement();
			rs=stm.executeQuery("select * from message where getMessage='"+userId+"'");
			while(rs.next()) {
				JsonObject obj=new JsonObject();
				obj.addProperty("messageId", rs.getInt("messageId"));
				obj.addProperty("userId", rs.getString("userId"));
				obj.addProperty("messageTitle", rs.getString("messageTitle"));
				obj.addProperty("messageCountent", rs.getString("messageCountent"));
				obj.addProperty("messageTime", rs.getString("messageTime"));
				obj.addProperty("messageTypeId", rs.getString("messageTypeId"));
				obj.addProperty("getMessage", rs.getString("getMessage"));
				obj.addProperty("isRead", rs.getInt("isRead"));
				if(rs.getString("messageTypeId").equals("6")) {  //学生发给教师
					//System.out.println(rs.getString("userId"));
					//System.out.println("------------------------------------------");
					JsonArray a=new studentServer().getStudentInfoByUserId(rs.getString("userId"));
					JsonObject b=(JsonObject) a.get(0);
					//System.out.println("------------------------------------------");
					//System.out.println(b.get("name").toString().substring(1, b.get("name").toString().length()-1)+"---"+b.get("zyName").toString().substring(1,b.get("zyName").toString().length()-1)+b.get("bjName").toString().substring(1,b.get("bjName").toString().length()-1)+"班");
					String student=b.get("name").toString().substring(1, b.get("name").toString().length()-1)+"---"+b.get("zyName").toString().substring(1,b.get("zyName").toString().length()-1)+b.get("bjName").toString().substring(1,b.get("bjName").toString().length()-1)+"班";
					obj.addProperty("student",student );
				}
				arr.add(obj);
			}
			stm.close();
			rs.close();
			coon.close();
			return arr;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return arr;
		}
		
	}
   /*
    * 查询教师开设的课程
    * teacherId
    * */
	public JsonArray getTeacherHasProject(int teacherId) {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		try {
			Statement stm =coon.createStatement();
			rs=stm.executeQuery("select project.prName,teacher.Name,teacher.userId,bj.bjName,semester.semesterName,setPrId,credit,weekStart,weekEnd,time,zy.zyName,xy.xyName from project,teacher,semester,bj,setProject,xy,zy where setProject.prId=project.prId and setProject.teacherId=teacher.teacherId and setProject.bjId=bj.bjId and setProject.semesterId=semester.semesterId and bj.zyId=zy.zyId and zy.xyId=xy.xyId and setProject.teacherId='"+teacherId+"'");
			while(rs.next()) {
				JsonObject obj=new JsonObject();
				obj.addProperty("setPrId", rs.getInt("setPrId"));
				obj.addProperty("prId", rs.getInt("prId"));
				obj.addProperty("teacherId", rs.getInt("teacherId"));
				obj.addProperty("bjId", rs.getInt("bjId"));
				obj.addProperty("semesterId", rs.getInt("semesterId"));
				obj.addProperty("credit", rs.getFloat("credit"));
				obj.addProperty("weekStart", rs.getInt("weekStart"));
				obj.addProperty("weekend", rs.getInt("weekend"));
				obj.addProperty("time", rs.getString("time"));
				arr.add(obj);
			}
			stm.close();
			rs.close();
			coon.close();
			return arr;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return arr;
		}
	}
	
	
   /*
    * 教师开设课程
    * prId teacherId bjId semesterId crdeit weekStart weekEnd time
    * */
	public String teacherSetProject(int prId,int teacherId,int bjId,int semesterId,float credit,int weekStart,int weekEnd,String time) {
		String sql="insert into setProject values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setInt(1,prId);
			ps.setInt(2,teacherId);
			ps.setInt(3,bjId);
			ps.setInt(4,semesterId);
			ps.setFloat(5,credit);
			ps.setInt(6,weekStart);
			ps.setInt(7,weekEnd);
			ps.setString(8,time);
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
