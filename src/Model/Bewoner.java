package Model;

import java.sql.SQLException;


import Persistentie.AccountDao;

public class Bewoner {
	private String voorNaam;
	private String achterNaam;
	private int id;
	private boolean claimed;
	private int kamerNummer;
	private Account account;
	
	public Bewoner(int d, String vN, String aN, int kN, boolean cl) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		id = d;
		voorNaam = vN;
		achterNaam = aN;
		kamerNummer = kN;
		claimed = cl;
		AccountDao aD = new AccountDao();
		account = aD.getAccount(d);
		aD.close();
	}
	
	public String getVoorNaam(){
		return voorNaam;
	}
	
	public String getAchterNaam(){
		return achterNaam;
	}
	
	public void claimBewoner(){
		claimed = true;
	}
	
	public boolean getClaim(){
		return claimed;
	}
	
	public int getKamerNummer(){
		return kamerNummer;
	}
	public int getId(){
		return id;
	}
	
	
	public Account getAccount(){
		return account;
	}
	
	public void setAccount(Account a){
		account = a;
	}
	
	public boolean equals(Bewoner b){
		if(this.account.equals(b.getAccount()) &&
		this.achterNaam.equals(b.getAchterNaam()) &&
		this.claimed == b.getClaim() &&
		this.id == b.getId() &&
		this.kamerNummer == b.getKamerNummer() &&
		this.voorNaam.equals(b.getVoorNaam()))
		{return true;}
		else{return false;}
	}
	
}
