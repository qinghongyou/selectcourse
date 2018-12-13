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

import com.yqh.beans.Admin;
import com.yqh.beans.ShowLog;
import com.yqh.beans.Students;
import com.yqh.dao.AdminDao;
import com.yqh.dao.ShowLogDao;
import com.yqh.dao.StudentDao;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServlet() {
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
		if(action.equals("updatePwd")){
			this.updatePwd(request,response);
		}
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		HttpSession session=request.getSession();
		ArrayList<String> strList = new ArrayList<String>();
		String admId = request.getParameter("admId");
		String oldPwd = request.getParameter("oldPwd").trim();
		String newPwd = request.getParameter("newPwd").trim();
		String newPwd1 = request.getParameter("newPwd2").trim();
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
			Admin adm = AdminDao.findMyInfo(admId);
			if(!adm.getAdmPwd().equals(oldPwd)){
				strList.add("原密码错误！");
			}
			if(!newPwd.equals(newPwd1)){
				strList.add("新密码与确认新密码不一致！");
			}
			if(adm.getAdmPwd().equals(oldPwd)&&newPwd.equals(newPwd1)){
				String flag = AdminDao.updatePwd(admId, newPwd);//updatePwdMsg.jsp
				ShowLog showLog = new ShowLog(admId, "修改密码", "Admin修改密码成功！");
				ShowLogDao.insertShowLog(showLog);
				if(flag.equals("success")){
					request.setAttribute("msg", "修改密码成功！");
					rd = sc.getRequestDispatcher("/admin/success.jsp");
				}else if(flag.equals("error")){
					request.setAttribute("msg", "修改密码失败！");
					rd = sc.getRequestDispatcher("/admin/success.jsp");
				}
			}
		}
		
		String forward="";
		if(strList.size()>0){
			request.setAttribute("strList", strList);
			rd = sc.getRequestDispatcher("/admin/updatePwd.jsp");
		}
		rd.forward(request, response);
	}
	
	public void isLogon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = null;
		HttpSession session=request.getSession();
		String admName = request.getParameter("admName").trim();
		String admPwd = request.getParameter("admPwd").trim();
		ArrayList<String> strList = new ArrayList<String>();
		String forward="";
		if((null!=admName&&!admName.equals(""))||(null!=admPwd&&!admPwd.equals(""))){
			AdminDao admDao = new AdminDao();
			Admin adms = admDao.findAdmin(admName, admPwd);
			if(null!=adms){
				session.setAttribute("adms", adms);
				ShowLog showLog = new ShowLog(adms.getAdmId()+"", "登录系统", adms.getAdmName()+"登录系统成功！");
				int i =ShowLogDao.insertShowLog(showLog);
				System.out.println(i);
				rd = sc.getRequestDispatcher("/admin/main.jsp");
			}else{
				strList.add("用户名或密码错误！");
			}
		}else{
			strList.add("用户名或密码不能为空！");
		}
		if(strList.size()>0){
			request.setAttribute("strList", strList);
			rd = sc.getRequestDispatcher("/admin/login.jsp");
		}
		rd.forward(request, response);
	}

}
