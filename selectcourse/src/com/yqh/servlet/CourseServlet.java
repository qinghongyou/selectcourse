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

import com.yqh.beans.Course;
import com.yqh.beans.ShowLog;
import com.yqh.beans.Students;
import com.yqh.dao.CourseDao;
import com.yqh.dao.ShowLogDao;
import com.yqh.dao.StuCouDao;
import com.yqh.dao.StudentDao;
import com.yqh.utils.MyTools;
import com.yqh.utils.Paging;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null)			
			action="";
		if(action.equals("selectAll")){
			this.isSelectAll(request,response);
		}
		if(action.equals("findCourse")){
			this.findCourse(request,response);
		}
		if(action.equals("findAllAdm")){
			this.findAllAdm(request,response);
		}
		if(action.equals("findAllAdmWhere")){
			this.findAllAdmWhere(request,response);
		}
		if(action.equals("addCou")){
			this.addCou(request,response);
		}
		if(action.equals("findCouId")){
			this.findCouId(request,response);
		}
		if(action.equals("updateCou")){
			this.updateCou(request,response);
		}
		if(action.equals("delCouId")){
			this.delCouId(request,response);
		}
	}
	
	private void delCouId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		HttpSession session = request.getSession(true);
		String couId = request.getParameter("couId");
		String flag="";
		flag = CourseDao.delCouId(couId);
		ShowLog showLog = new ShowLog("1", "删除课程", "Admin删除课程编号"+couId+"的课程成功！");
		ShowLogDao.insertShowLog(showLog);
		System.out.println(flag);
		if(flag.equals("success")){
			request.setAttribute("msg", "删除课程成功！");
		}else if(flag.equals("error")){
			request.setAttribute("msg", "删除课程失败！");
		}
		rd = sc.getRequestDispatcher("/admin/success.jsp");
		rd.forward(request, response);
        
	}
	
	/**
	 * 修改课程信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		HttpSession session=request.getSession();
		ArrayList<String> strList = new ArrayList<String>();
		String couId = request.getParameter("couId");
		String couname = request.getParameter("couname").trim();
		String teacher = request.getParameter("teacher").trim();
		String couexp = request.getParameter("couexp").trim();
		String credit = request.getParameter("credit").trim();
		credit= !credit.equals("")?credit:"0";
		int creditInt = Integer.parseInt(credit);
		couname = MyTools.toChinese(couname);
		teacher = MyTools.toChinese(teacher);
		couexp = MyTools.toChinese(couexp);
		if(couname!=null&&!couname.equals("")){
			strList.add("课程名称不能为空！");
		}
		if(teacher!=null&&!teacher.equals("")){
			strList.add("老师不能为空！");
		}
		if(couId!=null&&couname!=null&&teacher!=null&&credit!=null
				&&!couId.equals("")&&!couname.equals("")&&!teacher.equals("")&&!couexp.equals("")){
			Course cou = new Course(couId, couname, teacher, couexp, creditInt);
			String flag = CourseDao.updateCou(cou);
			ShowLog showLog = new ShowLog("1", "修改课程", "Admin修改课程《"+cou.getCouName()+"》成功！");
			ShowLogDao.insertShowLog(showLog);
			if(flag.equals("success")){
				request.setAttribute("msg", "修改课程！");
				rd = sc.getRequestDispatcher("/admin/success.jsp");
			}else if(flag.equals("error")){
				request.setAttribute("msg", "修改课程失败！");
				rd = sc.getRequestDispatcher("/admin/success.jsp");
			}
		}
		rd.forward(request, response);
	}
	
	/**
	 * 根据课程Id查询课程信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findCouId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		HttpSession session=request.getSession();
		String couid = request.getParameter("couId");
		Course cous = CourseDao.findMyInfo(couid);
		String forward="";
		session.setAttribute("cous", cous);
		System.out.println(cous);
		rd = sc.getRequestDispatcher("/admin/courseInfo.jsp");
		rd.forward(request, response);
	}
	
	private void addCou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		ArrayList<String> strList = new ArrayList<String>();
		HttpSession session=request.getSession();
		String couid = request.getParameter("couid").trim();
		String couname = request.getParameter("couname").trim();
		String teacher = request.getParameter("teacher").trim();
		String couexp = request.getParameter("couexp").trim();
		String credit = request.getParameter("credit").trim();
		credit= !credit.equals("")?credit:"0";
		int creditInt = Integer.parseInt(credit);
		couname = MyTools.toChinese(couname);
		teacher = MyTools.toChinese(teacher);
		couexp = MyTools.toChinese(couexp);
		if(couid!=null&&couname!=null&&teacher!=null&&credit!=null
				&&!couid.equals("")&&!couname.equals("")&&!teacher.equals("")&&!couexp.equals("")){
			StudentDao stuDao = new StudentDao();
			Course couInfo = CourseDao.findMyInfo(couid);
			if(couInfo==null){
				Course cou = new Course(couid, couname, teacher, couexp, creditInt);
				int i = CourseDao.insertStudent(cou);
				ShowLog showLog = new ShowLog("1", "新建课程", "新建课程《"+cou.getCouName()+"》成功！");
				ShowLogDao.insertShowLog(showLog);
				String forward="";
				if(i==1){
					request.setAttribute("msg", "新建课程成功！");
					rd = sc.getRequestDispatcher("/admin/success.jsp");
				}else{
					strList.add("新建课程失败！");
				}
			}else{
				strList.add("此课程已添加！");
			}
		}else{
			strList.add("新建课程失败！");
		}
		System.out.println(strList.size());
		if(strList.size()>0){
			request.setAttribute("strList", strList);
			rd = sc.getRequestDispatcher("/admin/addCou.jsp");
		}
		rd.forward(request, response);
	}
	
	
	private void findAllAdm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		List<Object> courses = CourseDao.getAllCourses();
		//request.setAttribute("courses", courses);
		
		//页面当前页
        int page=0;
        //得到传过来的当前页
        String str_page= request.getParameter("page");
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
        rd = sc.getRequestDispatcher("/admin/right.jsp");
		rd.forward(request, response);
	}
	
	private void findAllAdmWhere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
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
			courses = CourseDao.getAllCourses();
			teacher ="";
			couName= "";
		}if(null!=submit&&submit.equals("查询")){
			courses = CourseDao.findCourse(couName, teacher);
		}else{
			courses = CourseDao.getAllCourses();
			teacher ="";
			couName= "";
		}
		//request.setAttribute("courses", courses);
		if(null!=courses&&courses.size()>0){
			//页面当前页
	        int page=0;
	        //得到传过来的当前页
	        String str_page= request.getParameter("page");
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
        rd = sc.getRequestDispatcher("/admin/right.jsp");
		rd.forward(request, response);
	}
	
	private void isSelectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		List<Object> courses = CourseDao.getAllCourses();
		//request.setAttribute("courses", courses);
		
		//页面当前页
        int page=0;
        //得到传过来的当前页
        String str_page= request.getParameter("page");
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
        rd = sc.getRequestDispatcher("/student/right.jsp");
		rd.forward(request, response);
	}
	
	private void findCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
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
			courses = CourseDao.getAllCourses();
			teacher ="";
			couName= "";
		}if(null!=submit&&submit.equals("查询")){
			courses = CourseDao.findCourse(couName, teacher);
		}else{
			courses = CourseDao.getAllCourses();
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
        rd = sc.getRequestDispatcher("/student/right.jsp");
		rd.forward(request, response);
	}

}
