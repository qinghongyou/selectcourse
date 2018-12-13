package com.yqh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yqh.beans.Course;
import com.yqh.beans.ShowLog;
import com.yqh.utils.DB;
import com.yqh.utils.MyTools;

public class ShowLogDao {
	
	public static int insertShowLog(ShowLog log){
		Connection conn = DB.GetConnection();
		Date date = new Date();
		String dateStr = MyTools.changeTime(date);
		log.setLogDate(dateStr);
		log.setUserName("");
		String sql1 = "select count(*) from sys_showlog where 1=1";
        int count = 0;
		try{
			// 计算数据库student表中数据总数
        	PreparedStatement pstmt = DB.getStatement(conn, sql1);
        	ResultSet rst = DB.executeQuery(pstmt);
            while (rst.next()) {
                count = rst.getInt(1)+1;
            }
            log.setLogId(count);
			String sql = "insert into sys_showlog values('"+
					log.getLogId()+"','"+
					log.getUserId()+"','"+
					log.getUserName()+"','"+
					log.getDoing()+"','"+
					log.getMsg()+"','"+
					log.getLogDate()+"')";
            int i = DB.executeUpdate(sql);
            rst.close();
            pstmt.close();
            conn.close();
            return 1;
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static List<Object> findHis(String stuId){
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select l.logid,s.stuname,l.doing,l.msg,l.logdate "
					+ " from sys_showlog l,sys_students s "
					+ " where s.stuid=l.stuid and l.stuid='"+stuId+"' order by l.logid DESC";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			System.out.println(sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					ShowLog showLog = null;
					int logid = rst.getInt(1);
					String username = rst.getString(2);
					String doing = rst.getString(3);
					String msg = rst.getString(4);
					String logdate = rst.getString(5);
					showLog = new ShowLog(logid, username, doing, msg, logdate);
					list.add(showLog);
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


	public static List<Object> findHisAll() {
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select l.logid,s.stuname,l.doing,l.msg,l.logdate "
					+ " from sys_showlog l,sys_students s "
					+ " where s.stuid=l.stuid and s.stuid!='"+1+"' order by l.logid DESC";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			System.out.println(sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					ShowLog showLog = null;
					int logid = rst.getInt(1);
					String username = rst.getString(2);
					String doing = rst.getString(3);
					String msg = rst.getString(4);
					String logdate = rst.getString(5);
					showLog = new ShowLog(logid, username, doing, msg, logdate);
					list.add(showLog);
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


	public static List<Object> findHisAllWhere(String stuName) {
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select l.logid,s.stuname,l.doing,l.msg,l.logdate "
					+ " from sys_showlog l,sys_students s "
					+ " where s.stuid=l.stuid and s.stuid!='"+1+"'"
					+ " and s.stuname like '%"+stuName+"%'"
					+ " order by l.logid DESC";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			System.out.println(sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					ShowLog showLog = null;
					int logid = rst.getInt(1);
					String username = rst.getString(2);
					String doing = rst.getString(3);
					String msg = rst.getString(4);
					String logdate = rst.getString(5);
					showLog = new ShowLog(logid, username, doing, msg, logdate);
					list.add(showLog);
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


	public static List<Object> findHisAdm(String stuId) {
		List<Object> list = new ArrayList<Object>();
		Connection conn = DB.GetConnection();
		try{
			String sql = "select l.logid,s.admname,l.doing,l.msg,"
					+ " l.logdate from sys_showlog l,sys_admin s "
					+ " where s.admid=l.stuid and l.stuid='"+stuId+"' order by l.logid DESC";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			System.out.println(sql);
			ResultSet rst = DB.executeQuery(pstmt);
			if(null!=rst){
				while(rst.next()){
					ShowLog showLog = null;
					int logid = rst.getInt(1);
					String username = rst.getString(2);
					String doing = rst.getString(3);
					String msg = rst.getString(4);
					String logdate = rst.getString(5);
					showLog = new ShowLog(logid, username, doing, msg, logdate);
					list.add(showLog);
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
	

}
