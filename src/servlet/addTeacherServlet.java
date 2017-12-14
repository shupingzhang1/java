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

import server.adminServer;
import server.teacherServer;
import server.userServer;

/**
 * Servlet implementation class addTeacherServlet
 */
@WebServlet("/addTeacherServlet")
public class addTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTeacherServlet() {
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
		JsonObject jo = new JsonObject();//ʵ����
		JsonParser jp = new JsonParser();
		if(json.toString() != null){//ת����jsonʱ,result����ǿ�
			try{
			    jo = jp.parse(json.toString()).getAsJsonObject(); //result ת����JsonObject
			    boolean a= jo.has("ret");  //�ж�ת��json�Ƿ���ret�������
			  } catch(JsonParseException e){  //���ؽ������json��ʽ
			      System.out.println("result��json��ʽ");
			  }
		  } else {
		      System.out.println("resultΪ��");
		}
//		System.out.println("------");
		System.out.println((jo.get("sex").toString())+"---"+(jo.get("xyId").toString()));
		teacherServer teacher=new teacherServer();
		userServer user=new userServer();
		String rs2=user.addUser(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1),jo.get("email").toString().substring(1,jo.get("email").toString().length()-1),1);
		String rs1=teacher.addTeacher(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1),jo.get("name").toString().substring(1,jo.get("name").toString().length()-1),Integer.parseInt(jo.get("sex").toString().substring(1,jo.get("sex").toString().length()-1)),Integer.parseInt(jo.get("xyId").toString().substring(1,jo.get("xyId").toString().length()-1)));
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		JsonObject jsonobj = new JsonObject(); 
		
		if(rs1=="��ӳɹ�"&&rs2=="��ӳɹ�") {
			jsonobj.addProperty("data","��ӳɹ�"); 
			out.print(jsonobj);	
		}else {
			jsonobj.addProperty("data","���ʧ��"); 
			out.print(jsonobj);	
		}
		out.flush();
		out.close();
	}

}
