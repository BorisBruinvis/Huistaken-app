package Model;

public class Huistaak {
	private String titel;
	private String omschrijving;
	private int huistaakId;
	
	public Huistaak(String t, String o, int i){
		titel = t;
		omschrijving = o;
		huistaakId = i;
	}
	
	public String getTitel(){
		return titel;
	}
	
	public String getOmschrijving(){
		return omschrijving;
	}
	
	public void setTitel(String s){
		titel = s;
	}
	
	public void setOmschrijving(String o){
		omschrijving = o;
	}
	
	public int getHuistaakId(){
		return huistaakId;
	}
}
