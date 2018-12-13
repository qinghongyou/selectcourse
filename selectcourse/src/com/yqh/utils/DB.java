package com.yqh.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {
	
	// 定义连接所需的字符串
    // 127.0.0.1是本机地址(要改成自己的IP地址)，1521端口号，XE是精简版Oracle的默认数据库名
	private static String DRVIER = "oracle.jdbc.OracleDriver"; // Oracle 驱动
	private static String URL = "jdbc:oracle:thin:@127.0.0.1:1522:oracle"; // URL
    private static String USERNAMR = "System"; // Oracle用户名
    private static String PASSWORD = "123456"; // 密码

    /**
     * 获取数据库连接，返回Connection 对象
     * @return
     */
    public static Connection GetConnection(){
    	Connection conn = null;
    	try{
    		// 这里使用Class.forName() 方法创建驱动程序的实例并且自动调用DriverManager 对其注册
    		Class.forName(DRVIER).newInstance();
    	} catch(InstantiationException e){
    		e.printStackTrace();
    	} catch(IllegalAccessException e){
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	try{
    		// 通过DriverManager 获取数据库连接
    		conn = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
    	} catch(SQLException e){
    		e.printStackTrace();
    	}
    	return conn;
    }
    
    /**
     * 关闭连接
     * @param conn
     */
    public static void close(Connection conn){
    	try{
    		if(conn != null && !conn.isClosed() )
    			conn.close();
    	} catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 得到PreparedStatement 对象
     * @param conn
     * @param sql
     * @return
     */
    public static PreparedStatement getStatement(Connection conn, String strsql){
    	if(strsql == null || "".equals(strsql) ){
    		System.out.println("SQL 为空……");
    		return null;
    	}
    	if(conn == null){
    		System.out.println("连接为空……");
    		return null;
    	}
    	
    	try{
    		return conn.prepareStatement(strsql,  // 预编译语句得到PreparedStatement 对象
    				ResultSet.TYPE_SCROLL_INSENSITIVE,
    				ResultSet.CONCUR_UPDATABLE);
    	} catch(SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * 得到 ResultSet
     * @param pstmt
     * @return
     */
    public static ResultSet executeQuery(PreparedStatement pstmt){
    	try{
    		if(pstmt!=null)
    			return pstmt.executeQuery(); // 查询
    	} catch(SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * 执行增、删、改的操作
     * @param sql
     * @return
     */
    public static int executeUpdate(String sql){
    	int returnvalue = 0;
    	Connection conn = DB.GetConnection();
    	try{
    		Statement stmt = conn.createStatement();
    		returnvalue = stmt.executeUpdate(sql);
    		return returnvalue;
    	} catch(SQLException e){
    		System.out.println(e.getMessage());
    		return -1;
    	}
    }
    
    /**
     * 关闭Statement 对象
     * @param stmt
     */
    public static void close(Statement stmt){
    	try{
    		if(stmt!=null) {
    			stmt.close();
    		}
    	} catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 关闭结果集
     * @param rs
     */
    public static void close(ResultSet rs){
    	try{
    		if(rs!=null){
    			rs.close();
    		}
    	} catch(SQLException e){
			e.printStackTrace();
		}
    }
   
}
