package com.yqh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqh.beans.Course;
import com.yqh.beans.ShowLog;
import com.yqh.beans.StuCou;
import com.yqh.utils.DB;
import com.yqh.utils.StuCous;

public class StuCouDao{
	
	public StuCouDao(){
	}
	
	/**
	 * 学生选课方法
	 * @param stuId
	 * @param couId
	 * @return
	 */
	public static String addCou(String stuId, String couId){
		String flag="";
		try{
			String sql="insert into sys_stucou values('"+stuId+"',"+"'"+couId+"')";
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
	
	/**
	 * 查询学生是否选过这门课
	 */
	public static StuCou findStuCou(String stuId, String couId){
		StuCou stuCou = null;
		Connection conn = DB.GetConnection();
		String strSql = "select * from sys_stucou where stuid='"+stuId+"' and couid='"+couId+"'";
		PreparedStatement pstmt = DB.getStatement(conn, strSql);
		ResultSet rst = DB.executeQuery(pstmt);
		try {
			while (rst.next()) {
				String stuId1 = rst.getString("stuid");
				String couId1 = rst.getString("couid");
				stuCou= new StuCou(stuId1,couId1);
			}
			rst.close();
            pstmt.close();
            conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stuCou;
	}
	
	/**
	 * 查询学生选过
	 */
	public static List<Object> getAllCourses(String stuId){
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from SYS_COURSES where COUID in(select couid from sys_stucou where stuid='"+stuId+"')";
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
	
	public static List<Object> findMyCouOnly(String couName, String teacher,String stuId){
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from sys_courses where COUID in(select couid from sys_stucou where stuid='"+stuId+"')";
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
	
	/**
	 * 删除课程
	 * @param stuId
	 * @param couId
	 * @return
	 */
	public static String delCou(String stuId, String couId){
		String flag="";
		try{
			String sql="delete from sys_stucou where stuid='"+stuId+"' and couid='"+couId+"'";
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

	public static List<Object> findStuSelectCou() {
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select s.stuid,s.stuname,"
					+ " c.couname,c.teacher,c.couexp "
					+ " from sys_stucou sc, sys_students s,sys_courses c "
					+ " where s.stuid = sc.stuid and c.couid=sc.couid";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			System.out.println(sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					StuCous stuCous = null;
					String stuid = rst.getString(1);
					String stuname = rst.getString(2);
					String couname = rst.getString(3);
					String teacher = rst.getString(4);
					String couexp = rst.getString(5);
					stuCous = new StuCous(stuid, stuname, couname, teacher, couexp);
					list.add(stuCous);
				}
			}
			if(null!=rst&&!rst.isClosed())
				rst.close();
			if(null!=pstmt&&!pstmt.isClosed())
				pstmt.close();
			if(null!=conn&&!conn.isClosed())
				conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<Object> findStuSelectCouWhere(String stuName,
			String couName) {
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select s.stuid,s.stuname,"
					+ " c.couname,c.teacher,c.couexp "
					+ " from sys_stucou sc, sys_students s,sys_courses c "
					+ " where s.stuid = sc.stuid and c.couid=sc.couid ";
			
			if(couName!=null&&!couName.equals("")){
				sql=sql+" and c.couname like '%"+couName+"%'";
			}
			if(stuName!=null&&!stuName.equals("")){
				sql=sql+" and s.stuname like '%"+stuName+"%'";
			}
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			System.out.println(sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					StuCous stuCous = null;
					String stuid = rst.getString(1);
					String stuname = rst.getString(2);
					String couname = rst.getString(3);
					String teacher = rst.getString(4);
					String couexp = rst.getString(5);
					stuCous = new StuCous(stuid, stuname, couname, teacher, couexp);
					list.add(stuCous);
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
}
