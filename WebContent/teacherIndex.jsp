<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <% 	String teacherName=(String)session.getAttribute("name") ;
		String userId=(String)session.getAttribute("userId");
    	int teacherId=(int)session.getAttribute("teacherId");
    	if(teacherName==null){
    		response.sendRedirect("login.jsp");
    	}
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Insert title here</title>
<link rel="stylesheet" href="style/bootstrap.min.css">
<link rel="stylesheet" href="style/bootstrap-theme.min.css">
<link rel="stylesheet" href="style/teacher.css">
</head>
<body>
	<!-- 页面标题 -->
	<div class="page_title">
		<div class="title">教务管理系统（教师端）</div>
		<div class="my_info">欢迎您，<span class="myUserId"><%=teacherName %></span></div>
		<img src="images/logout.png" alt="点击退出" class="logout" value="<%=userId%>" teacherId="<%=teacherId%>">
		<div class="clear"></div>
	</div>
	<!-- 导航栏 -->
	<div class="nav">
		<li class="li_noitem"></li>
		<li class="li_item" style="background-color:rgb(54, 178, 220)" value="message">我的消息<span class="label label-danger my_message_num"></span></li>
		<li class="li_item" value="set_project">开设课程</li>
		<li class="li_item" value="add_achievement">添加成绩</li>
		<li class="li_item" value="put_message">发布通知</li>
	</div>
	<div class="container">
		
	</div>
	<div class="footer">
		<span class="footer_text">Copyright 2017 JAVA四人组. All Rights Reserved.</span>

	</div>
	
		<!-- 模态框（Modal） start-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">上课时间选择</h4>
		            </div>
		            <div class="modal-body">  <!--模态框主体内容-->
							<div class="freetime">
							<table>
								<tr>
									<th colspan="2"></th>
									<th>周一</th>
									<th>周二</th>
									<th>周三</th>
									<th>周四</th>
									<th>周五</th>
									<th>周六</th>
									<th>周日</th>
								</tr>
								<tr>
									<td rowspan="2">上
										<br>午</td>
									<td>07:50
										<br>~
										<br>09:25</td>
									<td>
										<input type="checkbox" name="freetime" value="1" id="1a">
										<label for="1a"></label>
									</td>
									<td>
										<input type="checkbox" name="freetime" value="6" id="2a">
										<label for="2a">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="11" id="3a">
										<label for="3a">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="16" id="4a">
										<label for="4a">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="21" id="5a">
										<label for="5a">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="26" id="6a">
										<label for="6a">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="31" id="7a">
										<label for="7a">
									</td>
								</tr>
								<tr>
									<td>9:45
										<br>~
										<br>12:10</td>
									<td>
										<input type="checkbox" name="freetime" value="2" id="1b">
										<label for="1b">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="7" id="2b">
										<label for="2b">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="12" id="3b">
										<label for="3b">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="17" id="4b">
										<label for="4b">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="22" id="5b">
										<label for="5b">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="27" id="6b">
										<label for="6b">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="32" id="7b">
										<label for="7b">
									</td>
								</tr>
								<tr>
									<td rowspan="2">下
										<br>午</td>
									<td>13:50
										<br>~
										<br>15:25</td>
									<td>
										<input type="checkbox" name="freetime" value="3" id="1c">
										<label for="1c">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="8" id="2c">
										<label for="2c">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="13" id="3c">
										<label for="3c">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="18" id="4c">
										<label for="4c">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="23" id="5c">
										<label for="5c">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="28" id="6c">
										<label for="6c">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="33" id="7c">
										<label for="7c">
									</td>
								</tr>
								
								<tr>
									<td>15:45
										<br>~
										<br>18:10</td>
									<td>
										<input type="checkbox" name="freetime" value="4" id="1e">
										<label for="1e">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="9" id="2e">
										<label for="2e">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="14" id="3e">
										<label for="3e">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="19" id="4e">
										<label for="4e">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="24" id="5e">
										<label for="5e">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="29" id="6e">
										<label for="6e">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="34" id="7e">
										<label for="7e">
									</td>
								</tr>
								<tr>
									<td rowspan="1">晚
										<br>上</td>
									<td>18:50
										<br>~
										<br>20:25</td>
									<td>
										<input type="checkbox" name="freetime" value="5" id="1d">
										<label for="1d">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="10" id="2d">
										<label for="2d">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="15" id="3d">
										<label for="3d">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="20" id="4d">
										<label for="4d">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="25" id="5d">
										<label for="5d">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="30" id="6d">
										<label for="6d">
									</td>
									<td>
										<input type="checkbox" name="freetime" value="35" id="7d">
										<label for="7d">
									</td>
								</tr>
							</table>
						</div>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary btn_project_time">提交</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div>
		<!-- 模态框（Modal）end -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/teacher.js"></script>
</body>
</html>