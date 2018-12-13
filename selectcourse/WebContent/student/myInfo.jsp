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
    <li><a href="#">我的信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
	    <div class="formtitle"><span>我的信息</span></div>
	    	<%
		    	HttpSession sessions=request.getSession();
				Object stus = sessions.getAttribute("stus");
				Students stus1 = (Students)stus;
			%>
		    <ul class="forminfo">
			    <li><label>学号</label><input name="" value="<%=stus1.getStuId() %>" type="text" class="dfinput" readonly="readonly" /></li>
			    <li><label>姓名</label><input name="" value="<%=stus1.getStuName() %>" type="text" class="dfinput" readonly="readonly" /></li>
			    <li><label>登录名</label><input name="" value="<%=stus1.getStuLoginName() %>" type="text" class="dfinput" readonly="readonly" /></li>
			    <li><label>性别</label><input name="" value="<%=stus1.getStuSex() %>" type="text" class="dfinput" readonly="readonly" /></li>
			    <li><label>学院</label><input name="" value="<%=stus1.getStuInstitute() %>" type="text" class="dfinput" readonly="readonly" /></li>
		    </ul>
	    </div>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
