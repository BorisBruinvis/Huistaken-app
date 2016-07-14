package Model;

public class Account {
	private String email;
	private String wachtwoord;
	private int bewonerId;
	
	public Account(String em, String ww, int bI){
		email = em;
		wachtwoord = ww;
		bewonerId = bI;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getWachtwoord(){
		return wachtwoord;
	}
	
	public int getBewonerId(){
		return bewonerId;
	}
	
	public boolean equals(Account a){
		if(
			this.email.equals(a.getEmail()) &&
			this.wachtwoord.equals(a.getWachtwoord()) &&
			this.bewonerId == a.getBewonerId()){
			return true;}
		else{return false;}
		}
}
