package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * �༶Server  
 * */
public class bjServer {
	Connection coon;
	public bjServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * ��ȡ���а༶��Ϣ
	 * bjId �༶��� bjName �༶���� xyName ѧԺ���� zyName רҵ����
	 * */
	public JsonArray getAllBjInfo() throws SQLException {
		
		JsonArray arr=new JsonArray(); //���json����
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
		//System.out.println("bj��Ϣ"+arr);
		return arr;
		
	}
	
	/*
	 * ��ȡһ��ѧԺ���а༶��Ϣ xyId
	 * bjId �༶��� bjName �༶���� xyName ѧԺ���� zyName רҵ����
	 * */
	public JsonArray getBjInfoByXyId(int xyId) throws SQLException {
		JsonArray arr=new JsonArray(); //���json����
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
		//System.out.println("bj��Ϣ"+arr);
		return arr;
	}
	/*
	 * ��zyId��ȡ�༶��Ϣ  zyId
	 * bjId �༶��� bjName �༶���� xyName ѧԺ���� zyName רҵ����
	 * */
	public JsonArray getBjInfoByZyId(int zyId) throws SQLException {
		
		JsonArray arr=new JsonArray(); //���json����
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
		//System.out.println("bj��Ϣ"+arr);
		return arr;
		
	}
	
	/*
	 * ��ȡ�༶��Ϣ bjId
	 * bjId �༶��� bjName �༶���� xyName ѧԺ���� zyName רҵ����
	 * */
	public JsonArray getBjInfoByBjId(int bjId) throws SQLException {
		JsonArray arr=new JsonArray(); //���json����
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
		//System.out.println("bj��Ϣ"+arr);
		return arr;
	}
	/*
	 * ��Ӱ༶
	 * zyId רҵ��� bjName �༶���� 
	 * 
	 * */
	
	public String addBj(int zyId,String bjName)  {
		String sql="insert into bj values(?,?)";
		PreparedStatement ps;
		try {
			ps = coon.prepareStatement(sql);
			ps.setInt(1,zyId);
			ps.setString(2,bjName);
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
			return "���ʧ��";
		}
		
		
	}
	
}
