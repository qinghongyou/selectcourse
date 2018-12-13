<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yqh.beans.ShowLog" %>
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
    <li><a href="#">操作历史</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>操作编号<i class="sort"><img src="http://localhost:8088/selectcourse/resources/images/px.gif" /></i></th>
		        <th>姓名</th>
		        <th>操作</th>
		        <th>操作描述</th>
		        <th>时间</th>
	        </tr>
        </thead>
        <tbody>
        	<%
        		Object obj = request.getAttribute("list");
        		ArrayList<ShowLog> showLogs = (ArrayList<ShowLog>)obj;
        		if(showLogs.size()>0){
	        		for(int i=0;i<showLogs.size();i++){
	        			ShowLog showLog = showLogs.get(i);
        	%>
	        <tr>
		        <td><%=i+1 %></td>
		        <td><%=showLog.getUserName() %></td>
		        <td><%=showLog.getDoing() %></td>
		        <td><%=showLog.getMsg() %></td>
		        <td><%=showLog.getLogDate() %></td>
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
	HttpSession sessions=request.getSession();
	Object stus = session.getAttribute("stus");
	Students stus1 = (Students)stus;
   	if(showLogs.size()>0){
   %>
    <div class="pagin">
    	<div class="message">共<i class="blue">${paging.count}</i>条记录，共&nbsp;<i class="blue">${paging.pagenumber}&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHis&stuId=<%=stus1.getStuId() %>&page=${paging.indexpage-1}">首页</a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHis&stuId=<%=stus1.getStuId() %>&page=${paging.page-1}"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">${paging.page+1}</a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHis&stuId=<%=stus1.getStuId() %>&page=${paging.page+1}"><span class="pagenxt"></span></a></li>
        <li class="paginItem"><a href="/selectcourse/ShowLogServlet?action=findHis&stuId=<%=stus1.getStuId() %>&page=${paging.pagenumber-1}">尾页</a></li>
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
