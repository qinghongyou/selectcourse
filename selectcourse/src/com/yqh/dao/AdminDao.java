package com.yqh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yqh.beans.Admin;
import com.yqh.beans.Students;
import com.yqh.utils.DB;

public class AdminDao {
	
	public static Admin findAdmin(String admName, String admPwd){
	Admin adm = null;
	Connection conn = DB.GetConnection();
	try{
		String sql = "select * from sys_admin where admname='"+admName+"' and admpwd='"+admPwd + "'";
		PreparedStatement pstmt = DB.getStatement(conn, sql);
		ResultSet rst = DB.executeQuery(pstmt);
		while(rst.next()){
			int admid = rst.getInt("admid");
			String admname = rst.getString("admname");
			String admpwd = rst.getString("admpwd");
			adm = new Admin(admid, admname, admpwd);
		}
		rst.close();
        pstmt.close();
        conn.close();
	} catch(Exception e){
		e.printStackTrace();
	}
	return adm;
}

	public static Admin findMyInfo(String admId) {
		Admin adm = null;
		Connection conn = DB.GetConnection();
		try{
			String sql = "select * from sys_admin where admid='"+admId+ "'";
			PreparedStatement pstmt = DB.getStatement(conn, sql);
			ResultSet rst = DB.executeQuery(pstmt);
			while(rst.next()){
				String admid1 = rst.getString("admid");
				int admidInt = Integer.parseInt(admid1);
				String admname = rst.getString("admname");
				String admpwd = rst.getString("admpwd");
				adm = new Admin(admidInt, admname, admpwd);
			}
			rst.close();
            pstmt.close();
            conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return adm;
	}

	public static String updatePwd(String admId, String newPwd) {
		String flag="";
		try{
			String sql="update sys_admin set admpwd='"+newPwd+"' where admid='"+admId+"'";
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
