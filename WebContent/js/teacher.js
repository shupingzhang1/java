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

/*我的消息 start*/

//查看消息详情
$(function () {
	message();
	
})

/*
//判断checkbox 是否选中  
$("#id").is(":checked");//选中，返回true，没选中，返回false  
  
//设置checkbox为选中状态  
$("#id").prop("checked",true);  
  
//设置checkbox为不选中状态  
$("#id").prop("checked",false);  

 */







// 导航栏选择
$(".li_item").click(function(){
	$(".item").hide();
	var value=$(this).attr("value");
	$(".li_item").css("background-color","#36b2a1");
	$(this).css("background-color","rgb(54, 178, 220)");
	if(value=='message'){
		message();
	}else if(value=="set_project"){
		set_project();
	}
})

//消息函数
function message(){
	var userId=$(".logout").attr("value");
	$(".container").empty();
	$(".container").append("<div class='message item'><span class='message_page_title'><img src='images/message.png' alt=''>消息通知</sapn>(共计<span class='hasMessage'></span>条消息，<span class='noReadMessage'></span>条未读，<a href='#' class='onlyNoRead'>仅查看未读消息</a>)</span> <div class='button_list'><button class='msg btn btn-info'>标记为已读</button><button class='msg btn btn-info'>标记所有消息为已读</button><span >|</span><button class='msg btn btn-primary msgs all_msg' >所有消息</button><button class='msg btn msgs admin_msg'>管理员通知</button><button class='msg btn msgs student_msg'>学生消息</button></div><div class='message_table'><table class='table table-hover message_tables'></table></div></div>")
	var formData={
		"userId":userId,
	}
	var data=[];
	$.ajax({  //获取消息
	    type        : 'post',
	    url         : 'getMessageByUserIdServlet',
	    dataType    : 'json',
	    data:JSON.stringify(formData),
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	 data=res;
	    	 sessionStorage.hasMessage=data.length;
	    	 var noReadMessage=0;
	    	 $(".message_tables").empty();
	    	 $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	    	 for(var i=0;i<res.length;i++){
	    		 //$(".message_tables").append("<tr class='readed_message'><td><input class='message_item item"+res[i].messageId+"'  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td><a href='#'>"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	    		 if(res[i].isRead=='0'){
	    			 noReadMessage++;
		    		 $(".message_tables").append("<tr ><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	    		 }else{
		    		 $(".message_tables").append("<tr class='readed_message'><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")

	    		 }
	    	 }
	    	 messsage2(res); 
	    	 sessionStorage.noReadMessage=noReadMessage;
	    	 $(".hasMessage").text(sessionStorage.hasMessage);
	    	 $(".noReadMessage").text(sessionStorage.noReadMessage);
	    	 $(".my_message_num").text(sessionStorage.noReadMessage);
	    	 $(".all_msg").click(function(){
	    		 $(".msgs").removeClass("btn-primary");
	    		 $(this).addClass("btn-primary");
	    		 $(".message_tables").empty();
	    		 $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	    		 for(var i=0;i<res.length;i++){
	    			 if(res[i].isRead=='0'){
			    		 $(".message_tables").append("<tr ><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
		    		 }else{
			    		 $(".message_tables").append("<tr class='readed_message'><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")

		    		 }	 	    	 }	 messsage2(res); 	 
	    	})
	    	  $(".student_msg").click(function(){
	    		 $(".msgs").removeClass("btn-primary");
	    		 $(this).addClass("btn-primary");
	    		 $(".message_tables").empty();
	    		 $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	    		 for(var i=0;i<res.length;i++){
	 	    		 if(res[i].messageTypeId=='6'&&res[i].isRead=='0'){
			    		 $(".message_tables").append("<tr ><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	 	    		 }else if(res[i].messageTypeId=='6'&&res[i].isRead=='1'){
			    		 $(".message_tables").append("<tr class='readed_message'><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	 	    		 }
	 	    	 }
	    		 messsage2(res)
	    		 	 
	    	 })
	    	  $(".admin_msg").click(function(){
	    		 $(".msgs").removeClass("btn-primary");
	    		 $(this).addClass("btn-primary");
	    		
	    		 $(".message_tables").empty();
	    		 $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	    		 for(var i=0;i<res.length;i++){
	 	    		 if(res[i].messageTypeId!='6'&&res[i].isRead=='0'){
		 	    		 $(".message_tables").append("<tr class=''><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'> <a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>系统消息</td></tr>")
	 	    		 }else if(res[i].messageTypeId!='6'&&res[i].isRead=='1'){
			    		 $(".message_tables").append("<tr class='readed_message'><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	 	    		 }
	 	    	 }
	    		 messsage2(res)
	    			 
	    	 })
	    	 $(".onlyNoRead").click(function(){
	    		 $(".msgs").removeClass("btn-primary");
	    		 $(".all_msg").addClass("btn-primary");
	    		  $(".message_tables").empty();
	    		  $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	    		  for(var i=0;i<res.length;i++){
	 	    		 if(res[i].isRead=='0'){
			    		 $(".message_tables").append("<tr ><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	 	    		 }
	 	    	 }
	    		  messsage2(res);	 
	    		  $(".all_msg").click(function(){
	 	    		 $(".msgs").removeClass("btn-primary");
	 	    		 $(this).addClass("btn-primary");
	 	    		 $(".message_tables").empty();
	 	    		 $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	 	    		 for(var i=0;i<res.length;i++){
	 	    			 if(res[i].isRead=='0'){
				    		 $(".message_tables").append("<tr ><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	 	    			 }	
	 	    		 }    
	 	    		messsage2(res)
	 	    		 	 
	 	    	 })
	 	    	  $(".student_msg").click(function(){
	 	    		 $(".msgs").removeClass("btn-primary");
	 	    		 $(this).addClass("btn-primary");
	 	    		 $(".message_tables").empty();
	 	    		 $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	 	    		 for(var i=0;i<res.length;i++){
	 	 	    		 if(res[i].messageTypeId=='6'&&res[i].isRead=='0'){
				    		 $(".message_tables").append("<tr ><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	 	 	    		 }
	 	 	    	 }
	 	    		messsage2(res)
	 	    			 
	 	    	 })
	 	    	  $(".admin_msg").click(function(){
	 	    		 $(".msgs").removeClass("btn-primary");
	 	    		 $(this).addClass("btn-primary");
	 	    		
	 	    		 $(".message_tables").empty();
	 	    		 $(".message_tables").append("<tr><th><input class='message_all' type='checkbox'   value='all'></th><th>消息标题</th><th>发送时间</th><th>消息类型</th></tr>");
	 	    		 for(var i=0;i<res.length;i++){
	 	 	    		 if(res[i].messageTypeId!='6'&&res[i].isRead=='0'){
				    		 $(".message_tables").append("<tr ><td><input class='message_item '  name='checkbox' type='checkbox' value='"+res[i].messageId+"'></td><td class='msg_info' value='"+(i+1)+"' a='"+res[i].messageId+"'><a href='#' >"+res[i].messageTitle+"</a></td><td>"+res[i].messageTime+"</td><td>"+(res[i].messageTypeId=='6'?'学生消息':'管理员消息')+"</td></tr>")
	 	 	    		 }
	 	 	    	 }
	 	    		messsage2(res)
	 	    		 	 
	 	    		
	 	    	 })

	    	 })	
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})

	
}

//message 辅助函数 获取消息详情
function messsage2(res){
	message3()  	
	 $(".msg_info").click(function(){
		  	var i=$(this).attr("value");
		  	var messageId=$(this).attr("a");
		  	formData={
		  		"messageId":messageId	
		  	}
  		 $(".container").empty();
  		 if(res[i-1].student){
	    		 $(".container").append("<div class='message_info'><span class='back'><img src='images/back.png' alt=''><span >返回</span></span><div class='message_title'>"+res[i-1].messageTitle+" </div><div class='message_content'>"+res[i-1].messageCountent+"</div><div class='message_des'><span class='time'>"+res[i-1].messageTime+"</span><span class='message_from'>来自<span class='message_send_man'>"+res[i-1].student+"</span></span></div></div>");
  		 }else{
	    		 $(".container").append("<div class='message_info'><span class='back'><img src='images/back.png' alt=''><span >返回</span></span><div class='message_title'>"+res[i-1].messageTitle+" </div><div class='message_content'>"+res[i-1].messageCountent+"</div><div class='message_des'><span class='time'>"+res[i-1].messageTime+"</span><span class='message_from'>来自<span class='message_send_man'>管理员</span></span></div></div>");
  		 }
  		 if(res[i-1].isRead==0){
  			$.ajax({  //设置消息为已读
			    type        : 'post',
			    url         : 'readMessageServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			    	if(res.data==1){
			    		$(".back").click(function(){
			    			message();
			    		})
			    	} 
			    },
			    error:function(errMsg){
			        console.log(errMsg);
			    }
			})
  		 }else{
  			$(".back").click(function(){
    			message();
    		})
  		 }
  		
  		
	  })
}

//消息选择辅助函数
function message3(){
	$(".message_all").click(function(){
		if($(".message_all").is(":checked")){
			 $(".message_item").prop("checked",true);
			
		}else{
			$(".message_item").prop("checked",false);
		}
	})
	
	$(".message_item").click(function(){
		if(!$(this).is(":checked")){
			$(".message_all").prop("checked",false);
		}else{
	
			 $(".deletemsg").attr("disabled",false);
		}
	})
}


// 查看我的课表
function set_project(){
	var userId=$(".logout").attr("value");
	$(".container").empty();
	$(".container").append("<!-- /*开设课程*/ --><div class='set_project item'><div class='title'><span class='set_project_page_title'><img src='images/Projector.png' alt=''>开设课程</span></div><div class='my_projects'><img src='images/kebiao.png' alt=''><div>查看我的课表</div></div><div class='project_input'> <select name='' id='' class='form-control xyId'><option value='0'>请选择学院</option></select><select name='' id='' class='form-control zyId'><option value='0'>请选择专业</option></select><select name='' id='' class='form-control bjId'><option value='0'>请选择班级</option></select><select name='' id='' class='form-control project'><option value='0'>请选择课程</option></select><select name='' id='' class='form-control semester'><option value='0'>请选择开课学期</option></select><input type='text' class='form-control week' placeholder='输入上课周数(如1-16周)'><input type='text' class='form-control time' data-toggle='modal' data-target='#myModal' readonly='readonly'  placeholder='选择上课时间'><input type='number' class='form-control credit' placeholder='请输入课程学分'><input type='button' class='btn btn-info project_btn' value='提交'></div></div>");
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
	    	$(".bjId").append("<option value='0'>请选择班级</option>");
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
	$.ajax({  //请求获取已存在课程
	    type        : 'post',
	    url         : 'getAllProjectServlet',
	    dataType    : 'json',
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	//console.log(res.data+"-----");
	    	$(".project").empty();
	    	$(".project").append("<option value='0'>请选择课程</option>")
	        for(var i=0;i<res.length;i++){
	        	 $(".project").append("<option value='"+res[i].prId+"'>"+res[i].prName+"</option>")
	        }
	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$.ajax({  //请求获取已存在学期信息
	    type        : 'post',
	    url         : 'getAllSemesterInfoServlet',
	    dataType    : 'json',
	    
	    contentType:'application/json;charset=UTF-8',
	    success     : function(res){
	    	$(".semester").empty();
	    	$(".semester").append("<option value='0'>请选择开课学期</option>");
	        for(var i=0;i<res.length;i++){
	        	
	        	 $(".semester").append("<option value='"+res[i].semesterId+"'>"+res[i].semesterName+"</option>")
	        }

	       
	    },
	    error:function(errMsg){
	        console.log(errMsg);
	    }
	})
	$(".my_projects").click(function(){
		var teacherId=$(".logout").attr("teacherId");
		var formData={
			"teacherId":teacherId
		}
		$.ajax({  //教师开设课程
		    type        : 'post',
		    url         : 'getTeacherHasProjectServlet',
		    dataType    : 'json',
		    data:JSON.stringify(formData),
		    contentType:'application/json;charset=UTF-8',
		    success     : function(res){
		    	$(".container").empty();
		    	$(".container").append("<div class='my_project'><span class='back1'><img src='images/back.png' alt=''><span >返回</span></span><div class='project_table'><table><tr class='table_title'><th colspan='2'></th><th>周一</th><th>周二</th><th>周三</th><th>周四</th><th>周五</th><th>周六</th><th>周日</th></tr><tr><td rowspan='2'>上<br>午</td><td>07:50<br>~<br>09:25</td><td class='p1'></td><td class='p6'></td><td class='p11'></td><td class='p16'></td><td class='p21'></td><td class='p26'></td><td class='p31'></td></tr><tr><td>9:45<br>~<br>12:10</td><td class='p2'></td><td class='p7'></td><td class='p12'></td><td class='p17'></td><td class='p22'></td><td class='p27'></td><td class='p32'></td></tr><tr><td rowspan='2'>下<br>午</td><td>13:50<br>~<br>15:25</td><td class='p3'></td><td class='p8'></td><td class='p13'></td><td class='p18'></td><td class='p23'></td><td class='p28'></td><td class='p33'></td></tr><tr><td>15:45<br>~<br>18:10</td><td class='p4'></td><td class='p9'></td><td class='p14'></td><td class='p19'></td><td class='p24'></td><td class='p29'></td><td class='p34'></td></tr><tr><td rowspan='1'>晚<br>上</td><td>18:50<br>~<br>20:25</td><td class='p5'></td><td class='p10'></td><td class='p15'></td><td class='p20'></td><td class='p25'></td><td class='p30'></td><td class='p35'></td></tr></table></div></div>");
		    	

		    },
		    error:function(errMsg){
		    	alert(res.data);
		        console.log(errMsg);
		    }
		})
	})
	
	
	var projecttime='';
	$(".btn_project_time").click(function(){
		var freetime=[];
		$("input[name='freetime']:checked").each(function(i) {
			freetime[i] = $(this).val();
		});
		freetime.sort();
		
		var projectTime=['周一1~2','周一3~5','周一6~7','周一8~10','周一11~12','周二1~2','周二3~5','周二6~7','周二8~10','周二11~12','周三1~2','周三3~5','周三6~7','周三8~10','周三11~12','周四1~2','周四3~5','周四6~7','周四8~10','周四11~12','周五1~2','周五3~5','周五6~7','周五8~10','周五11~12','周六1~2','周六3~5','周六6~7','周六8~10','周六11~12','周天1~2','周天3~5','周天6~7','周天8~10','周天11~12'];
		var time='';
		projecttime=''
		for(var i=0;i<freetime.length;i++){
			time+=","+projectTime[parseInt(freetime[i])-1];
			projecttime+=","+freetime[i];
		}
		time=time.substring(1);
		$(".time").val(time);
		 $('#myModal').modal('hide');
		 
	})
	
	$(".project_btn").click(function(){
		var bjId=$(".bjId").find("option:selected").val();
		var semesterId=$(".semester").find("option:selected").val();
		var prId=$(".project").find("option:selected").val();
		var week=$(".week").val();
		var length=week.indexOf('-') ;
		var patt = /(\d-\d)|(\d\d-\d\d)|(\d-\d\d)/;
		var weekstart=week.substring(0,length);
		var weekend=week.substring(length+1);
		
		var credit=$(".credit").val();
		if(bjId==0){
			alert("请选择班级！");
		}else if(prId==0){
			alert("请选择课程！")
		}else if(semesterId==0){
			alert("请选择学期！")
		}else if(week==''){
			alert("请输入上课周数！")
		}else if(!patt.test(week)){
			alert("上课周数格式不正确！")
		}else if(weekstart<1||weekstart>weekend||weekend>16){
			alert("上课周数在1-16范围内！")
		}else if(projecttime==''){
			alert("请选择上课时间！")
		}else if(credit==''){
			alert("请输入课程学分！")
		}else{
			var teacherId=$(".logout").attr("teacherId");
			var formData={
				"prId":prId,
				"bjId":bjId,
				"teacherId":teacherId,
				"semesterId":semesterId,
				"credit":credit,
				"weekStart":weekstart,
				"weekEnd":weekend,
				"time":projecttime
			}
			$.ajax({  //教师开设课程
			    type        : 'post',
			    url         : 'setProjectServlet',
			    dataType    : 'json',
			    data:JSON.stringify(formData),
			    contentType:'application/json;charset=UTF-8',
			    success     : function(res){
			       alert(res.data);
			       set_project();
			    },
			    error:function(errMsg){
			    	alert(res.data);
			        console.log(errMsg);
			    }
			})
			
		}
	})
	
}

//返回开设课程
$(".back1").click(function(){
	$(".set_project").show()
	$(".my_project").hide()
})

// 添加考试成绩具体页面
$(".add").click(function(){
	$(".add_achievements").show()
	$(".add_achievement").hide()
})
// 返回添加成绩
$(".back2").click(function(){
	$(".add_achievement").show()
	$(".add_achievements").hide()
})




