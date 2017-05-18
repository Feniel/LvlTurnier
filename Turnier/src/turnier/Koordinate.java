package turnier;

import com.nextlevel.nlb.battleship.api.communication.Coordinate;

public class Koordinate implements Coordinate {

	private int länge;
	private int breite;
	
	public Koordinate(int länge,int breite){
		this.länge = länge;
		this.breite = breite;
	}
	
	public Koordinate(){
		
	}
	
	
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getLenght() {
		// TODO Auto-generated method stub
		return länge;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return breite;
	}
	
	public void setBreite(int breite){
		this.breite = breite;
	}
	
	public void setLänge(int länge){
		this.länge = länge;
	}

}
