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
 <%
	HttpSession sessions=request.getSession();
	Object stus = session.getAttribute("stus");
	Students stus1 = (Students)stus;
%> 

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">已选课程</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    	<form action="/selectcourse/StuCouServlet?action=findMyCouOnly&stuId=<%=stus1.getStuId() %>" method="post">
    	<ul class="seachform1">
		    <li><label>课程名称</label><input name="couName" type="text" class="scinput1" value="${couName }" /></li>
		    <li><label>任课教师</label><input name="teacher" type="text" class="scinput1" value="${teacher }" /></li>
    		<li>&nbsp;&nbsp;&nbsp;&nbsp;<input name="submit" type="submit" class="scbtn1" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="cancel" type="submit" class="scbtn1" value="取消"/></li>  
	    </ul>
	    </form>
    </div>
    
    
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>序号</th>
		        <th>课程编号<i class="sort"><img src="http://localhost:8088/selectcourse/resources/images/px.gif" /></i></th>
		        <th>课程名称</th>
		        <th>授课教师</th>
		        <th>课程描述</th>
		        <th>学分</th>
		        <th>操作</th>
	        </tr>
        </thead>
        <tbody>
        	<%
        		Object obj = request.getAttribute("list");
        		ArrayList<Course> courses = (ArrayList<Course>)obj;
        		if(courses.size()>0){
	        		for(int i=0;i<courses.size();i++){
	        			Course course = courses.get(i);
        	%>
	        <tr>
	        	<td><%=i+1 %></td>
		        <td><%=course.getCouId() %></td>
		        <td><%=course.getCouName() %></td>
		        <td><%=course.getTeacher() %></td>
		        <td><%=course.getCourseDes() %></td>
		        <td><%=course.getCredit() %></td>
		        <td><a href="/selectcourse/StuCouServlet?action=delCou&stuId=<%=stus1.getStuId() %>&couId=<%=course.getCouId() %>" class="tablelink">删除</a></td>
	        </tr> 
	        <%
        			}
        		}else{
	        %>
	        <tr>
		        <td colspan="6">没有课程信息！</td>
	        </tr>
	        <%
        		}
	        %>
        </tbody>
    </table>
    
   <%
   	if(courses.size()>0){
   %>
    <div class="pagin">
    	<div class="message">共<i class="blue">${paging.count}</i>条记录，共&nbsp;<i class="blue">${paging.pagenumber}&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="/selectcourse/CourseServlet?action=selectAll&page=${paging.indexpage-1}">首页</a></li>
        <li class="paginItem"><a href="/selectcourse/CourseServlet?action=selectAll&page=${paging.page-1}"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">${paging.page+1}</a></li>
        <li class="paginItem"><a href="/selectcourse/CourseServlet?action=selectAll&page=${paging.page+1}"><span class="pagenxt"></span></a></li>
        <li class="paginItem"><a href="/selectcourse/CourseServlet?action=selectAll&page=${paging.pagenumber-1}">尾页</a></li>
        </ul>
    </div>
    <%
   	}
    %>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
