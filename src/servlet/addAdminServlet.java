package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import javabeen.admin;
import server.adminServer;
import server.userServer;

/**
 * Servlet implementation class addAdminServlet
 */
@WebServlet("/addAdminServlet")
public class addAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StringBuffer json=new StringBuffer();
		BufferedReader reader=request.getReader();
		System.out.println(reader);
		String line=null;
		while((line=reader.readLine())!=null) {
			json.append(line);
		}
		JsonObject jo = new JsonObject();//实例化
		JsonParser jp = new JsonParser();
		if(json.toString() != null){//转换成json时,result必须非空
			try{
			    jo = jp.parse(json.toString()).getAsJsonObject(); //result 转化成JsonObject
			    boolean a= jo.has("ret");  //判断转成json是否有ret这个属性
			  } catch(JsonParseException e){  //返回结果不是json格式
			      System.out.println("result非json格式");
			  }
		  } else {
		      System.out.println("result为空");
		}
//		System.out.println("------");
//		System.out.println(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1));
		adminServer admin=new adminServer();
		userServer user=new userServer();
		String rs1=user.addUser(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1),jo.get("email").toString().substring(1,jo.get("email").toString().length()-1),0);
		String rs2=admin.addAdminInfo(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1),jo.get("name").toString().substring(1,jo.get("name").toString().length()-1),Integer.parseInt(jo.get("sex").toString().substring(1,jo.get("sex").toString().length()-1)));
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		JsonObject jsonobj = new JsonObject(); 
		
		if(rs1=="添加成功"&&rs2=="添加成功") {
			jsonobj.addProperty("data","添加成功"); 
			out.print(jsonobj);	
		}else {
			jsonobj.addProperty("data","添加失败"); 
			out.print(jsonobj);	
		}
		out.flush();
		out.close();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StringBuffer json=new StringBuffer();
		BufferedReader reader=request.getReader();
		System.out.println(reader);
		String line=null;
		while((line=reader.readLine())!=null) {
			json.append(line);
		}
		JsonObject jo = new JsonObject();//实例化
		JsonParser jp = new JsonParser();
		if(json.toString() != null){//转换成json时,result必须非空
			try{
			    jo = jp.parse(json.toString()).getAsJsonObject(); //result 转化成JsonObject
			    boolean a= jo.has("ret");  //判断转成json是否有ret这个属性
			  } catch(JsonParseException e){  //返回结果不是json格式
			      System.out.println("result非json格式");
			  }
		  } else {
		      System.out.println("result为空");
		}
		System.out.println("------");
		System.out.println(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1));
		adminServer admin=new adminServer();
		userServer user=new userServer();
		String rs1=user.addUser(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1),jo.get("email").toString().substring(1,jo.get("email").toString().length()-1),0);
		String rs2=admin.addAdminInfo(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1),jo.get("name").toString().substring(1,jo.get("name").toString().length()-1),Integer.parseInt(jo.get("sex").toString().substring(1,jo.get("sex").toString().length()-1)));
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		JsonObject jsonobj = new JsonObject(); 
		
		if(rs1=="添加成功"&&rs2=="添加成功") {
			jsonobj.addProperty("data","添加成功"); 
			out.print(jsonobj);	
		}else {
			jsonobj.addProperty("data","添加失败"); 
			out.print(jsonobj);	
		}
		out.flush();
		out.close();
	}

}
