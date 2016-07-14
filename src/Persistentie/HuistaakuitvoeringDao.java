package Persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Bewoner;
import Model.Huistaak;
import Model.Huistaakuitvoering;

public class HuistaakuitvoeringDao {

	private ResultSet rs;
	private static final String DB_DRIV = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Huistaken-app";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	private static Connection conn;
	private static Statement stmt;


	
	public HuistaakuitvoeringDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName(DB_DRIV).newInstance();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Huistaakuitvoering getHuistaakuitvoering(int i) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		rs = stmt.executeQuery("select * from huistaken where id = "+i);
		ArrayList<Huistaak> huistaken = new ArrayList<Huistaak>();
		ArrayList<Bewoner> bewoners = new ArrayList<Bewoner>();
		BewonerDao bD = new BewonerDao();
		HuistaakDao hD = new HuistaakDao();
		huistaken = hD.getHuistaken();
		bewoners = bD.getAllBewoners();
		int hID = 0;
		int bID = 0;
		int id = 0;
		int weeknr = 0;
		boolean check = false;
		while(rs.next()){
			hID = rs.getInt("huistaakID");
			bID = rs.getInt("bewonersID");
			id = rs.getInt("id");
			int ch = rs.getInt("done");
			weeknr = rs.getInt("weeknr");
			if(ch == 0){
				check = false;
			}
			else{
				check = true;
			}
			
		}
		return new Huistaakuitvoering(bewoners.get(bID), huistaken.get(hID), check, weeknr, id);
	}
	
	
	public ArrayList<Huistaakuitvoering> getHuistaken(int week) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		rs = stmt.executeQuery("select * from huistaakuitvoering where weeknr = "+week);
		ArrayList<Huistaakuitvoering> huistaakuitvoeringen = new ArrayList<Huistaakuitvoering>();
		ArrayList<Huistaak> huistaken = new ArrayList<Huistaak>();
		ArrayList<Bewoner> bewoners = new ArrayList<Bewoner>();
		BewonerDao bD = new BewonerDao();
		HuistaakDao hD = new HuistaakDao();
		huistaken = hD.getHuistaken();
		bewoners = bD.getAllBewoners();
		hD.close();
		bD.close();
		
		while(rs.next()){
			int hID = rs.getInt("huistaakID");
			int bID = rs.getInt("bewonerID");
			int id = rs.getInt("id");
			int ch = rs.getInt("done");
			int weeknr = rs.getInt("weeknr");
			boolean check;
			if(ch == 0){
				check = false;
			}
			else{
				check = true;
			}
			
			huistaakuitvoeringen.add(new Huistaakuitvoering(bewoners.get(bID-1), huistaken.get(hID-1), check, weeknr, id));
		}
		return huistaakuitvoeringen;
	}
	
	public void checkHuistaakuitvoering(int id) throws SQLException{
		String query = "select done from huistaakuitvoering where id = "+id;
		rs = stmt.executeQuery(query);
		int check = 0;
		while(rs.next()){
			check = rs.getInt("done");
		}
		if(check == 0){
			stmt.executeUpdate("update huistaakuitvoering set done = 1 where id = "+id);
		}
		else{
			stmt.executeUpdate("update huistaakuitvoering set done = 0 where id = "+id);
		}
	}
	
	public void fillHuistaakDatabase() throws SQLException{
		int i = 1;
		int bID = 1;
		int hID = 1;
		while(i <= 14){
			int d = 1;
			
			while(d <= 14){
				stmt.executeUpdate("insert into huistaakuitvoering (bewonerID, huistaakID, weeknr) values("+bID+","+hID+","+i+");");
				if(hID==14){hID=1;}
				else{hID ++;}
				if(bID==14){bID=1;}
				else{bID ++;}
				d ++;
				}
			bID++;
			i ++;
			}
		}
	
	public void close() throws SQLException{
		rs.close();
		stmt.close();
		conn.close();
	}
	}
	