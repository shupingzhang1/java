<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<% String adminName=(String)session.getAttribute("name") ;
	String userId=(String)session.getAttribute("userId");
	session.setMaxInactiveInterval(24*60*3600)	;
    	if(adminName==null){
    		response.sendRedirect("login.jsp");
    	}
    	
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>管理员</title>
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/admin.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<!-- 导航栏 -->
	<div class="nav">
		<span class="nav_title">学生成绩管理平台(管理员)</span> <span class="user">欢迎你
		<span class="user_name"><%=adminName %></span>
		<span class="logout" value="<%=userId %>">退出 </span></span> <span class="clear"></span>
	</div>
	<div class="container-fluid">
		<div class="container_left">
			<div class="panel-group" id="panel-981931">

				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title collapsed" data-toggle="collapse"
							data-parent="#panel-981931" href="#panel-element-72579">查看</a>
					</div>
					<div id="panel-element-72579" class="panel-collapse collapse">
						<div class="panel-body">

							<div class="list-group">
								<a href="#" class="list-group-item" id="see1"> 查看学院-专业-班级 </a>
								<a href="#" class="list-group-item" id="see2">查看教师</a> <a
									href="#" class="list-group-item" id="see3">查看管理员</a> <a
									href="#" class="list-group-item" id="see4">查看学生</a> <a
									href="#" class="list-group-item" id="see5">查看考试类型</a> 
									 <a
									href="#" class="list-group-item" id="see6">查看学期</a> 
								<!--  <a href="#" class="list-group-item" id="see3" >查看已添加人员信息</a> -->

							</div>

						</div>
					</div>

				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title collapsed" data-toggle="collapse"
							data-parent="#panel-981931" href="#panel-element-137063 ">修改</a>
					</div>
					<div id="panel-element-137063" class="panel-collapse collapse">
						<div class="panel-body">
							<div class="list-group">
								<a href="#" class="list-group-item" id="change1"> 修改登录密码 </a> 
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title collapsed" data-toggle="collapse"
							data-parent="#panel-981931" href="#panel-element-137061 ">添加</a>
					</div>
					<div id="panel-element-137061" class="panel-collapse collapse">
						<div class="panel-body">
							<div class="list-group">
								<a href="#" class="list-group-item" id="add1"> 添加管理员 </a> <a
									href="#" class="list-group-item" id="add2">添加学院</a> <a
									href="#" class="list-group-item" id="add3">添加专业</a> <a
									href="#" class="list-group-item" id="add4">添加班级</a> <a
									href="#" class="list-group-item" id="add5">添加教师</a> 
									<a
									href="#" class="list-group-item" id="add9">添加学生</a><a
									href="#" class="list-group-item" id="add6">添加课程</a> <a
									href="#" class="list-group-item" id="add7">添加学期</a> <a
									href="#" class="list-group-item" id="add8">添加考试类型</a>
							</div>
						</div>
					</div>

				</div>
				<!-- <div class="panel panel-default">
								<div class="panel-heading">
									 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-981931" href="#panel-element-137064 ">统计</a>
								</div>
								<div id="panel-element-137064" class="panel-collapse collapse">
									<div class="panel-body">
									<div class="list-group">
									  <a href="#" class="list-group-item" id="tonji1" >
									    aaa
									  </a> 
									</div>
									</div>
								</div>
								
							</div> -->
			</div>

		</div>
		
		<div class="container_right" >
			
	    	
	    	
			
			
		</div>


	</div>
	<!-- 底部 -->
	<div class="footer">
		<span class="footer_text">Copyright 2017 JAVA四人组. All Rights
			Reserved.</span>
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/admin.js"></script>
	

</body>
</html>