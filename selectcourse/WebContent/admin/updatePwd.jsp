<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yqh.beans.Course" %>
<%@ page import="com.yqh.beans.Students" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生选课系统</title>
<link href="http://localhost:8088/selectcourse/resources/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://localhost:8088/selectcourse/resources/js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">修改密码</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
	    <div class="formtitle"><span>修改密码</span></div>
			<form action="/selectcourse/AdminServlet?action=updatePwd&admId=1" method="post">
		    <ul class="forminfo">
			    <li><label>原密码</label><input name="oldPwd" value="" type="password" class="dfinput" /></li>
			    <li><label>新密码</label><input name="newPwd" value="" type="password" class="dfinput" /></li>
			    <li><label>确认新密码</label><input name="newPwd2" value="" type="password" class="dfinput" /></li>
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
		    	
		    	<div style="margin-top: 20px;margin-left: 150px;">
			    	<input name="" type="submit" class="scbtn1" value="修改密码"/></li>  
		    	</div>
		    </ul>
		    </form>
		    
	    </div>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
