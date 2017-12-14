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

import server.examTypeServer;
import server.projectServer;

/**
 * Servlet implementation class addExamTypeServlet
 */
@WebServlet("/addExamTypeServlet")
public class addExamTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addExamTypeServlet() {
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
		System.out.println("--addXy"+json.toString());
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
		examTypeServer exam=new examTypeServer();  //(int zyId,String bjName)
//System.out.println(jo.toString()+"----------------------------------------");
		String rs1=exam.addexamType(jo.get("examTypeName").toString().substring(1,jo.get("examTypeName").toString().length()-1));
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		JsonObject jsonobj = new JsonObject(); 
		
		if(rs1=="��ӳɹ�") {
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
