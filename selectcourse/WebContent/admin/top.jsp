<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yqh.beans.Admin" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="http://localhost:8088/selectcourse/resources/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="http://localhost:8088/selectcourse/resources/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(http://localhost:8088/selectcourse/resources/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="#" target="_parent"><img src="http://localhost:8088/selectcourse/resources/images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <!-- <li><a href="default.html" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="imgtable.html" target="rightFrame"><img src="images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="imglist.html"  target="rightFrame"><img src="images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="tools.html"  target="rightFrame"><img src="images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
    <li><a href="computer.html" target="rightFrame"><img src="images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="tab.html"  target="rightFrame"><img src="images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li> -->
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="http://localhost:8088/selectcourse/resources/images/help.png" title="帮助"  class="helpimg"/></span>
    <a href="http://localhost:8088/selectcourse/student/help.jsp" target="rightFrame">帮助</a></li>
    <li><a href="http://localhost:8088/selectcourse/student/about.jsp" target="rightFrame">关于</a></li>
    <li><a href="http://localhost:8088/selectcourse/index.jsp" target="_parent">退出</a></li>
    </ul>
    <%
	    HttpSession sessions=request.getSession();
		Object adms = session.getAttribute("adms");
		Admin adm = (Admin)adms;
	%> 
    <div class="user">
    <span><a href="#"><%=adm.getAdmName() %></a></span>
    <i><a href="/selectcourse/ShowLogServlet?action=findHisAdm&stuId=1" target="rightFrame">消息</a></i>
    <b>*</b>
    </div>    
    
    </div>

</body>
</html>
