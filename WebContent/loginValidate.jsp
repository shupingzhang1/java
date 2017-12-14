<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.sql.ResultSet" %>
 <%request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="User" class="javabeen.User" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="User"/>
<jsp:useBean id="userServer" class="server.userServer" scope="page"></jsp:useBean>
<jsp:useBean id="adminServer" class="server.adminServer" scope="page"></jsp:useBean>
<jsp:useBean id="teacherServer" class="server.teacherServer" scope="page"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录验证</title>
</head>
<body>
	<%	out.println(User.getUserName());
		int stats=userServer.valiUser(User.getUserName(),User.getPassWord());
		if(stats==0){
			ResultSet rs=adminServer.getAdminInfo(User.getUserName());
			if(rs!=null){
				int adminId=rs.getInt("adminId");
				String userId =rs.getString("userId"); 
				String adminName =rs.getString("name");
				int sex=rs.getInt("sex");
				session.setAttribute("adminId", adminId);
				session.setAttribute("name", adminName);
				session.setAttribute("userId", userId);
				session.setAttribute("sex", sex);
				session.setAttribute("userType", 0);%>
		
		<% 	}response.sendRedirect("adminIndex.jsp");
		}else if(stats==1){
			ResultSet rs=teacherServer.getTeacherInfo(User.getUserName());
			if(rs!=null){
				int teacherId=rs.getInt("teacherId");
				String userId =rs.getString("userId"); 
				String teacherName =rs.getString("name");
				int sex=rs.getInt("sex");
				session.setAttribute("teacherId", teacherId);
				session.setAttribute("name", teacherName);
				session.setAttribute("userId", userId);
				session.setAttribute("userType", 1);
			}
			response.sendRedirect("teacherIndex.jsp");
		}else if(stats==2){
		
			response.sendRedirect("studentIndex.jsp");
			session.setAttribute("userId", User.getUserName());
			session.setAttribute("userType", 2);
		}else if(stats==-1){ %>   																					
			<jsp:forward page="login.jsp">
		       <jsp:param name="err" value="密码错误！" />
			</jsp:forward>
			
		<% }else if(stats==-2){%> 
			<jsp:forward page="login.jsp">
		       <jsp:param name="err" value="用户不存在！" />
			</jsp:forward>
		<% 
		}else if(stats==-4){%> 
		<jsp:forward page="login.jsp">
	       <jsp:param name="err" value="用户已在线！" />
		</jsp:forward>
	<% 
		}else{%>	
			<jsp:forward page="login.jsp">
		       <jsp:param name="err" value="登录失败，请稍后再试！" />
			</jsp:forward>;
		<% 	
		}
	%>
	
	
</body>
</html>