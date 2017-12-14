<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8");%>
   
<!--  DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<span class="err"> ${param.err }</span>
	<form action="loginValidate.jsp" method="post">
		<input type="number" name="userName" placeholder="请输入用户名">
		<input type="password" name="passWord" placeholder="请输入密码">
		<input type="submit" value="提交">
	</form>
</body>
</html-->


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/bootstrap.min.css">
    <link rel="stylesheet" href="style/login.css">
</head>
<body style="background: url(images/bg.jpg) no-repeat center 0">
	<div class="container" id="chuangkou">
	    <div class="row">
	        <div class="col-md-4 col-sm-3 col-xs-0">

	        </div>
	        <div class="col-md-4 col-sm-6  col-xs-12 dr">
	        	<div class="panel panel-default">
	        		<div class="panel-heading">
	        			<h2 class="panel-title"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>  登录</h2>
					
					</div>
					<div class="panel-body">
				       <form action="loginValidate.jsp" method="post">
				        	<div class="row">
								<div class="col-md-1 col-sm-1 col-xs-1"></div>
								<div class="col-md-10 col-sm-10 col-xs-10">
								   <span class="err"> ${param.err }</span>
								</div>
								<div class=" col-md-1 col-sm-1 col-xs-1"></div>


				        	</div>
					        <div class="row ">
						        <div class="col-md-1 col-sm-1 col-xs-1"></div>
								<div class="form-group col-md-10 col-sm-10 col-xs-10">
								    <input type="number" name="userName" class="form-control " required="required" oninput='if(value.length>10)value=value.slice(0,10)' placeholder="请输入用户名">
								</div>
								<div class=" col-md-1 col-sm-1 col-xs-1"></div>
							</div>
							<div class="row">
						 		<div class="col-md-1 col-sm-1 col-xs-1"></div>
						  		<div class="form-group col-md-10 col-sm-10 col-xs-10">
						   			 <input type="password" name="passWord" class="form-control "  required="required"  maxlength="12"  placeholder="请输入密码">
						 		</div>
						  		<div class="col-md-1 col-sm-1 col-xs-1"></div>
							</div>
							<div class="row"> 
							  	<div class="col-md-1 col-sm-2 col-xs-2"></div>
							  
							    <div class="col-md-6 col-xs-6 col-sm-6"></div>
							    <div class="col-md-4 col-xs-4 col-sm-4 rember_password">
								   <label>
							          <h5><a href="#">忘记密码？</a>	</h5>
							       </label>
							    </div>
							    <div class="col-md-1 col-sm-1 col-xs-1"></div>
							</div>
						    <div class="row"> 
							  	<div class="col-md-2 col-sm-2 col-xs-2"></div>
							    <div class="col-md-8 col-sm-8 col-xs-8">
									<button type="submit" class="btn btn-info dran" id="denglu" >登录</button>
								</div>
								<div class="col-md-2 col-sm-2 col-xs-2">
							</div>
							<div class="row"> 
							  	<div class="col-md-2 col-sm-2 col-xs-2"></div>
							    <div class="col-md-8 col-sm-8 col-xs-8 reserved">
									Copyright 2017 JAVA四人组. All Rights Reserved.
								</div>
								<div class="col-md-2 col-sm-2 col-xs-2">
							</div>
					</form>
						
				    </div>
				</div>

	        </div>
	        <div class="col-md-4 col-sm-3 col-xs-0">

	        </div>
		</div>
	</div>
	


	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
    	$(".rember_password").click(function () {
    		alert("请联系管理员qq 273647953！")
    	})
    </script>

</body>
</html>