//退出登录按钮
$(".logout").click(function(){
	var userId=$(this).attr("value");
	if(confirm("你确定要退出吗？")){ 
		var formData={
				"userId":userId,
				"stats":0
			}
			$.ajax({  //设置用户的在线状态
			    type        : 'post',
			    url         : 'setUserLoginStatsServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	 
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
			window.location.href="logout.jsp"
	} 
		
})





//邮箱验证函数
function checkEmail(str){
    var re = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/; 
    if (re.test(str)) {
      return 1;
    } else {
      return 0;
    }
  }
//点击添加管理员
$("#add1").click(function(){
	add1();
})

//添加管理员辅助函数
function add1(){
	$(".container_right").empty();
	$(".container_right").append("<!-- 添加管理员页面 --><div class='add1 right_item'><!-- 显示已经存在的管理员信息 --><div class='has_admin'><div class='has_admin_title'>管理员信息列表</div><div class='has_admin_table'></div></div><div class='add_admin'><div class='add_admin_title'>添加管理员 <small>(登录密码默认为123456）</small></div><form action='' metdod='post'><input type='number' class='form-control userId' required='required' oninput='if(value.length>10)value=value.slice(0,10)' name='userId' placeholder='请输入用户名'> <input type='text' class='form-control name' required='required' maxlength='5' name='name' placeholder='请输入管理员姓名'> <select name='sex' id='sex' class='form-control'><option value='0'>男</option><option value='1'>女</option></select> <input type='email' class='form-control email' required='required' name='email' placeholder='请输入邮箱'> <input type='button' class='btn btn-info btn_admin' value='提交'></form></div></div>");
	var allAdminInfo=[];
	$.ajax({  //请求获取已存在管理员信息
	    type        : 'post',
	    url         : 'getAllAdminServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	console.log(res.data+"-----");
	    	$(".has_admin_table").empty();
	    	$(".has_admin_table").append("<table class='has_admin_tables table table-hover'><tr><th>管理员id</th><th>用户名</th><th>姓名</th><th>性别</th><th>email</th></tr></table>")
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".has_admin_tables").append("<tr><td>"+res[i].adminId+"</td><td>"+res[i].userId+"</td><td>"+res[i].name+"</td><td>"+res[i].sex+"</td><td>"+res[i].email+"</td></tr>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在用户名
	    type        : 'post',
	    url         : 'getAllUserServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	for(var i=0;i<res.length;i++){
	    		allAdminInfo.push(res[i].userId);
	    	}
	   },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".btn_admin").click(function(){  //添加新的管理员
		var userId=$(".userId").val();
		var name=$(".name").val();
		var sex=$("#sex").find("option:selected").val();
		var email=$(".email").val();
		alert(userId)
		
		if(userId==""){
			alert("请填写用户名！");
		}else if(userId.length!=10){
			alert("用户名要为10位！");
		}else if(name==""){
		
			alert("请填写姓名！")
		}else if(checkEmail(email)==0){
			alert("邮箱格式不正确，请核对后重新输入！")
		}else if(email==""){
			alert("请输入邮箱！")
		}else if($.inArray(userId,allAdminInfo)!=-1){
			alert("用户名已经存在，请核对后再添加！")
		}else{
			var formData={
					"userId":userId, 
					"name":name,
					"sex":sex,
					"email":email
			}
//			alert(allAdminInfo);
			$.ajax({  //提交新添加的管理员信息
			    type        : 'post',
			    url         : 'addAdminServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add1();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			 $(".userId").val("");
			$(".name").val("");
			$("#sex").val("0");
			$(".email").val("");
		}
	})
}

//点击添加学院
$("#add2").click(function(){
	add2();
})

function add2(){
		$(".container_right").empty();
		$(".container_right").append("<!-- 添加学院 --><div class='add2 right_item'><div class='has_xy'><div class='title'>已有学院</div><div class='xy_table'></div></div><div class='add_xy'><div class='title'>添加学院</div><div class='add_form'><form action='' method='post'><input type='text' class='form-control xyName' name='xyNmae' placeholder='请输入学院名称'><textarea class='form-control xyInfo' name='xyInfo' placeholder='请输入50字内的学院介绍'></textarea><input type='button' class='btn btn-info btn_xy' value='提交'></form></div></div></div>");
	var allAdminInfo=[];
	$.ajax({  //请求获取已存在学院信息
	    type        : 'post',
	    url         : 'getAllXyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	console.log(res[0].xyName+"-----");
	    	$(".xy_table").empty();
	    	$(".xy_table").append("<table class='has_xy_tables table table-hover'><tr><th>学院编号</th><th>学院名称</th><th>学院介绍</th></tr></table>")
	        for(var i=0;i<res.length;i++){
	        	allAdminInfo.push(res[i].xyName);
	        	 $(".has_xy_tables").append("<tr><td>"+res[i].xyId+"</td><td>"+res[i].xyName+"</td><td>"+res[i].xyInfo+"</td></tr>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".btn_xy").click(function(){  //添加新的学院
		
		
		var xyName=$(".xyName").val();
		var xyInfo=$(".xyInfo").val();
		

		if(xyName==""){
			alert("请填写学院名！");
		}else if(xyInfo==""){
			alert("请填写学院介绍！")
		}else if(xyInfo.length>50){
			alert("学院介绍不能超过50字！")
		}else if($.inArray(xyName,allAdminInfo)!=-1){
			alert(xyName+"已经存在，请核对后再添加！")
		}else{
			var formData={
					
					"xyName":xyName,
					"xyInfo":xyInfo
			}
			$.ajax({  //提交新添加的学院信息
			    type        : 'post',
			    url         : 'addXyServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add2();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			$(".xyName").val("");
			$(".xyInfo").val("");
		}
	})
}

//点击添加学生
$("#add9").click(function(){
	add9();
})

function add9(){
		$(".container_right").empty();
		$(".container_right").append("<!-- 添加学生 --><div class='add9 right_item'><div class='add_student'><div class='title'>添加学生</div><div class='student_input'><select name='xyId' id='xyId' class='xyId form-control'><option value='0'>请选择学院</option></select><select name='zyId' id='zyId' class='zyId form-control'><option value='0'>请选择专业</option></select><select name='bjId' id='bjId' class='bjId form-control'><option value='0'>请选择班级</option></select><input class='form-control userId' type='number' placeholder='请输入10位学号'><input class='form-control name' type='number' placeholder='请输入姓名'><select class='form-control sex'><option value='3'>请选择性别</option><option value='0'>男</option><option value='1'>女</option></select><input class='form-control userId' type='email' placeholder='请输入邮箱'><input class='form-control idCard' type='text' placeholder='请输入18位身份证号码'><input class='form-control rxDate' type='text' placeholder='请输入入学年份（2017-12-01)'><input class='form-control zhengzhi' type='text' placeholder='请输入政治面貌'><input class='form-control jiguan' type='text' placeholder='请输入籍贯'><input class='form-control birthday' type='text' placeholder='请输入生日'><input type='button' class='btn btn-info student_btn' value='提交'></div></div>");
	var allStudentInfo=[];
	$.ajax({  //请求获取已存在学生信息
	    type        : 'post',
	    url         : 'getAllStudentInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	
	        for(var i=0;i<res.length;i++){
	        	allStudentInfo.push(res[i].userId);
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在学院信息
	    type        : 'post',
	    url         : 'getAllXyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".xyId").empty();
	    	$(".xyId").append("<option value='0'>请选择学院</option>")
	    	
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".xyId").append("<option value='"+res[i].xyId+"'>"+res[i].xyName+"</option>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$('.xyId').change(function(){  //select二级联动 select改变触发
		var xyId=$(this).children('option:selected').val();//这就是selected的值 
		if(xyId==0){  
			$(".zyId").empty();
	    	$(".zyId").append("<option value='0'>请选择专业</option>");
	    	$(".bjId").empty();
	    	$(".bjId").append("<option value='0'>请选择班级</option>");
		}else{
			var formData={
				"xyId":xyId	
			}
			$.ajax({  //由xyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getZyInfoByXyIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.length==0){
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>请选择专业</option>")
			    	}else{
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>请选择专业</option>")
				        for(var i=0;i<res.length;i++){
				        	
				        	 $(".zyId").append("<option value='"+res[i].zyId+"'>"+res[i].zyName+"</option>")
				        }
			    	}
			    	
			       
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		}
	})
	$('.zyId').change(function(){  //select三级联动 select改变触发
		var zyId=$(this).children('option:selected').val();//这就是selected的值 
		if(zyId==0){  
			$(".bjId").empty();
	    	$(".bjId").append("<option value='0'>所有班级</option>");
		}else{
			var formData={
				"zyId":zyId	
			}
			$.ajax({  //由zyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getBjInfoByZyIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.length==0){
			    		$(".bjId").empty();
				    	$(".bjId").append("<option value='0'>请选择班级</option>")
			    	}else{
			    		$(".bjId").empty();
				    	$(".bjId").append("<option value='0'>请选择班级</option>")
				        for(var i=0;i<res.length;i++){
				        	
				        	 $(".bjId").append("<option value='"+res[i].bjId+"'>"+res[i].bjName+"</option>")
				        }
			    	}
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		}
	})
	
	$(".student_btn").click(function(){  //添加新的学生
		var userId=$(".userId").val();
		var name=$(".name").val();
		var sex=$(".sex").find("option:selected").val();
		var email=$(".email").val();
		var idCard=$(".idCard").val();
		var rxDate=$(".rxDate").val();
		var zhengzhi=$(".zhengzhi").val();
		var bjId=$(".bjId").find("option:selected").val();
		var jiguan=$(".jiguan").val();
		var birthday=$(".birthday").val();
		alert(userId+"--"+name+"--"+sex+"--"+email+"---"+idCard+"---"+rxDate+"----");
		if(xyName==""){
			alert("请填写学院名！");
		}else if(xyInfo==""){
			alert("请填写学院介绍！")
		}else if(xyInfo.length>50){
			alert("学院介绍不能超过50字！")
		}else if($.inArray(xyName,allAdminInfo)!=-1){
			alert(xyName+"已经存在，请核对后再添加！")
		}else{
			var formData={
					"userId":'6103115074',  //用户名（学号10位）  不可以重复
					"name":'zsp',		//姓名
					"sex":'0',             //性别  0：男 1：女
					"email":'273647953@qq.com', //邮箱：注意检查格式
					"idCard":'360502xxxxxxxxx',		//身份证号
					"rxDate":'2015-09-01',       //入学年份 xxxx-xx-xx
					"zhengzhi":'团员',        //政治面貌
					"bjId":'1',		//班级的Id  见下表
					"jiguan":'江西新余',		//籍贯
					 "birthday":'1996-01-20'		//生日 xxxx-xx-xx
			}
			$.ajax({  //提交新添加的学院信息
			    type        : 'post',
			    url         : 'addXyServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add2();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			$(".xyName").val("");
			$(".xyInfo").val("");
		}
	})
}

//点击添加专业
$("#add3").click(function(){
	add3();
})

function add3(){
	$(".container_right").empty();
	$(".container_right").append("<!-- 添加专业 --><div class='add3 right_item'><div class='has_zy'><div class='title'>已有专业</div><div class='zy_table'></div></div><div class='add_zy'><div class='title'>添加专业</div><div class='add_form'><form action='' method='post'><select name='xyId' id='has_xy_option' class='form-control has_xy_option'></select> <input type='text' name='zyName' class='form-control zyName' placeholder='请输入专业名称' /><textarea type='text' name='zyInfo' class='form-control zyInfo' placeholder='请输入50字以内的专业介绍'></textarea><input type='button' class='btn btn-info btn_zy' value='提交'></form></div></div></div>");
	var allAdminInfo=[]; //保存已存在专业名称
	$.ajax({  //请求获取已存在学院信息
	    type        : 'post',
	    url         : 'getAllXyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	console.log(res[0].xyName+"-----");
	    	$(".has_xy_option").empty();
	    	$(".has_xy_option").append("<option value='0'>请选择学院</option>")
	        for(var i=0;i<res.length;i++){
	        	allAdminInfo.push(res[i].xyName);
	        	 $(".has_xy_option").append("<option value='"+res[i].xyId+"'>"+res[i].xyName+"</option>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在专业信息
	    type        : 'post',
	    url         : 'getAllZyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	
	    	$(".zy_table").empty();
	    	$(".zy_table").append("<table class='has_zy_tables table table-hover'><tr><th>专业编号</th><th>所属学院</th><th>专业名称</th><th>专业介绍</th></tr></table>")
	        for(var i=0;i<res.length;i++){
	        	allAdminInfo.push(res[i].zyName);
	        	 $(".has_zy_tables").append("<tr><td>"+res[i].zyId+"</td><td>"+res[i].xyName+"</td><td>"+res[i].zyName+"</td><td>"+res[i].zyInfo+"</td></tr>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".btn_zy").click(function(){  //添加新的专业
		
		var xyId=$("#has_xy_option").find("option:selected").val();
		var zyName=$(".zyName").val();
		var zyInfo=$(".zyInfo").val();
		

		if(zyName==""){
			alert("请填写专业名！");
		}else if(zyInfo==""){
			alert("请填写专业介绍！")
		}else if(zyInfo.length>50){
			alert("专业介绍不能超过50字！")
		}else if($.inArray(zyName,allAdminInfo)!=-1){
			alert(zyName+"专业已经存在，请核对后再添加！")
		}else{
			var formData={
					"xyId":xyId,
					"zyName":zyName,
					"zyInfo":zyInfo
			}
			$.ajax({  //提交新添加的管理员信息
			    type        : 'post',
			    url         : 'addZyServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add3();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			$(".zyName").val("");
			$(".zyInfo").val("");
			$("#has_xy_option").val("0");
		}
	})
}

//点击添加班级
$("#add4").click(function(){
	add4();
	
})

//添加班级函数
function add4(){
	$(".container_right").empty();
	$(".container_right").append("<div class='add4 right_item'><div class='has_bj'><div class='title'>已有班级</div><div class='bj_table'></div></div><div class='add_bj'><div class='title'>添加班级</div><div class='add_form'><select name='xyId' id='' class='form-control xyId'></select> <select name='zyId' id='' class='form-control zyId'></select><input type='text' placeholder='请输入班级名称（如152）' class='form-control bjName' name='bjName'> <input type='submit' class='btn btn-info btn_bj' value='提交'></div></div></div>");
	var allBjInfo=[];
	$(".bj_table").empty();
	$(".bj_table").append("<table class='bj_tables table table-hover'><tr><th>班级编号</th><th>班级名称</td><th>所属学院</th><th>所属专业</td></tr></table>")
	$.ajax({  //请求获取已存在班级信息
	    type        : 'post',
	    url         : 'getAllBjInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	console.log(res[0].xyName+"-----");
	    	$(".bj_table").empty();
	    	$(".bj_table").append("<table class='bj_tables table table-hover'><tr><th>班级编号</th><th>班级名称</td><th>所属学院</th><th>所属专业</td></tr></table>")
	        for(var i=0;i<res.length;i++){
	        	allBjInfo.push(res[i].zyName+"-"+res[i].bjName);
	        	 $(".bj_tables").append("<tr><td>"+res[i].bjId+"</td><td>"+res[i].bjName+"</td><td>"+res[i].xyName+"</td><td>"+res[i].zyName+"</td></tr>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在学院信息
	    type        : 'post',
	    url         : 'getAllXyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	
	    	$(".xyId").empty();
	    	$(".xyId").append("<option value='0'>请选择学院</option>")
	    	$(".zyId").append("<option value='0'>请选择专业</option>")
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".xyId").append("<option value='"+res[i].xyId+"'>"+res[i].xyName+"</option>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$('.xyId').change(function(){  //select二级联动 select改变触发
		var xyId=$(this).children('option:selected').val();//这就是selected的值 
		if(xyId==0){  
			$(".zyId").empty();
	    	$(".zyId").append("<option value='0'>请选择专业</option>")
		}else{
			var formData={
				"xyId":xyId	
			}
			$.ajax({  //由xyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getZyInfoByXyIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.length==0){
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>该学院还未添加专业，快去添加吧！</option>")
			    	}else{
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>请选择专业</option>")
				        for(var i=0;i<res.length;i++){
				        	
				        	 $(".zyId").append("<option value='"+res[i].zyId+"'>"+res[i].zyName+"</option>")
				        }
			    	}
			    	
			       
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		}
	})

	$(".btn_bj").click(function(){
		
		var xyId=$(".xyId").children('option:selected').val();//这就是selected的值 
		var zyId=$(".zyId").children('option:selected').val();
		var zyName=$(".zyId").children('option:selected').text();
		
		var bjName=$(".bjName").val();
		var bj=zyName+"-"+bjName;
//		alert(xyId+"----"+zyId+"----"+bjName);
		if(xyId==0){
			alert("请选择学院！");
			
		}else if(zyId==0){
			alert("请选择专业！");
		}else if(bjName==""){
			alert("请输入班级名！")
		}else if($.inArray(bj,allBjInfo)!=-1){
			alert(bjName+"班级已经存在，请核对后再添加！");
		}else{
			
			var formData={
				"xyId":xyId,
				"zyId":zyId,
				"bjName":bjName
			}
			$.ajax({  //提交新添加的班级信息
			    type        : 'post',
			    url         : 'addBjServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			    	 add4();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			 $(".xyId").val("0");
			$(".zyId").val("0");
			$(".bjName").val("");
		}
		
	})
	
}

//点击添加教师
$("#add5").click(function(){
	add5();
})

//添加教师
function add5(){
	$(".container_right").empty();
	$(".container_right").append("<div class='add5 right_item'><div class='has_teacher'><div class='title'>已有教师信息</div><div class='teacher_table'></div></div><div class='add_teacher'><div class='title'>添加教师<small>（密码默认为123456）</small></div><div class='add_form'><form action=''><select name='xyId' id='' class='form-control xyId has_xy_option'></select> <input type='text' name='name' class='teacherName form-control' placeholder='请输入姓名'> <input type='number' name='userId' class='teacherUserId form-control' placeholder='请输入用户名' oninput='if(value.length>10)value=value.slice(0,10)'> <select name='sex' id='' class='form-control teacherSex'><option value='0'>男</option><option value='1'>女</option></select> <input type='email' placeholder='请输入email' class='form-control teacherEmail' name='email'> <input type='button' class='btn btn-info btn_teacher' value='提交'></form></div></div></div>");
	var allAdminInfo=[]; //保存已存在教师
	$(".teacher_table").empty();
	$(".teacher_table").append("<table class='table table-hover'><tr><th>教师编号</th><th>教师姓名</td><th>用户名</th><th>性别</td><th>所在学院</td><th>email</td></tr></table></div>");
	$.ajax({  //请求获取已存在教师信息
	    type        : 'post',
	    url         : 'getAllTeacherInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".teacher_table").empty();
	    	$(".teacher_table").append("<table class='table table-hover has_teacher_table'><tr><th>教师编号</th><th>教师姓名</td><th>用户名</th><th>性别</td><th>所在学院</td><th>email</td></tr></table></div>");
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".has_teacher_table").append("<tr><td>"+res[i].teacherId+"</td><td>"+res[i].name+"</td><td>"+res[i].userId+"</td><td>"+res[i].sex+"</td><td>"+res[i].xyName+"</td><td>"+res[i].email+"</td></tr>")
	        }

	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在学院信息
	    type        : 'post',
	    url         : 'getAllXyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
//	    	console.log(res[0].xyName+"-----");
	    	$(".has_xy_option").empty();
	    	$(".has_xy_option").append("<option value='0' selected>请选择学院</option>")
	        for(var i=0;i<res.length;i++){
	        
	        	 $(".has_xy_option").append("<option value='"+res[i].xyId+"'>"+res[i].xyName+"</option>")
	        }
	    	
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在用户名
	    type        : 'post',
	    url         : 'getAllUserServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	for(var i=0;i<res.length;i++){
	    		allAdminInfo.push(res[i].userId);
	    	}
	   },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".btn_teacher").click(function(){  //添加新的专业
		
		var xyId=$(".xyId").find("option:selected").val();
		var userId=$(".teacherUserId").val();
		var name=$(".teacherName").val();
		var sex=$(".teacherSex").find("option:selected").val();
		var email=$(".teacherEmail").val();
		
		
		if(xyId==0){
			alert("请选择学院！");
		}else if(name==""){
			alert("请填写教师姓名！")
		}else if(userId==""){
			alert("请填写用户名！")
		}else if(email==""){
			alert("请填写邮箱！")
		}else if(checkEmail(email)==0){
			alert("邮箱格式不正确！");
		}else if($.inArray(userId,allAdminInfo)!=-1){
			alert("用户名"+userId+"已经存在，请核对后再添加！")
		}else{
			var formData={
					"xyId":xyId,
					"userId":userId,
					"name":name,
					"sex":sex,
					"email":email
			}
			$.ajax({  //提交新添加的管理员信息
			    type        : 'post',
			    url         : 'addTeacherServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add5();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			$(".xyId").find("option:selected").val("0");
			$(".teacherUserId").val("");
			$(".teacherName").val("");
			$(".teacherSex").find("option:selected").val("0");
			$(".teachEmail").val("");
			
		}
	})
}

//点击添加课程
$("#add6").click(function(){
	add6();
})

//添加课程
function add6(){
	$(".container_right").empty();
	$(".container_right").append("<!-- 添加课程 --><div class='add6 right_item'><div class='has_project'><div class='title'>已有课程信息</div><div class='project_table'></div></div><div class='add_project'><div class='title'>添加课程</div><div class='add_form'><form action=''><input type='text' name='projectName' class='projectName form-control' placeholder='请输入课程名'> <input type='text' placeholder='请输入25字以内课程介绍' class='form-control projectInfo' name='projectInfo'> <input type='button' class='btn btn-info btn_project' value='提交'></form></div></div></div>");
	var allprInfo=[]; //保存已存在课程
	$(".project_table").empty();
	$(".project_table").append("<table class='table table-hover project_tables'><tr><th>课程编号</th><th>课程名称</td><th>课程介绍</th></tr></table>");
	$.ajax({  //请求获取已存在课程
	    type        : 'post',
	    url         : 'getAllProjectServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	//console.log(res.data+"-----");
	    	$(".project_table").empty();
	    	$(".project_table").append("<table class='table table-hover project_tables'><tr><th>课程编号</th><th>课程名称</td><th>课程介绍</th></tr></table>")
	        for(var i=0;i<res.length;i++){
	        	allprInfo.push(res[i].prName)
	        	 $(".project_tables").append("<tr><td>"+res[i].prId+"</td><td>"+res[i].prName+"</td><td>"+res[i].prInfo+"</td></tr>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".btn_project").click(function(){
		var prName=$(".projectName").val();
		var prInfo=$(".projectInfo").val();
		if(prName==""){
			alert("请输入课程名！")
		}else if(prInfo==""){
			alert("请输入课程介绍！")
		}else if($.inArray(prName,allprInfo)!=-1){
			alert(prName+"已存在，请核对后再添加！")
		}else if(prName.length>25){
			alert("课程介绍需在25字内！")
		}else{
			var formData={
					"prName":prName,
					"prInfo":prInfo
			}
			$.ajax({  //提交新添加的管理员信息
			    type        : 'post',
			    url         : 'addProjectServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add6();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			$(".projectName").val("");
			$(".projectInfo").val("");
			
		
			
		}
		
		
			
	})
}


//点击添加学期
$("#add7").click(function(){
	add7();
})
//点击添加学期辅助函数
function add7(){
	$(".container_right").empty();
	$(".container_right").append("<!-- 添加学期 --><div class='add7 right_item'><div class='has_semester'><div class='title'>已有学期信息</div><div class='semester_table'></div></div><div class='add_semester'><div class='title'>添加学期</div><div class='add_form'><form action=''><input type='text' name='semesterName' class='semesterName form-control' placeholder='请输入学期名称(2017-2018-2)'><input type='button' class='btn btn-info btn_semester' value='提交'></form></div></div></div>");
	var allSemesterInfo=[]; //保存已存在学期
	$(".semester_table").empty();
	$(".semester_table").append("<table class='table table-hover semester_tables' ><tr><th>学期编号</th><th>学期名称</td></tr></table>");
	$.ajax({  //请求获取已存在学期信息
	    type        : 'post',
	    url         : 'getAllSemesterInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".semester_table").empty();
	    	$(".semester_table").append("<table class='table table-hover semester_tables' ><tr><th>学期编号</th><th>学期名称</td></tr></table>");
	        for(var i=0;i<res.length;i++){
	        	allSemesterInfo.push(res[i].semesterName);
	        	 $(".semester_tables").append("<tr><td>"+res[i].semesterId+"</td><td>"+res[i].semesterName+"</td></tr>")
	        }

	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".btn_semester").click(function(){
		var semesterName=$(".semesterName").val();
		if(semesterName==""){
			alert("请输入学期名称！")
		}else if(semesterName.length!=11){
			alert("学期名称格式不正确！")
		}else if($.inArray(semesterName,allSemesterInfo)!=-1){
			alert(semesterName+"已经存在！")
		}else{
			var formData={
					"semesterName":semesterName
			}
			$.ajax({  //提交新添加的学期信息
			    type        : 'post',
			    url         : 'addSemesterServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add7();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			$(".semesterName").val("");
			
		}
	})
}

//点击添加考试类型
$("#add8").click(function(){
	add8();
})
//点击添加考试类型辅助函数
function add8(){
	$(".container_right").empty();
	$(".container_right").append("<!-- 添加考试类型 --><div class='add8 right_item'><div class='has_examType'><div class='title'>已有考试类型</div><div class='examType_table'></div></div><div class='add_examType'><div class='title'>添加考试类型</div><div class='add_form'><form action=''><input type='text' name='examTypeName' class='examTypeName form-control' placeholder='请输入考试描述'><input type='text' class='btn btn-info btn_examType' value='提交'></form></div></div></div>");
	var allexamTypeInfo=[]; //保存已存在考试类型
	$(".examType_table").empty();
	$(".examType_table").append("<table class='table table-hover examType_tables'><tr><th>考试类型编号</th><th>考试类型描述</td></tr></table>");
	$.ajax({  //请求获取已存在考试类型
	    type        : 'post',
	    url         : 'getAllExamTypeInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".examType_table").empty();
	    	$(".examType_table").append("<table class='table table-hover examType_tables'><tr><th>考试类型编号</th><th>考试类型描述</th></tr></table>");
	        for(var i=0;i<res.length;i++){
	        	allexamTypeInfo.push(res[i].semesterName);
	        	 $(".examType_tables").append("<tr><td>"+res[i].examTypeId+"</td><td>"+res[i].examTypeName+"</td></tr>")
	        }

	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".btn_examType").click(function(){
		var examTypeName=$(".examTypeName").val();
		if(examTypeName==""){
			alert("请输入考试描述！")
		}else if(examTypeName>10){
			alert("考试描述不能超过10个字！")
		}else if($.inArray(examTypeName,allexamTypeInfo)!=-1){
			alert(examType+"已经存在！")
		}else{
			var formData={
					"examTypeName":examTypeName
			}
			$.ajax({  //提交新添加的学期信息
			    type        : 'post',
			    url         : 'addExamTypeServlet',
			    dataType    : 'json',
			    data: JSON.stringify(formData),
			    success:function(res){
			       add8();
			       alert(res.data);
			       
			    },
			    error:function(res){
			        console.log(errMsg);alert(res.err);
			    }
			})
			$(".examTypeName").val("");
	
			
		}
	})
}
//查看教师
$("#see2").click(function(){
	see2();
})
//查看教师辅助函数
function see2(){
	$(".container_right").empty();
	$(".container_right").append("<div class='see2 right_item'><div class='has_teacher'><div class='title'>已有教师信息</div><div class='teacher_table'></div></div></div>");
	
	$(".teacher_table").empty();
	$(".teacher_table").append("<table class='table table-hover'><tr><th>教师编号</th><th>教师姓名</td><th>用户名</th><th>性别</td><th>所在学院</td><th>email</th><th>操作</th></tr></table></div>");
	$.ajax({  //请求获取已存在教师信息
	    type        : 'post',
	    url         : 'getAllTeacherInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".teacher_table").empty();
	    	$(".teacher_table").append("<table class='table table-hover has_teacher_table'><tr><th>教师编号</th><th>教师姓名</td><th>用户名</th><th>性别</td><td>所在学院</td><td>email</td><td>操作</td></tr></table></div>");
	        for(var i=0;i<res.length;i++){
	        	$(".has_teacher_table").append("<tr><td>"+res[i].teacherId+"</td><td>"+res[i].name+"</td><td>"+res[i].userId+"</td><td>"+res[i].sex+"</td><td>"+res[i].xyName+"</td><td>"+res[i].email+"</td><td class='delete'><button value='"+res[i].userId+"' class='btn btn-danger'>删除</button></td></tr>")
	        }
	        $(".btn-danger").click(function(){
	        	  deleteTeacher($(this));
	        })
	      

	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
}
function deleteTeacher(a){
	var userId=a.attr("value");
	
	if(confirm("你确定要删除吗？")){ 
		var formData={
				"userId":userId
			}
			$.ajax({  //设置用户的在线状态
			    type        : 'post',
			    url         : 'deleteTeacherServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	alert(res.data); see2();
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
			
	} 
}
//查看管理员
$("#see3").click(function(){
	see3();
})
//查看管理员辅助函数
function see3(){
	
	
	$(".container_right").empty();
	$(".container_right").append("<div class='see3 right_item'><div class='has_admin'><div class='title'>已有管理员信息</div><div class='admin_table'></div></div></div>");
	
	$(".admin_table").empty();
	$(".admin_table").append("<table class='has_admin_tables table table-hover'><tr><th>管理员id</th><th>用户名</th><th>姓名</th><th>性别</th><th>email</th><th>操作</th></tr></table>");
	$.ajax({  //请求获取已存在管理员信息
	    type        : 'post',
	    url         : 'getAllAdminServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	console.log(res.data+"-----");
	    	$(".admin_table").empty();
	    	$(".admin_table").append("<table class='has_admin_tables table table-hover'><tr><th>管理员id</th><th>用户名</th><th>姓名</th><th>性别</th><th>email</th><th>操作</th></tr></table>")
	        for(var i=0;i<res.length;i++){
	        	 $(".has_admin_tables").append("<tr><td>"+res[i].adminId+"</td><td>"+res[i].userId+"</td><td>"+res[i].name+"</td><td>"+res[i].sex+"</td><td>"+res[i].email+"</td><td class='delete'><button value='"+res[i].userId+"' class='btn btn-danger'>删除</button></td></tr>")
	        }
	    	 $(".btn-danger").click(function(){
	        	  deleteAdmin($(this));
	        })
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
}
//删除管理员
function deleteAdmin(a){
	var userId=a.attr("value");
	var adminId=$(".logout").attr("value");
	if(adminId!='0000000000'){
		alert("你没有权限执行此操作！")
	}else if(userId=='0000000000'){
		alert("不能删除自己！")
	}else{
		if(confirm("你确定要删除吗？")){ 
			var formData={
					"userId":userId
				}
				$.ajax({  //设置用户的在线状态
				    type        : 'post',
				    url         : 'deleteAdminServlet',
				    dataType    : 'json',
				    data:JSON.stringify(formData),
				    contentType:'application/json;charset=UTF-8',
				    success     : function(res){
				    	alert(res.data); see3();
				    },
				    error:function(errMsg){
				        console.log(errMsg);
				    }
				})
				
		} 
	}
	
}

//查看考试类型
$("#see5").click(function(){
	see5();
})
//查看考试类型辅助函数
function see5(){
	$(".container_right").empty();
	$(".container_right").append("<!-- 添加考试类型 --><div class='see5 right_item'><div class='has_examType'><div class='title'>已有考试类型</div><div class='examType_table'></div></div>");
	
	$(".examType_table").empty();
	$(".examType_table").append("<table class='table table-hover examType_tables'><tr><th>考试类型编号</th><th>考试类型描述</td><th>操作</th></tr></table>");
	$.ajax({  //请求获取已存在考试类型
	    type        : 'post',
	    url         : 'getAllExamTypeInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".examType_table").empty();
	    	$(".examType_table").append("<table class='table table-hover examType_tables'><tr><th>考试类型编号</th><th>考试类型描述</th><th>操作</th></tr></table>");
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".examType_tables").append("<tr><td>"+res[i].examTypeId+"</td><td>"+res[i].examTypeName+"</td><td class='delete'><button value='"+res[i].examTypeId+"' class='btn btn-danger'>删除</button></td></tr>")
	        }
	        $(".btn-danger").click(function(){
	        	  deleteExamType($(this));
	        })

	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	

}
//删除考试类型
function deleteExamType(a){
	var examTypeId=a.attr("value");

		if(confirm("你确定要删除吗？")){ 
			var formData={
					"examTypeId":examTypeId
				}
				$.ajax({  
				    type        : 'post',
				    url         : 'deleteExamTypeServlet',
				    dataType    : 'json',
				    data:JSON.stringify(formData),
				    contentType:'application/json;charset=UTF-8',
				    success     : function(res){
				    	alert(res.data); see5();
				    },
				    error:function(errMsg){
				        console.log(errMsg);
				    }
				})
				
	} 
	
	
}

//查看学期
$("#see6").click(function(){
	see6();
})
//查看学期辅助函数
function see6(){
	$(".container_right").empty();
	$(".container_right").append("<!-- 添加学期 --><div class='see6 right_item'><div class='has_semester'><div class='title'>已有学期信息</div><div class='semester_table'></div></div>");
	
	$(".semester_table").empty();
	$(".semester_table").append("<table class='table table-hover semester_tables' ><tr><th>学期编号</th><th>学期名称</th><th>操作</th></tr></table>");
	$.ajax({  //请求获取已存在学期信息
	    type        : 'post',
	    url         : 'getAllSemesterInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".semester_table").empty();
	    	$(".semester_table").append("<table class='table table-hover semester_tables' ><tr><th>学期编号</th><th>学期名称</th><th>操作</th></tr></table>");
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".semester_tables").append("<tr><td>"+res[i].semesterId+"</td><td>"+res[i].semesterName+"</td><td class='delete'><button value='"+res[i].semesterId+"' class='btn btn-danger'>删除</button></td></tr>")
	        }
	        $(".btn-danger").click(function(){
	        	  deleteSemester($(this));
	        })
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	

}
//删除考试类型
function deleteSemester(a){
	var semesterId=a.attr("value");

		if(confirm("你确定要删除吗？")){ 
			var formData={
					"semesterId":semesterId
				}
				$.ajax({  
				    type        : 'post',
				    url         : 'deleteSemesterServlet',
				    dataType    : 'json',
				    data:JSON.stringify(formData),
				    contentType:'application/json;charset=UTF-8',
				    success     : function(res){
				    	alert(res.data); see6();
				    },
				    error:function(errMsg){
				        console.log(errMsg);
				    }
				})
				
	} 
	
	
}

/*查看学生*/
$("#see4").click(function(){
	see4();
})
/*查看学生辅助函数*/
function see4(){
	$(".container_right").empty();
	$(".container_right").append("	<!-- 查看学生 --><div class='see4 right_item'><div class='see_student'>	<div class='see_student_title'>学生信息列表</div><div class='student_input'><select name='xyId' id='xyId' class='xyId form-control'><option value='0'>所有学院</option></select><select name='zyId' id='zyId' class='zyId form-control'><option value='0'>所有专业</option></select><select name='bjId' id='bjId' class='bjId form-control'><option value='0'>所有班级</option></select><input type='button' class='btn btn-info seeStudent' value='查询'><div class='clear'></div></div><div class='clear'></div>	<div class='see_student_table'></div></div></div>");
	
	$(".see_student_table").empty();
	$(".see_student_table").append("<table class='table table-hover see_student_tables'><tr><th>学生编号</th><th>学号（用户名）</th><th>姓名</th><th>性别</th><th>学院</th><th>专业</th><th>班级</th><th>籍贯</th><th>入学年份</th><th>政治面貌</th><th>身份证号</th><th>操作</th></tr></table>");
	var formData2={
			"xyId":'0',
			"zyId":'0',
			"bjId":'0'
		}
	$.ajax({  //由zyId请求获取已存在专业信息
	    type        : 'post',
	    url         : 'getStudentInfoByBjIdServlet',
	    dataType    : 'json',
	    data:JSON.stringify(formData2),
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".see_student_table").empty();
			$(".see_student_table").append("<table class='table table-hover see_student_tables'><tr><th>学生编号</th><th>学号（用户名）</th><th>姓名</th><th>性别</th><th>学院</th><th>专业</th><th>班级</th><th>籍贯</th><th>入学年份</th><th>政治面貌</th><th>身份证号</th><th>操作</th></tr></table>");
	    	console.log(res);
	    	for(var i=0;i<res.length;i++){
	    		$(".see_student_tables").append("<tr><td>"+res[i].studentId+"</td><td>"+res[i].userId+"</td><td>"+res[i].name+"</td><td>"+res[i].sex+"</td><td>"+res[i].xyName+"</td><td>"+res[i].zyName+"</td><td>"+res[i].bjName+"</td><td>"+res[i].jiguan+"</td><td>"+res[i].rxdate+"</td><td>"+res[i].zhengzhi+"</td><td>"+res[i].idCard+"</td><td class='delete'><button value='"+res[i].userId+"' class='btn btn-danger'>删除</button></td></tr>");
	    	}
	    	$(".btn-danger").click(function(){
	    		deleteStudent($(this));
	    	})
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在学院信息
	    type        : 'post',
	    url         : 'getAllXyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".xyId").empty();
	    	$(".xyId").append("<option value='0'>所有学院</option>")
	    	
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".xyId").append("<option value='"+res[i].xyId+"'>"+res[i].xyName+"</option>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$('.xyId').change(function(){  //select二级联动 select改变触发
		var xyId=$(this).children('option:selected').val();//这就是selected的值 
		if(xyId==0){  
			$(".zyId").empty();
	    	$(".zyId").append("<option value='0'>所有专业</option>");
	    	$(".bjId").empty();
	    	$(".bjId").append("<option value='0'>所有班级</option>");
		}else{
			var formData={
				"xyId":xyId	
			}
			$.ajax({  //由xyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getZyInfoByXyIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.length==0){
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>所有专业</option>")
			    	}else{
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>所有专业</option>")
				        for(var i=0;i<res.length;i++){
				        	
				        	 $(".zyId").append("<option value='"+res[i].zyId+"'>"+res[i].zyName+"</option>")
				        }
			    	}
			    	
			       
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		}
	})
	$('.zyId').change(function(){  //select三级联动 select改变触发
		var zyId=$(this).children('option:selected').val();//这就是selected的值 
		if(zyId==0){  
			$(".bjId").empty();
	    	$(".bjId").append("<option value='0'>所有班级</option>");
		}else{
			var formData={
				"zyId":zyId	
			}
			$.ajax({  //由zyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getBjInfoByZyIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.length==0){
			    		$(".bjId").empty();
				    	$(".bjId").append("<option value='0'>所有班级</option>")
			    	}else{
			    		$(".bjId").empty();
				    	$(".bjId").append("<option value='0'>所有班级</option>")
				        for(var i=0;i<res.length;i++){
				        	
				        	 $(".bjId").append("<option value='"+res[i].bjId+"'>"+res[i].bjName+"</option>")
				        }
			    	}
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		}
	})
	
	$(".seeStudent").click(function(){  //点击查询按钮
		var xyId=$(".xyId").children('option:selected').val();
		var zyId=$(".zyId").children('option:selected').val();
		var bjId=$(".bjId").children('option:selected').val();
		var formData={
			"xyId":xyId,
			"zyId":zyId,
			"bjId":bjId
		}
		$.ajax({  //由zyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getStudentInfoByBjIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	$(".see_student_table").empty();
					$(".see_student_table").append("<table class='table table-hover see_student_tables'><tr><th>学生编号</th><th>学号（用户名）</th><th>姓名</th><th>性别</th><th>学院</th><th>专业</th><th>班级</th><th>籍贯</th><th>入学年份</th><th>政治面貌</th><th>身份证号</th><th>操作</th></tr></table>");
//			    	console.log(res);
			    	for(var i=0;i<res.length;i++){
			    		$(".see_student_tables").append("<tr><td>"+res[i].studentId+"</td><td>"+res[i].userId+"</td><td>"+res[i].name+"</td><td>"+res[i].sex+"</td><td>"+res[i].xyName+"</td><td>"+res[i].zyName+"</td><td>"+res[i].bjName+"</td><td>"+res[i].jiguan+"</td><td>"+res[i].rxdate+"</td><td>"+res[i].zhengzhi+"</td><td>"+res[i].idCard+"</td><td class='delete'><button value='"+res[i].userId+"' class='btn btn-danger'>删除</button></td></tr>");
			    	}
			    	$(".btn-danger").click(function(){
			    		deleteStudent($(this));
			    	})
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		
	})
	
}


//删除学生
function deleteStudent(a){
	var userId=a.attr("value");
	
		if(confirm("你确定要删除吗？")){ 
			var formData={
					"userId": userId
				}
				$.ajax({  
				    type        : 'post',
				    url         : 'deleteStudentServlet',
				    dataType    : 'json',
				    data:JSON.stringify(formData),
				    contentType:'application/json;charset=UTF-8',
				    success     : function(res){
				    	alert(res.data); see4();
				    },
				    error:function(errMsg){
				        console.log(errMsg);
				    }
				})
				
	} 
	
	
}
/*查看学院-专业-班级*/
$("#see1").click(function(){
	see1();
})
/*查看班级辅助函数*/
function see1(){
	$(".container_right").empty();
	$(".container_right").append("	<!-- 查看班级 --><div class='see1 right_item'><div class='see_student'>	<div class='see_student_title'>班级信息列表</div><div class='student_input'><select name='xyId' id='xyId' class='xyId form-control'><option value='0'>所有学院</option></select><select name='zyId' id='zyId' class='zyId form-control'><option value='0'>所有专业</option></select><select name='bjId' id='bjId' class='bjId form-control'><option value='0'>所有班级</option></select><input type='button' class='btn btn-info seeStudent' value='查询'><div class='clear'></div></div><div class='clear'></div>	<div class='see_student_table'></div></div></div>");
	
	$(".see_student_table").empty();
	$(".see_student_table").append("<table class='table table-hover see_student_tables'><tr><th>学院</th><th>专业</th><th>班级</th><th>操作</th></tr></table>");
	
	var xyId=$(".xyId").children('option:selected').val();
	var zyId=$(".zyId").children('option:selected').val();
	var bjId=$(".bjId").children('option:selected').val();
	var formData2={
		"xyId":'0',
		"zyId":'0',
		"bjId":'0'
	}
	$.ajax({  
		    type        : 'post',
		    url         : 'getBjInfoByBjIdServlet',
		    dataType    : 'json',
		    data:JSON.stringify(formData2),
		    contentType:'application/json;charset=UTF-8',
		    success     : function(res){
		    	$(".see_student_table").empty();
				$(".see_student_table").append("<table class='table table-hover see_student_tables'><tr><th>学院</th><th>专业</th><th>班级</th></tr></table>");//<th>操作</th>
		    	
		    	for(var i=0;i<res.length;i++){
		    		$(".see_student_tables").append("<tr><td>"+res[i].xyName+"</td><td>"+res[i].zyName+"</td><td>"+res[i].bjName+"</td></tr>");//<td class='delete'><button value='"+res[i].bjId+"' class='btn btn-danger'>删除</button></td>
		    	}
		    },
		    error:function(errMsg){
		        console.log(errMsg);
		    }
		})
	$.ajax({  //请求获取已存在学院信息
	    type        : 'post',
	    url         : 'getAllXyServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".xyId").empty();
	    	$(".xyId").append("<option value='0'>所有学院</option>")
	    	
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".xyId").append("<option value='"+res[i].xyId+"'>"+res[i].xyName+"</option>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$('.xyId').change(function(){  //select二级联动 select改变触发
		var xyId=$(this).children('option:selected').val();//这就是selected的值 
		if(xyId==0){  
			$(".zyId").empty();
	    	$(".zyId").append("<option value='0'>所有专业</option>");
	    	$(".bjId").empty();
	    	$(".bjId").append("<option value='0'>所有班级</option>");
		}else{
			var formData={
				"xyId":xyId	
			}
			$.ajax({  //由xyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getZyInfoByXyIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.length==0){
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>所有专业</option>")
			    	}else{
			    		$(".zyId").empty();
				    	$(".zyId").append("<option value='0'>所有专业</option>")
				        for(var i=0;i<res.length;i++){
				        	
				        	 $(".zyId").append("<option value='"+res[i].zyId+"'>"+res[i].zyName+"</option>")
				        }
			    	}
			    	
			       
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		}
	})
	$('.zyId').change(function(){  //select三级联动 select改变触发
		var zyId=$(this).children('option:selected').val();//这就是selected的值 
		if(zyId==0){  
			$(".bjId").empty();
	    	$(".bjId").append("<option value='0'>所有班级</option>");
		}else{
			var formData={
				"zyId":zyId	
			}
			$.ajax({  //由zyId请求获取已存在专业信息
			    type        : 'post',
			    url         : 'getBjInfoByZyIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.length==0){
			    		$(".bjId").empty();
				    	$(".bjId").append("<option value='0'>所有班级</option>")
			    	}else{
			    		$(".bjId").empty();
				    	$(".bjId").append("<option value='0'>所有班级</option>")
				        for(var i=0;i<res.length;i++){
				        	
				        	 $(".bjId").append("<option value='"+res[i].bjId+"'>"+res[i].bjName+"</option>")
				        }
			    	}
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		}
	})
	
	$(".seeStudent").click(function(){  //点击查询按钮
		var xyId=$(".xyId").children('option:selected').val();
		var zyId=$(".zyId").children('option:selected').val();
		var bjId=$(".bjId").children('option:selected').val();
		var formData={
			"xyId":xyId,
			"zyId":zyId,
			"bjId":bjId
		}
		$.ajax({  
			    type        : 'post',
			    url         : 'getBjInfoByBjIdServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	$(".see_student_table").empty();
					$(".see_student_table").append("<table class='table table-hover see_student_tables'><tr><th>学院</th><th>专业</th><th>班级</th></tr></table>");
			    	
			    	for(var i=0;i<res.length;i++){
			    		$(".see_student_tables").append("<tr><td>"+res[i].xyName+"</td><td>"+res[i].zyName+"</td><td>"+res[i].bjName+"</td></tr>"); //<td class='delete'><button value='"+res[i].bjId+"' class='btn btn-danger'>删除</button></td>
			    	}
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
		
	})
	
}


$("#change1").click(function(){
	var userId=$(".logout").attr("value");
	$(".container_right").empty();
	$(".container_right").append("	<!-- 修改密码 --><div class='change1 right_item'><div class='changePassword'><div class='changePassword_title'>修改登录密码</div><div class='password_input'><input type='number' name='userId' class='userId form-control' placeholder='请输入用户名' oninput='if(value.length>10)value=value.slice(0,10)'><input type='text' name='oldPasswd form-control' placeholder='请输入旧密码' oninput='if(value.length>12)value=value.slice(0,12)' class='form-control oldPassWd'><input type='text' placeholder='请输入新密码' oninput='if(value.length>9)value=value.slice(0,9)'  class='form-control newPassWd1'><input type='text' oninput='if(value.length>9)value=value.slice(0,9)'  placeholder='请确认密码'   class='form-control newPassWd2'><input type='button ' class='passWd_btn btn btn-info' value='提交'></div></div></div>");
	
	$(".passWd_btn").click(function(){
		var userId1=$(".userId").val();
		var oldPassWd=$(".oldPassWd").val();
		var newPassWd1=$(".newPassWd1").val();
		var newPassWd2=$(".newPassWd2").val();
		console.log( userId1+oldPassWd+newPassWd1+newPassWd2);
		if(userId1==''||oldPassWd==''||newPassWd1==''||newPassWd2==''){
			alert("请补全信息！");
		}else if(userId!=userId1){
			alert("用户名不正确!");
		}else if(newPassWd1!=newPassWd2){
			alert("新密码前后不一致！")
		}else{
			var formData={
				"userId":userId1,
				"oldPassWd":oldPassWd,
				"newPassWd":newPassWd1
			}
			$.ajax({  
			    type        : 'post',
			    url         : 'changePasswordServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	alert(res.data);
			    	var userId=$(".logout").attr("value");
	
					var formData={
							"userId":userId,
							"stats":0
						}
						$.ajax({  //设置用户的在线状态
						    type        : 'post',
						    url         : 'setUserLoginStatsServlet',
						    dataType    : 'json',
						    data:JSON.stringify(formData),
						    contentType:'application/json;charset=UTF-8',
						    success     : function(res){
						    	 
						    },
						    error:function(errMsg){
						        console.log(errMsg);
						    }
						})
						window.location.href="logout.jsp"
					
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
			
		}
	
	})
	
})




