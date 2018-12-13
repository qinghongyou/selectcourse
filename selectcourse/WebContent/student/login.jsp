<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>欢迎登录学生选课系统</title>
		<link href="http://localhost:8088/selectcourse/resources/css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="http://localhost:8088/selectcourse/resources/js/jquery.js"></script>
		<script src="http://localhost:8088/selectcourse/resources/js/cloud.js" type="text/javascript"></script>
		
		<script language="javascript">
			$(function(){
			    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
				$(window).resize(function(){  
			    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
			    })  
			}); 
			
			function loginBtn(){
				var stuLoginName = $("input[name='stuLoginName'] ").val();
		        var stuPwd = $("input[name='stuPwd'] ").val();
		    	var yzm = $("input[name='yzm'] ").val(); 
		    	console.info(stuLoginName+"  "+stuPwd+" "+yzm);
		    	url="/selectcourse/LoginServlet";
				$.ajax({
					type:"post",
					url:url,
					data:{"stuLoginName":stuLoginName, "stuPwd":stuPwd},
					dataType:"json",
					async: false,
					success: function(data) {
						console.info(data);
						alert("登录成功");
					},
					error: function() {
						alert("用户名或密码错误");
					}
				});
			}
		</script> 
	
	</head>
	
	<body style="background-color:#df7611; background-image:url(http://localhost:8088/selectcourse/resources/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
		<div id="mainBody">
	      <div id="cloud1" class="cloud"></div>
	      <div id="cloud2" class="cloud"></div>
	    </div>  
		<div class="logintop">    
		    <span>欢迎登录学生选课系统管理界面平台</span>    
		    <ul>
		    <li><a href="http://localhost:8088/selectcourse/admin/login.jsp">Admin</a></li>
		    <li><a href="http://localhost:8088/selectcourse/student/register.jsp">注册</a></li>
		    <li><a href="http://localhost:8088/selectcourse/student/help.jsp">帮助</a></li>
		    <li><a href="http://localhost:8088/selectcourse/student/about.jsp">关于</a></li>
		    </ul>    
		    </div>
		    <div class="loginbody" style="margin-top:20px;">
		    <!-- <span class="systemlogo"></span>  -->
		    <form action="/selectcourse/StudentServlet?action=logon" method="post">
		    <div class="loginbox loginbox1">
			    <ul>
			    <li><input name="stuLoginName" type="text" class="loginuser" value="用户名" onclick="JavaScript:this.value=''"/></li>
			    <li><input name="stuPwd" type="password" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
			    
			    <li class="yzm">
			    <span><input name="yzm" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite> 
			    </li>
			    <%
			    
			    	Object lists = request.getAttribute("strList");
		    		ArrayList<String> strList = (ArrayList<String>)lists;
			    	if(null!=strList&&strList.size()>0){
			    		for(int i=0;i<strList.size();i++){
			    			String str=strList.get(i);
			    %>
			    	
			    	<div class="loginpwd1"><%=str %></div>	
			    <%
			    		}
			    	}else{
			    %>
			    <span></span>	
			    <%
			    	} 
			    %>
			    <li><input name="" type="submit" class="loginbtn" value="登录" /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
			    </ul>
		    </div>
		    </form>
	    </div>
	    <div class="loginbm">版权所有  2014  <a href="#">www.baidu.com</a>  仅供学生选课使用  </div>
	</body>
</html>