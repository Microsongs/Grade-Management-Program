package Frames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_to {
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/score1?characterEncoding=UTF-8&serverTimezone=UTC";
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB 연결중");
			con = DriverManager.getConnection(url, "root", "********");
			System.out.println("DB 연결 성공");
			
		}catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		return con;
	}
}