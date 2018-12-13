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
		    <li><a href="http://localhost:8088/selectcourse/student/login.jsp">登录</a></li>
		    <li><a href="http://localhost:8088/selectcourse/student/help.jsp">帮助</a></li>
		    <li><a href="http://localhost:8088/selectcourse/student/about.jsp">关于</a></li>
		    </ul>    
		    </div>
		    <div class="loginbody">
		    <span ></span> 
		    <form action="/selectcourse/StudentServlet?action=register" method="post">
		    <center>
		    <div>
		    	<div class="userreg">用户注册</div>
		    	<%
			    
			    	Object lists = request.getAttribute("strList");
		    		ArrayList<String> strList = (ArrayList<String>)lists;
			    	if(null!=strList&&strList.size()>0){
			    		for(int i=0;i<strList.size();i++){
			    			String str=strList.get(i);
			    %>
			    	
			    	<div class="loginpwd1" style="color:#ffffff;"><%=str %></div>	
			    <%
			    		}
			    	}else{
			    %>
			    <span></span>	
			    <%
			    	} 
			    %>
			    <ul>
			    <li style="font-size:15px;color:#ffc;">学&nbsp;&nbsp;&nbsp;号：<input name="stuId" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/></li>
			    <li style="font-size:15px;color:#ffc;">姓&nbsp;&nbsp;&nbsp;名：<input name="stuName" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/></li>
			    <li style="font-size:15px;color:#ffc;">用户名：<input name="stuLoginName" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/></li>
			    <li style="font-size:15px;color:#ffc;">密&nbsp;&nbsp;&nbsp;码：<input name="stuPwd" type="password" class="loginuserreg"  onclick="JavaScript:this.value=''"/></li>
			    <li style="font-size:15px;color:#ffc;">学&nbsp;&nbsp;&nbsp;院：
			    	<select name="stuInstitute" class="loginuserreg" style="width:320px;">
					  <option value ="信息工程学院">信息工程学院</option>
					  <option value ="设计艺术学院">设计艺术学院</option>
					  <option value="陶瓷美术学院">陶瓷美术学院</option>
					  <option value="法学系">法学系</option>
					</select>
			    </li>
			    <li style="font-size:15px;color:#ffc; margin:12px 0;">性&nbsp;&nbsp;&nbsp;别：
			    	&nbsp;&nbsp;&nbsp;
			    	男：<input name="stuSex" style="width:15px;height:15px;" type="radio" value="男" checked="checked" />
			    	&nbsp;&nbsp;
			    	女：<input name="stuSex" style="width:15px;height:15px;" type="radio" value="女" />
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </li>
			    <li><input name="" type="submit" class="loginbtn" value="注册" /></li>
			    </ul>
			    
				<!-- <div class="userinfo">
					学&nbsp;&nbsp;&nbsp;号：<input name="stuId" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/>
				</div>
			    <div class="userinfo">	
			    	姓&nbsp;&nbsp;&nbsp;名：<input name="stuName" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/>
			   	</div>
				<div class="userinfo">
					用户名：<input name="stuLoginName" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/>
			 	</div>
			 	<div class="userinfo"> 
			  		 密&nbsp;&nbsp;&nbsp;码：<input name="stuPwd" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/>
			   	</div>
			   	<div class="userinfo">
			    	学&nbsp;&nbsp;&nbsp;院：<input name="stuInstitute" type="text" class="loginuserreg"  onclick="JavaScript:this.value=''"/>
			    </div>	
			    <div class="userinfo" style="width:299px; height:48px; margin-top:10px;">
			    	性&nbsp;&nbsp;&nbsp;别：&nbsp;&nbsp;
			    	<input name="stuSex" type="radio" style="width:17px;height:16px;" value="男" checked="checked" />
			    	<input name="stuSex" type="radio" style="width:17px;height:16px;" value="女" />
			    	<div style="100px;">&nbsp;</div>
				</div>
				<div style="margin-left:650px;">
					<input name="" type="submit" class="loginbtn" value="注册" />
			    </div> -->
		    </div>
		    </center>
		    </form>
	    </div>
	    <div class="loginbm">版权所有  2014  <a href="#">www.baidu.com</a>  仅供学生选课使用  </div>
	</body>
</html>