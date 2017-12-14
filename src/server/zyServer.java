package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * רҵserver
 * */
public class zyServer {
	private Connection coon;
	public zyServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * ��ȡ����רҵ��Ϣ zyId xyName zyName zyInfo 
	 * ����json��ʽ����
	 * */
	public JsonArray getAllZy() {
		ResultSet rs=null;
		JsonArray jsonarray = new JsonArray();  
		try {
			Statement stmt=coon.createStatement();
			 rs=stmt.executeQuery("select zyId,xyName,zyName,zyInfo  from xy,zy where xy.xyId=zy.xyId  ");
			//System.out.println("aaaaaa"+rs+"aaaaaaaaaaaaaaaaaaaaa");
			 while(rs.next()) {
				 JsonObject jsonobj = new JsonObject(); 
				jsonobj.addProperty("zyId",rs.getString("zyId"));  
				jsonobj.addProperty("xyName", rs.getString("xyName"));  
				jsonobj.addProperty("zyName", rs.getString("zyName"));
				jsonobj.addProperty("zyInfo", rs.getString("zyInfo"));
//				System.out.print(rs.getString("xyInfo"));                    
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
	 * ���רҵ��Ϣ
	 * xyId zyName  zyInfo
	 * */
		public String addZy(int xyId,String zyName,String  zyInfo) {
		
		try {
			String sql="insert into zy values(?,?,?)";
			PreparedStatement ps=coon.prepareStatement(sql);
			ps.setInt(1,xyId);
			ps.setString(2,zyName);
			ps.setString(3,zyInfo);
			
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
     * ����ѧԺ��ѯѧԺ�����רҵ ���zyId zyName
     * xyId 
     * */
		
	public JsonArray getZyInfoByXyId(int xyId) {
		JsonArray arr=new JsonArray();
		ResultSet rs=null;
		try {
			Statement stm=coon.createStatement();
			rs=stm.executeQuery("select zyId,zyName,zyInfo from xy,zy where zy.xyId=xy.xyId and xy.xyId='"+xyId+"'");
			
			 while(rs.next()) {
				 JsonObject jsonobj = new JsonObject(); 
				jsonobj.addProperty("zyId",rs.getString("zyId"));  
				jsonobj.addProperty("zyName", rs.getString("zyName"));
				jsonobj.addProperty("zyInfo", rs.getString("zyInfo"));
                 
				arr.add(jsonobj);   
			}
			stm.close();
			rs.close();
			coon.close();
//			System.out.print(arr);
			return arr;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return arr;   
		}
	}
}
