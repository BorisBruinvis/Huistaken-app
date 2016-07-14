package Persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Model.Week;

public class WeekDao {

	private ResultSet rs;
	private static final String DB_DRIV = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Huistaken-app";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	private static Connection conn;
	private static Statement stmt;
	int id;
	int lock;
	
	
	public WeekDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName(DB_DRIV).newInstance();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Week getWeek(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		rs = stmt.executeQuery("select * from Week where id = "+id);
		while(rs.next()){
			id = rs.getInt("id");
		}
		return new Week(id);
	}
	

	public void close() throws SQLException{
		rs.close();
		stmt.close();
		conn.close();
	}
	
	
	
}
