package com.yqh.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yqh.beans.ShowLog;
import com.yqh.beans.Students;
import com.yqh.dao.ShowLogDao;
import com.yqh.dao.StudentDao;
import com.yqh.utils.MyTools;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null)			
			action="";
		if(action.equals("logon")){
			this.isLogon(request,response);
		}
		if(action.equals("register")){
			this.register(request,response);
		}
		if(action.equals("findMyInfo")){
			this.findMyInfo(request,response);
		}
		if(action.equals("updatePwd")){
			this.updatePwd(request,response);
		}
	}
	
	protected void findMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		HttpSession session=request.getSession();
		String stuId = request.getParameter("stuId");
		Students stus = StudentDao.findMyInfo(stuId);
		System.out.println(stus.getStuId());
		String forward="";
		session.setAttribute("stus", stus);
		rd = sc.getRequestDispatcher("/student/myInfo.jsp");
		System.err.println(rd);
		rd.forward(request, response);
	}
	
	protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		HttpSession session=request.getSession();
		ArrayList<String> strList = new ArrayList<String>();
		String stuId = request.getParameter("stuId");
		String oldPwd = request.getParameter("oldPwd").trim();
		String newPwd = request.getParameter("newPwd").trim();
		String newPwd1 = request.getParameter("newPwd2").trim();
		System.out.println(oldPwd+newPwd+newPwd1);
		if(oldPwd.equals("")){
			strList.add("原密码为空！");
		}
		if(newPwd.equals("")){
			strList.add("新密码为空！");
		}
		if(newPwd1.equals("")){
			strList.add("确认新密码为空！");
		}
		if(null!=oldPwd&&!oldPwd.equals("")&&
				null!=newPwd&&!newPwd.equals("")&&
				null!=newPwd1&&!newPwd1.equals("")){
			Students stu = StudentDao.findMyInfo(stuId);
			if(!stu.getStuPwd().equals(oldPwd)){
				strList.add("原密码错误！");
			}
			if(!newPwd.equals(newPwd1)){
				strList.add("新密码与确认新密码不一致！");
			}
			if(stu.getStuPwd().equals(oldPwd)&&newPwd.equals(newPwd1)){
				String flag = StudentDao.updatePwd(stuId, newPwd);//updatePwdMsg.jsp
				ShowLog showLog = new ShowLog(stuId, "修改密码", "学号:"+stuId+"，修改密码成功！");
				ShowLogDao.insertShowLog(showLog);
				if(flag.equals("success")){
					request.setAttribute("msg", "修改密码成功！");
					rd = sc.getRequestDispatcher("/student/updatePwdMsg.jsp");
				}else if(flag.equals("error")){
					request.setAttribute("msg", "修改密码失败！");
					rd = sc.getRequestDispatcher("/student/updatePwdMsg.jsp");
				}
			}
		}
		
		String forward="";
		//session.setAttribute("stus", stus);
		System.out.println(strList.size());
		if(strList.size()>0){
			request.setAttribute("strList", strList);
			rd = sc.getRequestDispatcher("/student/updatePwd.jsp");
		}
		rd.forward(request, response);
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		ArrayList<String> strList = new ArrayList<String>();
		HttpSession session=request.getSession();
		String stuId = request.getParameter("stuId");
		String stuName = request.getParameter("stuName");
		stuName = MyTools.toChinese(stuName);
		String stuLoginName = request.getParameter("stuLoginName");
		String stuPwd = request.getParameter("stuPwd");
		String stuInstitute = request.getParameter("stuInstitute");
		stuInstitute = MyTools.toChinese(stuInstitute);
		String stuSex = request.getParameter("stuSex");
		stuSex = MyTools.toChinese(stuSex);
		if(stuId!=null&&stuName!=null&&stuLoginName!=null&&stuPwd!=null
				&&!stuId.equals("")&&!stuName.equals("")&&!stuLoginName.equals("")&&!stuPwd.equals("")){
			StudentDao stuDao = new StudentDao();
			Students stuInfo = stuDao.findMyInfo(stuId);
			if(stuInfo==null){
				Students stu = new Students(stuId, stuName, stuLoginName, stuInstitute, stuPwd, stuSex);
				System.out.println(stu);
				int i = stuDao.insertStudent(stu);
				ShowLog showLog = new ShowLog(stu.getStuId(), "注册", stu.getStuName()+"注册成功！");
				ShowLogDao.insertShowLog(showLog);
				String forward="";
				if(i==1){
					request.setAttribute("msg", "注册成功！");
					rd = sc.getRequestDispatcher("/student/success.jsp");
				}else{
					strList.add("注册失败！");
				}
			}else{
				strList.add("此学员已注册！");
			}
		}else{
			strList.add("注册失败！");
		}
		if(strList.size()>0){
			request.setAttribute("strList", strList);
			rd = sc.getRequestDispatcher("/student/register.jsp");
		}
		rd.forward(request, response);
	}
	
	public void isLogon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		HttpSession session=request.getSession();
		String stuLoginName = request.getParameter("stuLoginName");
		String stuPwd = request.getParameter("stuPwd");
		String yzm = request.getParameter("yzm").trim();
		StudentDao stuDao = new StudentDao();
		ArrayList<String> strList = new ArrayList<String>();
		String forward="";
		if(yzm.equals("X3D5S")){
			Students stus = stuDao.getLoginMsg(stuLoginName, stuPwd);
			if(null!=stus){
				session.setAttribute("stus", stus);
				ShowLog showLog = new ShowLog(stus.getStuId(), "登录系统", stus.getStuName()+"登录系统成功！");
				int i =ShowLogDao.insertShowLog(showLog);
				System.out.println(i);
				rd = sc.getRequestDispatcher("/student/main.jsp");
			}else{
				strList.add("用户名或密码错误！");
			}
		}else{
			strList.add("验证码错误！");
		}
		if(strList.size()>0){
			request.setAttribute("strList", strList);
			rd = sc.getRequestDispatcher("/student/login.jsp");
		}
		rd.forward(request, response);
	}

}
