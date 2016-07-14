package Persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Huistaak;

public class HuistaakDao {

	private ResultSet rs;
	private static final String DB_DRIV = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Huistaken-app";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	private static Connection conn;
	private static Statement stmt;
	private String titel;
	private String omschrijving;
	private int id;

	
	public HuistaakDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName(DB_DRIV).newInstance();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Huistaak getHuistaak(int id) throws SQLException{
		String query = "select * from huistaken where huistaakId = "+id;
		rs = stmt.executeQuery(query);
		while(rs.next()){
			titel = rs.getString("titel");
			omschrijving = rs.getString("omschrijving");
			id = rs.getInt("huistaakId");
		}
		rs.close();
		return new Huistaak(titel, omschrijving, id);
	}
	
	public ArrayList<Huistaak> getHuistaken() throws SQLException{
		rs = stmt.executeQuery("select * from huistaken order by huistaakId asc");
		ArrayList<Huistaak> huistaken = new ArrayList<Huistaak>();
		while(rs.next()){
			titel = rs.getString("titel");
			omschrijving = rs.getString("omschrijving");
			id = rs.getInt("huistaakId");
			huistaken.add(new Huistaak(titel, omschrijving, id));
		}
		rs.close();
		return huistaken;
	}
	
	public void changeHuistaak(int id, String tit, String omsch) throws SQLException{
		String query = "update huistaken set titel = '"+tit+"', omschrijving = '"+omsch+"' where huistaakId = "+id;
		stmt.executeUpdate(query);
	}
//	
	public void close() throws SQLException{
		stmt.close();
		conn.close();
	}
	
}