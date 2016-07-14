package Persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Bewoner;

public class BewonerDao {

	private ResultSet rs;
	private static final String DB_DRIV = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Huistaken-app";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	private static Connection conn;
	private static Statement stmt;
	private int id;
	private String voornaam;
	private String achternaam;
	private int kamerNummer;
	private boolean claimed;
	private int claim;
	
	
	public BewonerDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName(DB_DRIV).newInstance();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Bewoner getBewoner(int kN) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		String query = "select * from bewoners where kamerNummer = "+kN;
		rs = stmt.executeQuery(query);
		while(rs.next()){
			id = rs.getInt("id");
			voornaam = rs.getString("firstname");
			achternaam = rs.getString("lastname");
			kamerNummer = rs.getInt("kamerNummer");
			claim = rs.getInt("claim");
			if(claim == 0){
				claimed = false;
			}
			else{
				claimed = true;
			}
			
			
		}
		Bewoner b = new Bewoner(id, voornaam, achternaam, kamerNummer, claimed);
		rs.close();
		return b;
	}
	
	public Bewoner getBewonerById(int i) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		String query = "select * from bewoners where id = "+i;
		rs = stmt.executeQuery(query);
		while(rs.next()){
			id = rs.getInt("id");
			voornaam = rs.getString("firstname");
			achternaam = rs.getString("lastname");
			kamerNummer = rs.getInt("kamerNummer");
			claim = rs.getInt("claim");
			if(claim == 0){
				claimed = false;
			}
			else{
				claimed = true;
			}
			
		}
		Bewoner b = new Bewoner(id, voornaam, achternaam, kamerNummer, claimed);
		rs.close();
		return b;
	}
	
	public void updateBewoner(int id, String vN, String aN, int kN, boolean cl) throws SQLException{
		int cla;
		if(cl == false){
			cla = 0;
		}
		else{
			cla = 1;
		}
		String query = "update bewoners set FirstName = '"+vN+"', LastName = '"+aN+"', kamerNummer = "+kN+", claim = "+cla+" where id = "+id;
		stmt.executeUpdate(query);
	}
	
	public ArrayList<Bewoner> getAllBewoners() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList<Bewoner> bewoners = new ArrayList<Bewoner>();
		String query = "select * from bewoners order by id asc";
		rs = stmt.executeQuery(query);
		while(rs.next()){
			id = rs.getInt("id");
			voornaam = rs.getString("firstname");
			achternaam = rs.getString("lastname");
			kamerNummer = rs.getInt("kamerNummer");
			claim = rs.getInt("claim");
			if(claim == 0){
				claimed = false;
			}
			else{
				claimed = true;
			}
			bewoners.add(new Bewoner(id, voornaam, achternaam, kamerNummer, claimed));
			
		}
		return bewoners;
	}
	
	public void close() throws SQLException{
		stmt.close();
		conn.close();
	}
	
	
	
}
