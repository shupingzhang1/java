package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * 专业server
 * */
public class zyServer {
	private Connection coon;
	public zyServer() {
		coon=new dataconn.coon().getConn();
	}
	/*
	 * 获取所有专业信息 zyId xyName zyName zyInfo 
	 * 返回json格式数据
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
	 * 添加专业信息
	 * xyId zyName  zyInfo
	 * */
		public String addZy(int xyId,String zyName,String  zyInfo) {
		
		try {
			String sql="insert into zy values(?,?,?)";
			PreparedStatement ps=coon.prepareStatement(sql);
			ps.setInt(1,xyId);
			ps.setString(2,zyName);
			ps.setString(3,zyInfo);
			
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
     * 输入学院查询学院下面的专业 输出zyId zyName
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
