package Model;

public class Huistaakuitvoering {
	private Bewoner bewoner;
	private Huistaak huistaak;
	private boolean check = false;
	private int weeknr;
	private int id;
	
	public Huistaakuitvoering(Bewoner b, Huistaak h, boolean ch, int nr, int i){
		bewoner = b;
		huistaak = h;
		id = i;
		check = ch;
		weeknr = nr;
		
	}
	
	public Bewoner getBewoner(){
		return bewoner;
	}
	
	public Huistaak getHuistaak(){
		return huistaak;
	}
	
	
	public boolean isDeHuistaakGedaan(){
		return check;
	}
	
	public int getWeeknr(){
		return weeknr;
	}
	
	public int getId(){
		return id;
	}
	
}
