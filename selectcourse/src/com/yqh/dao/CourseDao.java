package com.yqh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqh.beans.Course;
import com.yqh.beans.Students;
import com.yqh.utils.DB;

public class CourseDao {
	
	public static List<Object> getAllCourses(){
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from sys_courses";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					Course course = null;
					String couId = rst.getString("couid");
					String couName = rst.getString("couname");
					String teacher = rst.getString("teacher");
					int credit = rst.getInt("credit");
					String courseDes = rst.getString("couexp");
					course = new Course(couId, couName, teacher, courseDes, credit);
					list.add(course);
				}
			}
			rst.close();
            pstmt.close();
            conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public static List<Object> findCourse(String couName, String teacher){
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from sys_courses where 1=1 ";
			if(couName!=null&&!couName.equals("")){
				sql=sql+" and couname like '%"+couName+"%'";
			}
			if(teacher!=null&&!teacher.equals("")){
				sql=sql+" and teacher like '%"+teacher+"%'";
			}
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			System.out.println(sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					Course course = null;
					String couId = rst.getString("couid");
					String couName1 = rst.getString("couname");
					String teacher1 = rst.getString("teacher");
					int credit = rst.getInt("credit");
					String courseDes = rst.getString("couexp");
					course = new Course(couId, couName1, teacher1, courseDes, credit);
					list.add(course);
				}
			}
			rst.close();
            pstmt.close();
            conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public static Course findMyInfo(String couid) {
		Course cou = null;
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from sys_courses where couid='"+couid+ "'";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			ResultSet rst = DB.executeQuery(pstmt);
			while(rst.next()){
				String couId = rst.getString("couid");
				String couName1 = rst.getString("couname");
				String teacher1 = rst.getString("teacher");
				int credit = rst.getInt("credit");
				String courseDes = rst.getString("couexp");
				cou = new Course(couId, couName1, teacher1, courseDes, credit);
			}
			rst.close();
            pstmt.close();
            conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return cou;
	}
	public static int insertStudent(Course cou) {
		Connection conn = DB.GetConnection();
		try{
			String sql = "insert into sys_courses values('"+
					cou.getCouId()+"','"+
					cou.getCouName()+"','"+
					cou.getTeacher()+"',"+
					cou.getCredit()+",'"+
					cou.getCourseDes()+"')";
			System.out.println(sql);
            DB.executeUpdate(sql);
            return 1;
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public static String updateCou(Course cou) {
		String flag="";
		try{
			String sql="update sys_courses set couname='"+cou.getCouName()+"', teacher='"
					+cou.getTeacher()+"', credit="
					+cou.getCredit()+", couexp='"
					+cou.getCourseDes()+"'  where couid='"+cou.getCouId()+"'";
			System.out.println(sql);
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
	public static String delCouId(String couId) {
		String flag="";
		try{
			String sql="delete from sys_courses where couid='"+couId+"'";
			System.out.println(sql);
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
