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
    <li><a href="#">新建课程</a></li>
    </ul>
    </div>
    
    <div class="formbody">
	    <div class="formtitle"><span>新建课程</span></div>
	    	<form action="/selectcourse/CourseServlet?action=addCou" method="post">
			    <ul class="forminfo">
				    <li><label>课程编号</label><input name="couid" value="" type="text" class="dfinput"/></li>
				    <li><label>课程名称</label><input name="couname" value="" type="text" class="dfinput"/></li>
				    <li><label>授课教师</label><input name="teacher" value="" type="text" class="dfinput"/></li>
				    <li><label>课程描述</label><textarea name="couexp" style="height:100px;" cols="" rows="" class="textinput"></textarea></li>
				    <li><label>学分</label><input name="credit" value="" type="text" class="dfinput"/></li>
			    	<%
				    	Object lists = request.getAttribute("strList");
			    		ArrayList<String> strList = (ArrayList<String>)lists;
				    	if(null!=strList&&strList.size()>0){
				    		for(int i=0;i<strList.size();i++){
				    			String str=strList.get(i);
				    %>
				    	
				    	<div class="loginpwd1" style="color:#4b4343;margin-left:230px;"><%=str %></div>	
				    <%
				    		}
				    	}else{
				    %>
				    <span></span>	
				    <%
				    	} 
				    %>
			    	
			    	<div style="margin-top: 10px;margin-left:300px;">
				    	<input name="" type="submit" class="scbtn1" value="确认保存"/></li>  
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
