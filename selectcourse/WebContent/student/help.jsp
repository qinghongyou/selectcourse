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
    <li><a href="#">帮助</a></li>
    </ul>
    </div>
    
    <div class="rightinfo" style="margin-left:20px;color:#0f0f00; font-size: 14px;line-height: 35px;">
    	1.此系统为学生选课系统，集注册学生信息，登录，选课，查询已选课程等诸多功能；<br/>
    	2.由于时间和能力有限，部分功能还未开发，敬请谅解；<br/>
    	3.如果有疑问，可以联系qinghongyou,我会积极解答；<br/>
    	4.QQ：1917958423；<br/>
    	5.地址：景德镇陶瓷大学信息工程学院16信管1班；<br/>
   	</div>
</body>

</html>
