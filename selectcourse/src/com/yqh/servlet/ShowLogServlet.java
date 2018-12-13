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

import com.yqh.beans.ShowLog;
import com.yqh.dao.CourseDao;
import com.yqh.dao.ShowLogDao;
import com.yqh.dao.StuCouDao;
import com.yqh.utils.MyTools;
import com.yqh.utils.Paging;

/**
 * Servlet implementation class ShowLogServlet
 */
@WebServlet("/ShowLogServlet")
public class ShowLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowLogServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action==null)			
			action="";
		if(action.equals("findHis")){
			this.findHis(request,response);
		}
		if(action.equals("findHisAll")){
			this.findHisAll(request,response);
		}
		if(action.equals("findHisAllWhere")){
			this.findHisAllWhere(request,response);
		}
		if(action.equals("findHisAdm")){
			this.findHisAdm(request,response);
		}
	}
	
	private void findHisAdm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		String stuId = request.getParameter("stuId");
		if(stuId!=null){
			List<Object> showLogs  = ShowLogDao.findHisAdm(stuId);
			//页面当前页
	        int page=0;
	        //得到传过来的当前页
	        String str_page=    request.getParameter("page");
	        // 创建分页的关于一些内容的工具bean
	        Paging paging=new Paging();
	        paging.setList(showLogs);//从数据库得到数据存入的list集合
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
	        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<showLogs.size(); i++) {
	            list_page.add(showLogs.get(i));
	        }
	        //将paging对象其设置在作用域中，以便后面页面调用
	        request.setAttribute("paging", paging);
	        request.setAttribute("list", list_page);
	        rd = sc.getRequestDispatcher("/admin/admhistory.jsp");
			rd.forward(request, response);
		}
		//request.setAttribute("courses", courses);
	}
	
	private void findHisAllWhere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		String stuName = request.getParameter("stuName").trim();
		stuName = MyTools.toChinese(stuName);
		String submit = request.getParameter("submit");
		submit = MyTools.toChinese(submit);
		String cancel = request.getParameter("cancel");
		cancel = MyTools.toChinese(cancel);
		List<Object> showLogs = null;
		if(null!=cancel&&cancel.equals("取消")){
			showLogs  = ShowLogDao.findHisAll();
			stuName ="";
		}if((null!=submit&&submit.equals("查询"))){
			showLogs = ShowLogDao.findHisAllWhere(stuName);
		}else{
			showLogs = ShowLogDao.findHisAll();
			stuName ="";
		}
		if(null!=showLogs&&showLogs.size()>0){
			//页面当前页
	        int page=0;
	        //得到传过来的当前页
	        String str_page= request.getParameter("page");
	        // 创建分页的关于一些内容的工具bean
	        Paging paging=new Paging();
	        paging.setList(showLogs);//从数据库得到数据存入的list集合
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
	        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<showLogs.size(); i++) {
	            list_page.add(showLogs.get(i));
	        }
	        //将paging对象其设置在作用域中，以便后面页面调用
	        request.setAttribute("paging", paging);
	        request.setAttribute("list", list_page);
	        request.setAttribute("stuName",stuName);
		}else{
			request.setAttribute("list", showLogs);
			request.setAttribute("stuName",stuName);
		}
		if(stuName.equals("")){
			request.setAttribute("flag","success");
		}else{
			request.setAttribute("flag","error");
		}
		System.out.println(request.getAttribute("flag"));
        rd = sc.getRequestDispatcher("/admin/history.jsp");
		rd.forward(request, response);
	}
	
	private void findHisAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		List<Object> showLogs  = ShowLogDao.findHisAll();
		//页面当前页
        int page=0;
        //得到传过来的当前页
        String str_page=    request.getParameter("page");
        // 创建分页的关于一些内容的工具bean
        Paging paging=new Paging();
        paging.setList(showLogs);//从数据库得到数据存入的list集合
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
        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<showLogs.size(); i++) {
            list_page.add(showLogs.get(i));
        }
		request.setAttribute("flag","success");
		request.setAttribute("stuName","");
		System.out.println(request.getAttribute("flag"));
        //将paging对象其设置在作用域中，以便后面页面调用
        request.setAttribute("paging", paging);
        request.setAttribute("list", list_page);
        rd = sc.getRequestDispatcher("/admin/history.jsp");
		rd.forward(request, response);
	}
	
	private void findHis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ServletContext sc = this.getServletContext();
		String stuId = request.getParameter("stuId");
		if(stuId!=null){
			List<Object> showLogs  = ShowLogDao.findHis(stuId);
			//页面当前页
	        int page=0;
	        //得到传过来的当前页
	        String str_page=    request.getParameter("page");
	        // 创建分页的关于一些内容的工具bean
	        Paging paging=new Paging();
	        paging.setList(showLogs);//从数据库得到数据存入的list集合
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
	        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<showLogs.size(); i++) {
	            list_page.add(showLogs.get(i));
	        }
	        //将paging对象其设置在作用域中，以便后面页面调用
	        request.setAttribute("paging", paging);
	        request.setAttribute("list", list_page);
	        rd = sc.getRequestDispatcher("/student/history.jsp");
			rd.forward(request, response);
		}
		//request.setAttribute("courses", courses);
	}

}
