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
import server.userServer;

/**
 * Servlet implementation class changePasswordServlet
 */
@WebServlet("/changePasswordServlet")
public class changePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePasswordServlet() {
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
		System.out.println(reader);
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
		userServer user=new userServer();
		String rs2=user.changeUserPassWord(jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1),jo.get("oldPassWd").toString().substring(1,jo.get("oldPassWd").toString().length()-1),jo.get("newPassWd").toString().substring(1,jo.get("newPassWd").toString().length()-1));
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		JsonObject jsonobj = new JsonObject(); 
		
		if(rs2=="�޸ĳɹ�") {
			jsonobj.addProperty("data","�޸ĳɹ�"); 
			out.print(jsonobj);	
		}else if(rs2=="���������") {
			jsonobj.addProperty("data","���������"); 
			out.print(jsonobj);	
		}else {
			jsonobj.addProperty("data","�޸�ʧ��"); 
			out.print(jsonobj);	
		}
		out.flush();
		out.close();
	}

	

}
