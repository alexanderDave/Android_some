package daxigua.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @param dyh
 * basical util for connection to sql;
 */
public class DBUtil {

	
	
	public static Connection getCon(){
		Connection con = null;
		
			String sname = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://139.129.211.42:3306/test";
			String username = "Sys_admin";
			String pswd = "_!sysin";
		try{
			Class.forName(sname);
			con = DriverManager.getConnection(url, username, pswd);
//			System.out.println("con is on");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return con;
	}
	
	public static void close(Connection con){
		if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
