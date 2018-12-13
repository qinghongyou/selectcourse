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

	<!-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">我的选课</a></li>
    </ul>
    </div> -->
    
    <div class="rightinfo">
    	<center>
    	<div style="color:#4b4343; font-size: 19px;">
    		<%
    		String msg = (String)request.getAttribute("msg");
    		%>
    		<%=msg %>
    	</div>
    	<div style="margin-top: 20px">
	    	<input name="" type="submit" onclick="javascript:window.location.href='http://localhost:8088/selectcourse/student/login.jsp'" class="scbtn1" value="登录"/></li>  
    	</div>
    	</center>
   	</div>
</body>

</html>
