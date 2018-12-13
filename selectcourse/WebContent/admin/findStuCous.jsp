<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yqh.beans.Students" %>
<%@ page import="com.yqh.utils.StuCous" %>
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
    <li><a href="#">学生选课记录</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <%-- 
    <div class="tools">
    	<form action="/selectcourse/ShowLogServlet?action=findHisAllWhere" method="post">
    	<ul class="seachform1">
		    <li><label>学生姓名</label><input name="stuName" type="text" class="scinput1" value="${stuName }" /></li>
    		<li>&nbsp;&nbsp;<input name="submit" type="submit" class="scbtn1" value="查询"/>&nbsp;&nbsp;
    		<input name="cancel" type="submit" class="scbtn1" value="取消"/></li>
	    </ul>
	    </form>
	    
    </div> --%>
    
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>序号</th>
		        <th>学号</th>
		        <th>姓名</th>
		        <th>课程名称</th>
		        <th>授课教师</th>
		        <th>课程描述</th>
	        </tr>
        </thead>
        <tbody>
        	<%
        		Object obj = request.getAttribute("list");
        		ArrayList<StuCous> stuCous = (ArrayList<StuCous>)obj;
        		if(stuCous.size()>0){
	        		for(int i=0;i<stuCous.size();i++){
	        			StuCous stuCou = stuCous.get(i);
        	%>
	        <tr>
		        <td><%=i+1 %></td>
		        <td><%=stuCou.getStuid() %></td>
		        <td><%=stuCou.getStuname() %></td>
		        <td><%=stuCou.getCouname() %></td>
		        <td><%=stuCou.getTeacher() %></td>
		        <td><%=stuCou.getCouexp() %></td>
	        </tr> 
	        <%
        			}
        		}else{
	        %>
	        <tr>
		        <td colspan="6">没有历史操作信息</td>
	        </tr>
	        <%
        		}
	        %>
        </tbody>
    </table>
    
   <%
   	String flag = (String)request.getAttribute("flag");
   	String stuName = (String)request.getAttribute("stuName");
   	String couName = (String)request.getAttribute("couName");
   	if(stuCous.size()>0 && flag.equals("success")){
   %>
    <div class="pagin">
    	<div class="message">共<i class="blue">${paging.count}</i>条记录，共&nbsp;<i class="blue">${paging.pagenumber}&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAll&page=${paging.indexpage-1}">首页</a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAll&page=${paging.page-1}"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">${paging.page+1}</a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAll&page=${paging.page+1}"><span class="pagenxt"></span></a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAll&page=${paging.pagenumber-1}">尾页</a></li>
        </ul>
    </div>
    <%
   	//}else if(showLogs.size()>0 && flag.equals("error")){
   	%>
   	<%-- <div class="pagin">
    	<div class="message">共<i class="blue">${paging.count}</i>条记录，共&nbsp;<i class="blue">${paging.pagenumber}&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAllWhere&stuName=${stuName }&submit=查询&page=${paging.indexpage-1}">首页</a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAllWhere&stuName=${stuName }&submit=查询&page=${paging.page-1}"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">${paging.page+1}</a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAllWhere&stuName=${stuName }&submit=查询&page=${paging.page+1}"><span class="pagenxt"></span></a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHisAllWhere&stuName=${stuName }&submit=查询&page=${paging.pagenumber-1}">尾页</a></li>
        </ul>
    </div> --%>
   	<% 	
   		}
    %>
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
