package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import server.bjServer;
import server.zyServer;

/**
 * Servlet implementation class getBjInfoByZyIdServlet
 */
@WebServlet("/getBjInfoByZyIdServlet")
public class getBjInfoByZyIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getBjInfoByZyIdServlet() {
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
		bjServer bj=new bjServer();
		JsonArray Json=null;
		try {
			Json = bj.getBjInfoByZyId(Integer.parseInt(jo.get("zyId").toString().substring(1,jo.get("zyId").toString().length()-1)));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");  
        PrintWriter pw = response.getWriter();  
        pw.print(Json);
	}

}