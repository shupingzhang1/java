package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import server.semesterServer;
import server.teacherServer;

/**
 * Servlet implementation class getTeacherHasProjectServlet
 */
@WebServlet("/getTeacherHasProjectServlet")
public class getTeacherHasProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTeacherHasProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StringBuffer json=new StringBuffer();
		BufferedReader reader=request.getReader();
		String line=null;
		while((line=reader.readLine())!=null) {
			json.append(line);
		}
		//System.out.println("--addXy"+json.toString());
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
		teacherServer teacher=new teacherServer(); 
		JsonArray arr=teacher.getTeacherHasProject(Integer.parseInt(jo.get("teacherId").toString().substring(1,jo.get("teacherId").toString().length()-1)));
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(arr);	
		out.flush();
		out.close();
	}

}
