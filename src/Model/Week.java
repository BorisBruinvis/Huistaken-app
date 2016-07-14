package Model;

import java.sql.SQLException;
import java.util.ArrayList;

import Persistentie.HuistaakDao;
import Persistentie.HuistaakuitvoeringDao;

public class Week {
	private int weekNr;
	private boolean lock;
	ArrayList<Huistaakuitvoering> huistaakuitvoeringen = new ArrayList<Huistaakuitvoering>();
	ArrayList<Huistaak> huistaken = new ArrayList<Huistaak>();
	
	public Week(int wN) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		weekNr = wN;
		HuistaakuitvoeringDao huD = new HuistaakuitvoeringDao();
		huistaakuitvoeringen = huD.getHuistaken(wN);
		HuistaakDao hD = new HuistaakDao();
		huistaken = hD.getHuistaken();
		hD.close();
		huD.close();
	}
	
	public int getWeekNr(){
		return weekNr;
	}
	
	public ArrayList<Huistaakuitvoering> getHuistaakuitvoeringen(){
		return huistaakuitvoeringen;
	}
	
	public ArrayList<Huistaak> getHuistaken(){
		return huistaken;
	}
	
	public boolean getLock(){
		return lock;
	}
	
	
}
