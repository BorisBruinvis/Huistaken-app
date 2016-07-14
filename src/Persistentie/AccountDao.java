package Persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Model.Account;

public class AccountDao {

	private ResultSet rs;
	private static final String DB_DRIV = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Huistaken-app";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	private static Connection conn;
	private static Statement stmt;
	private String email;
	private String wachtwoord;
	private int id;

	
	public AccountDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName(DB_DRIV).newInstance();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Account getAccount(String em) throws SQLException{
		String query = "select * from account where email = '"+em+"'";
		rs = stmt.executeQuery(query);
		while(rs.next()){
			email = rs.getString("email");
			wachtwoord = rs.getString("wachtwoord");
			id = rs.getInt("bewonerid");
		}
		rs.close();
		return new Account(email, wachtwoord, id);
	}
	
	public Account getAccount(int i) throws SQLException{
		String query = "select * from account where BewonerId = "+i;
		rs = stmt.executeQuery(query);
		while(rs.next()){
			email = rs.getString("email");
			wachtwoord = rs.getString("wachtwoord");
			id = rs.getInt("bewonerid");
		}
		rs.close();
		return new Account(email, wachtwoord, id);
	}
	
	public void createAccount(String email, String wachtwoord, int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			stmt.executeUpdate("insert into account values('"+email+"', '"+wachtwoord+"', "+id+");");
	}
	
	public void updateAccount(String em, int id) throws SQLException{
		String query = "update account set email = '"+em+"' where bewonerid = "+id;
		stmt.executeUpdate(query);
		
	}
	
	public void removeAccount(int id) throws SQLException{
		String query = "delete from account where bewonerId = "+id;
		stmt.executeUpdate(query);
	}
	
	public void close() throws SQLException{
		stmt.close();
		conn.close();
	}
	
}