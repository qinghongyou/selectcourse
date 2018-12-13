package com.yqh.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yqh.beans.Students;
import com.yqh.utils.DB;

/**
 * 学生有关的数据操作
 * @author xiaoyou
 *
 */
public class StudentDao {
	
	public static Students getLoginMsg(String stuLoginName, String stuPwd){
		Students stu = null;
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from sys_students where stuloginname='"+stuLoginName+"' and stupwd='"+stuPwd + "'";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			ResultSet rst = DB.executeQuery(pstmt);
			while(rst.next()){
				String stuId = rst.getString("stuid");
				String stuName = rst.getString("stuname");
				String stuloginname = rst.getString("stuloginname");
				String stupwd = rst.getString("stupwd");
				String stuSex = rst.getString("stusex");
				String stuInstitute = rst.getString("stuinstitute");
				stu = new Students(stuId, stuName, stuloginname, stuInstitute, stupwd, stuSex);
			}
			rst.close();
            pstmt.close();
            conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return stu;
	}
	
	public static int insertStudent(Students stu){
		Connection conn = DB.GetConnection();
		try{
			String sql = "insert into sys_students values('"+
					stu.getStuId()+"','"+
					stu.getStuName()+"','"+
					stu.getStuPwd()+"','"+
					stu.getStuSex()+"','"+
					stu.getStuInstitute()+"','"+
					stu.getStuLoginName()+"')";
			/*PreparedStatement pstmt = DB.getStatement(conn, sql);
			pstmt.setString(1, stu.getStuId());
            pstmt.setString(2, stu.getStuName());
            pstmt.setString(3, stu.getStuPwd());
            pstmt.setString(4, stu.getStuSex());
            pstmt.setString(5, stu.getStuInstitute());
            pstmt.setString(6, stu.getStuLoginName());*/
            DB.executeUpdate(sql);
            return 1;
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 查询学生信息
	 * @param stuId
	 * @return
	 */
	public static Students findMyInfo(String stuId){
		Students stu = null;
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from sys_students where stuid='"+stuId+ "'";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			ResultSet rst = DB.executeQuery(pstmt);
			while(rst.next()){
				String stuId1 = rst.getString("stuid");
				String stuName = rst.getString("stuname");
				String stuloginname = rst.getString("stuloginname");
				String stupwd = rst.getString("stupwd");
				String stuSex = rst.getString("stusex");
				String stuInstitute = rst.getString("stuinstitute");
				stu = new Students(stuId1, stuName, stuloginname, stuInstitute, stupwd, stuSex);
			}
			rst.close();
            pstmt.close();
            conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return stu;
	}
	
	public static String updatePwd(String stuId, String newPwd) {
		String flag="";
		try{
			String sql="update sys_students set stupwd='"+newPwd+"' where stuid='"+stuId+"'";
			int val = DB.executeUpdate(sql);
			if(val==1){
				flag="success";
			}else{
				flag="error";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	

}
