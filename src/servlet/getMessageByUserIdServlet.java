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

import server.teacherServer;
import server.userServer;

/**
 * Servlet implementation class getMessageByUserId
 */
@WebServlet("/getMessageByUserIdServlet")
public class getMessageByUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getMessageByUserIdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StringBuffer json = new StringBuffer();
		BufferedReader reader = request.getReader();
		//System.out.println(reader);
		String line = null;
		while ((line = reader.readLine()) != null) {
			json.append(line);
		}
		JsonObject jo = new JsonObject();// 实例化
		JsonParser jp = new JsonParser();
		if (json.toString() != null) {// 转换成json时,result必须非空
			try {
				jo = jp.parse(json.toString()).getAsJsonObject(); // result 转化成JsonObject
				boolean a = jo.has("ret"); // 判断转成json是否有ret这个属性
			} catch (JsonParseException e) { // 返回结果不是json格式
				System.out.println("result非json格式");
			}
		} else {
			System.out.println("result为空");
		}
		// jo.get("userId").toString().substring(1,jo.get("userId").toString().length()-1)
		userServer user = new userServer();
		// userType 0:管理员 1:教师 3：学生 -2：用户不存在
		int userType = user.userType(jo.get("userId").toString().substring(1, jo.get("userId").toString().length() - 1));
		if (userType == 1) {
			teacherServer teacher = new teacherServer();
			JsonArray arr = teacher.getTeacherMessageByUserId(
					jo.get("userId").toString().substring(1, jo.get("userId").toString().length() - 1));
			//System.out.println(arr);
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");  
	        PrintWriter pw = response.getWriter();  
	        pw.print(arr);
		}
	}

}
