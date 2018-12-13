package com.yqh.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yqh.beans.ShowLog;
import com.yqh.beans.StuCou;
import com.yqh.dao.CourseDao;
import com.yqh.dao.ShowLogDao;
import com.yqh.dao.StuCouDao;
import com.yqh.utils.MyTools;
import com.yqh.utils.Paging;

@WebServlet("/StuCouServlet")
public class StuCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StuCouServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null)			
			action="";
		if(action.equals("addCou")){
			this.addCou(request,response);
		}
		if(action.equals("findMyCou")){
			this.findMyCou(request,response);
		}
		if(action.equals("findMyCouOnly")){
			this.findMyCouOnly(request,response);
		}
		if(action.equals("delCou")){
			this.delCou(request,response);
		}
		if(action.equals("findStuSelectCou")){
			this.findStuSelectCou(request,response);
		}
		if(action.equals("findStuSelectCouWhere")){
			this.findStuSelectCouWhere(request,response);
		}
		
	}
	
	private void findStuSelectCouWhere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		String stuName = request.getParameter("stuName").trim();
		stuName = MyTools.toChinese(stuName);
		String couName = request.getParameter("couName").trim();
		couName = MyTools.toChinese(couName);
		String submit = request.getParameter("submit");
		submit = MyTools.toChinese(submit);
		String cancel = request.getParameter("cancel");
		cancel = MyTools.toChinese(cancel);
		List<Object> stuCous = null;
		if(null!=cancel&&cancel.equals("取消")){
			stuCous  = StuCouDao.findStuSelectCou();
			stuName ="";
			couName ="";
		}if((null!=submit&&submit.equals("查询"))){
			stuCous  = StuCouDao.findStuSelectCouWhere(stuName,couName);
		}else{
			stuCous = StuCouDao.findStuSelectCou();
			stuName ="";
			couName ="";
		}
		if(null!=stuCous&&stuCous.size()>0){
			//页面当前页
	        int page=0;
	        //得到传过来的当前页
	        String str_page= request.getParameter("page");
	        // 创建分页的关于一些内容的工具bean
	        Paging paging=new Paging();
	        paging.setList(stuCous);//从数据库得到数据存入的list集合
	        paging.setCount();//数据总数
	        paging.setPagesize(8);//一个页面的数据多少条
	        paging.setPagenumber();//总的页面数
	        paging.setEndpage();//最后一页
	        paging.setIndexpage(1);//第一页
	        if (str_page!=null) {
	            //将页转换整型判断其大小
	            int pag=Integer.parseInt(str_page);
	            //当大于零，将传过来的pag值赋给当前页page
	            if (pag>=0) {
	                page=pag;
	                //如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
	                if (pag>(paging.getPagenumber()-1)) {
	                    page=pag-1;
	                }
	            }
	        }
	        paging.setPage(page);//最终确认当前页
	        List<Object> list_page =new ArrayList<>();
	        //将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
	        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<stuCous.size(); i++) {
	            list_page.add(stuCous.get(i));
	        }
	        //将paging对象其设置在作用域中，以便后面页面调用
	        request.setAttribute("paging", paging);
	        request.setAttribute("list", list_page);
	        request.setAttribute("stuName",stuName);
	        request.setAttribute("couName",couName);
		}else{
			request.setAttribute("list", stuCous);
			request.setAttribute("stuName",stuName);
			request.setAttribute("couName",couName);
		}
		if(stuName.equals("")){
			request.setAttribute("flag","success");
		}else{
			request.setAttribute("flag","error");
		}
        rd = sc.getRequestDispatcher("/admin/findStuSelectCou.jsp");
		rd.forward(request, response);
	}
	
	private void findStuSelectCou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		List<Object> stuCous  = StuCouDao.findStuSelectCou();
		//页面当前页
        int page=0;
        //得到传过来的当前页
        String str_page=request.getParameter("page");
        // 创建分页的关于一些内容的工具bean
        Paging paging=new Paging();
        paging.setList(stuCous);//从数据库得到数据存入的list集合
        paging.setCount();//数据总数
        paging.setPagesize(8);//一个页面的数据多少条
        paging.setPagenumber();//总的页面数
        paging.setEndpage();//最后一页
        paging.setIndexpage(1);//第一页
        if (str_page!=null) {
            //将页转换整型判断其大小
            int pag=Integer.parseInt(str_page);
            //当大于零，将传过来的pag值赋给当前页page
            if (pag>=0) {
                page=pag;
                //如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
                if (pag>(paging.getPagenumber()-1)) {
                    page=pag-1;
                }
            }
        }
        paging.setPage(page);//最终确认当前页
        List<Object> list_page =new ArrayList<>();
        //将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<stuCous.size(); i++) {
            list_page.add(stuCous.get(i));
        }
		request.setAttribute("flag","success");
		request.setAttribute("stuName","");
		request.setAttribute("couName","");
        //将paging对象其设置在作用域中，以便后面页面调用
        request.setAttribute("paging", paging);
        request.setAttribute("list", list_page);
        System.out.println(request.getAttribute("list"));
        rd = sc.getRequestDispatcher("/admin/findStuSelectCou.jsp");
		rd.forward(request, response);
	}

	private void addCou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		HttpSession session = request.getSession(true);
		String couId = request.getParameter("couId");
		String stuId = request.getParameter("stuId");
		String flag="";
		StuCou stuCou =null;
		if((null!=couId&&!couId.equals(""))&&(null!=stuId&&!stuId.equals("")))
			stuCou = StuCouDao.findStuCou(stuId, couId);
			if(stuCou==null){
				flag = StuCouDao.addCou(stuId, couId);
				ShowLog showLog = new ShowLog(stuId, "选择课程", "学号:"+stuId+"，选择"+couId+"课程成功！");
				ShowLogDao.insertShowLog(showLog);
			}else{
				flag="repeat";
			}
		System.out.println(flag);
		if(flag.equals("success")){
			request.setAttribute("msg", "选课成功！");
		}else if(flag.equals("error")){
			request.setAttribute("msg", "选课失败！");
		}else if(flag.equals("repeat")){
			request.setAttribute("msg", "您已选择此课程，请勿重复选择！");
		}
		System.err.println(request.getAttribute("msg"));
		rd = sc.getRequestDispatcher("/student/msg.jsp");
		rd.forward(request, response);
        
	}
	
	private void findMyCou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		String stuId = request.getParameter("stuId");
		if(stuId!=null){
			List<Object> courses  = StuCouDao.getAllCourses(stuId);
			//页面当前页
	        int page=0;
	        //得到传过来的当前页
	        String str_page=    request.getParameter("page");
	        // 创建分页的关于一些内容的工具bean
	        Paging paging=new Paging();
	        paging.setList(courses);//从数据库得到数据存入的list集合
	        paging.setCount();//数据总数
	        paging.setPagesize(8);//一个页面的数据多少条
	        paging.setPagenumber();//总的页面数
	        paging.setEndpage();//最后一页
	        paging.setIndexpage(1);//第一页
	        if (str_page!=null) {
	            //将页转换整型判断其大小
	            int pag=Integer.parseInt(str_page);
	            //当大于零，将传过来的pag值赋给当前页page
	            if (pag>=0) {
	                page=pag;
	                //如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
	                if (pag>(paging.getPagenumber()-1)) {
	                    page=pag-1;
	                }
	            }
	        }
	        paging.setPage(page);//最终确认当前页
	        List<Object> list_page =new ArrayList<>();
	        //将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
	        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<courses.size(); i++) {
	            list_page.add(courses.get(i));
	        }
	        //将paging对象其设置在作用域中，以便后面页面调用
	        request.setAttribute("paging", paging);
	        request.setAttribute("list", list_page);
	        rd = sc.getRequestDispatcher("/student/findMyCou.jsp");
			rd.forward(request, response);
		}
		//request.setAttribute("courses", courses);
	}
	
	
	
	private void findMyCouOnly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		String stuId = request.getParameter("stuId");
		String couName = request.getParameter("couName");
		couName = MyTools.toChinese(couName);
		String teacher = request.getParameter("teacher");
		teacher = MyTools.toChinese(teacher);
		String submit = request.getParameter("submit");
		submit = MyTools.toChinese(submit);
		String cancel = request.getParameter("cancel");
		cancel = MyTools.toChinese(cancel);
		List<Object> courses = null;
		if(null!=cancel&&cancel.equals("取消")){
			courses = StuCouDao.getAllCourses(stuId);
			teacher ="";
			couName= "";
		}if(null!=submit&&submit.equals("查询")){
			courses = StuCouDao.findMyCouOnly(couName, teacher,stuId);
		}else{
			courses = StuCouDao.getAllCourses(stuId);
			teacher ="";
			couName= "";
		}
		//request.setAttribute("courses", courses);
		if(null!=courses&&courses.size()>0){
			//页面当前页
	        int page=0;
	        //得到传过来的当前页
	        String str_page=    request.getParameter("page");
	        // 创建分页的关于一些内容的工具bean
	        Paging paging=new Paging();
	        paging.setList(courses);//从数据库得到数据存入的list集合
	        paging.setCount();//数据总数
	        paging.setPagesize(8);//一个页面的数据多少条
	        paging.setPagenumber();//总的页面数
	        paging.setEndpage();//最后一页
	        paging.setIndexpage(1);//第一页
	        if (str_page!=null) {
	            //将页转换整型判断其大小
	            int pag=Integer.parseInt(str_page);
	            //当大于零，将传过来的pag值赋给当前页page
	            if (pag>=0) {
	                page=pag;
	                //如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
	                if (pag>(paging.getPagenumber()-1)) {
	                    page=pag-1;
	                }
	            }
	        }
	        paging.setPage(page);//最终确认当前页
	        List<Object> list_page =new ArrayList<>();
	        //将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
	        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<courses.size(); i++) {
	            list_page.add(courses.get(i));
	        }
	        //将paging对象其设置在作用域中，以便后面页面调用
	        request.setAttribute("paging", paging);
	        request.setAttribute("list", list_page);
	        request.setAttribute("couName", couName);
	        request.setAttribute("teacher", teacher);
		}else{
			request.setAttribute("list", courses);
			request.setAttribute("couName", couName);
	        request.setAttribute("teacher", teacher);
		}
        rd = sc.getRequestDispatcher("/student/findMyCou.jsp");
		rd.forward(request, response);
	}
	
	private void delCou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		HttpSession session = request.getSession(true);
		String couId = request.getParameter("couId");
		String stuId = request.getParameter("stuId");
		String flag="";
		if((null!=couId&&!couId.equals(""))&&(null!=stuId&&!stuId.equals("")))
			flag = StuCouDao.delCou(stuId, couId);
		ShowLog showLog = new ShowLog(stuId, "删除课程", "学号:"+stuId+"，删除"+couId+"课程成功！");
		ShowLogDao.insertShowLog(showLog);
		if(flag.equals("success")){
			request.setAttribute("msg", "删除成功！");
		}else if(flag.equals("error")){
			request.setAttribute("msg", "删除失败！");
		}
		rd = sc.getRequestDispatcher("/student/delCouMsg.jsp");
		rd.forward(request, response);
        
	}
}
