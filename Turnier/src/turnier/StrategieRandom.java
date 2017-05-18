package turnier;

import java.time.temporal.ValueRange;

import com.nextlevel.*;
import com.nextlevel.nlb.battleship.api.communication.Coordinate;
import com.nextlevel.nlb.battleship.api.communication.ShotResult;
import com.nextlevel.nlb.battleship.api.strategies.BattleShipStrategy;

public class StrategieRandom implements BattleShipStrategy {

	private int versuch = 1;
	private int länge = 0;
	private int breite = 0;
	boolean[][] merken = new boolean[10][10];
	private Koordinate ausgabe = new Koordinate();
	private boolean shipDetect = false;
	int[] prio1 = new int[2];
	int[] prio2 = new int[2];
	int[] prio3 = new int[2];
	int[] prio4 = new int[2];
	int[] oldHit = new int[2];
	int[] initHit = new int[2];
	int hits = 0;

	public StrategieRandom() {
		ausgabe = new Koordinate((int) (Math.random() * 10),
				(int) (Math.random() * 10));
	}

	public Coordinate getShotFromPlayer() {
		// TODO Auto-generated method stub
		return ausgabe;
	}

	public String getTeamName() {
		// TODO Auto-generated method stub
		return "TM";
	}

	public void setGameBoardSizeToPlayer(int arg0, int arg1, int arg2) { // xyz
		// TODO Auto-generated method stub

	}

	public void setShotResultToPlayer(ShotResult arg0) {
		// TODO Auto-generated method stub
		if (arg0 == ShotResult.SHIP_DESTROYED) {
			shipDetect = false;
			hits = 1;
		}
		if (shipDetect) {
			if(versuch == 2){
				saveNoHit(ausgabe.getLenght(), ausgabe.getWidth());
				ausgabe.setBreite(prio2[0]);
				ausgabe.setLänge(prio2[1]);
				versuch++;
			}
			if(versuch == 3){
				saveNoHit(ausgabe.getLenght(), ausgabe.getWidth());
				ausgabe.setBreite(prio3[0]);
				ausgabe.setLänge(prio3[1]);
				versuch++;
			}
			if(versuch == 4){
				saveNoHit(ausgabe.getLenght(), ausgabe.getWidth());
				ausgabe.setBreite(prio4[0]);
				ausgabe.setLänge(prio4[1]);
				versuch++;
			}
			if (arg0 == ShotResult.SHIP_HIT) {
					versuch = 0;
					int[] tmpOld = new int[2];
					tmpOld[0] = ausgabe.getWidth();
					tmpOld[1] = ausgabe.getLenght();
					save(ausgabe.getLenght(), ausgabe.getWidth());
					ausgabe.setBreite(ausgabe.getWidth()+(ausgabe.getWidth()-oldHit[0]));
					ausgabe.setLänge(ausgabe.getLenght()+(ausgabe.getLenght()-oldHit[1]));
					oldHit[0] = tmpOld[0];
					oldHit[1] = tmpOld[1];
					hits++;
			}else{
				saveNoHit(ausgabe.getLenght(), ausgabe.getWidth());
				int difx = ausgabe.getWidth() - oldHit[0];
				int dify = ausgabe.getLenght() - oldHit[1];
				if(difx > 0){
					ausgabe.setBreite(ausgabe.getWidth()-1);
				}else{
					ausgabe.setBreite(ausgabe.getWidth()+1);
				}
				if(dify > 0){
					ausgabe.setLänge(ausgabe.getLenght()-1);
				}else{
					ausgabe.setLänge(ausgabe.getLenght()+1);
				}
			}
			if(merken[ausgabe.getWidth()][ausgabe.getLenght()]){
				save(ausgabe.getLenght(), ausgabe.getWidth());
				boolean check = true;
				int tmpLänge = 0;
				int tmpBreite = 0;
				while (check) {
					tmpBreite = (int) (Math.random() * 10);
					tmpLänge = (int) (Math.random() * 10);
					if (this.merken[tmpLänge][tmpBreite] == false) {
						check = false;
					}
				}
				ausgabe.setLänge(tmpLänge);
				ausgabe.setBreite(tmpBreite);
			}
			if(ausgabe.getLenght()>10){
				ausgabe.setLänge(ausgabe.getLenght()-hits);
				hits=0;
				oldHit[0]=initHit[0];
				oldHit[1]=initHit[1];
			}
			if(ausgabe.getLenght()<0){
				ausgabe.setLänge(ausgabe.getLenght()+hits);
				hits=0;
				oldHit[0]=initHit[0];
				oldHit[1]=initHit[1];
			}
			if(ausgabe.getWidth()>10){
				ausgabe.setBreite(ausgabe.getWidth()-hits);
				hits=0;
				oldHit[0]=initHit[0];
				oldHit[1]=initHit[1];
			}
			if(ausgabe.getWidth()<0){
				ausgabe.setBreite(ausgabe.getWidth()+hits);
				hits=0;
				oldHit[0]=initHit[0];
				oldHit[1]=initHit[1];
			}
			
			
		} else {
			if (arg0 == ShotResult.NO_HIT) {
				saveNoHit(ausgabe.getLenght(), ausgabe.getWidth());
				boolean check = true;
				int tmpLänge = 0;
				int tmpBreite = 0;
				while (check) {
					tmpBreite = (int) (Math.random() * 10);
					tmpLänge = (int) (Math.random() * 10);
					if (this.merken[tmpLänge][tmpBreite] == false) {
						check = false;
					}
				}
				ausgabe.setLänge(tmpLänge);
				ausgabe.setBreite(tmpBreite);
			}
			if (arg0 == ShotResult.SHIP_DESTROYED) {
				save(ausgabe.getLenght(), ausgabe.getWidth());
				boolean check = true;
				int tmpLänge = 0;
				int tmpBreite = 0;
				while (check) {
					tmpBreite = (int) (Math.random() * 10);
					tmpLänge = (int) (Math.random() * 10);
					if (this.merken[tmpLänge][tmpBreite] == false) {
						check = false;
					}
				}
				ausgabe.setLänge(tmpLänge);
				ausgabe.setBreite(tmpBreite);
			}
			if (arg0 == ShotResult.SHIP_HIT) {
				save(ausgabe.getLenght(), ausgabe.getWidth());
				shipDetect = true;
				initHit[0] = ausgabe.getWidth();
				initHit[1] = ausgabe.getLenght();
				if (9 - ausgabe.getWidth() > ausgabe.getWidth()) {
					if (9 - ausgabe.getLenght() > ausgabe.getLenght()) {
						if (9 - ausgabe.getWidth() > 9-ausgabe.getLenght()) {
							prio1[0] = ausgabe.getWidth() + 1;
							prio1[1] = ausgabe.getLenght();
							prio2[0] = ausgabe.getWidth();
							prio2[1] = ausgabe.getLenght()+1;
							prio3[0] = ausgabe.getWidth();
							prio3[1] = ausgabe.getLenght()-1;
							prio4[0] = ausgabe.getWidth()-1;
							prio4[1] = ausgabe.getLenght();
						}
						else{
							prio1[0] = ausgabe.getWidth();
							prio1[1] = ausgabe.getLenght()+1;
							prio2[0] = ausgabe.getWidth() + 1;
							prio2[1] = ausgabe.getLenght();
							prio3[0] = ausgabe.getWidth()-1;
							prio3[1] = ausgabe.getLenght();
							prio4[0] = ausgabe.getWidth();
							prio4[1] = ausgabe.getLenght()-1;
						}
					}
					else{
						if (9 - ausgabe.getWidth() > 9-ausgabe.getLenght()) {
							prio1[0] = ausgabe.getWidth() + 1;
							prio1[1] = ausgabe.getLenght();
							prio2[0] = ausgabe.getWidth();
							prio2[1] = ausgabe.getLenght()-1;
							prio3[0] = ausgabe.getWidth();
							prio3[1] = ausgabe.getLenght()+1;
							prio4[0] = ausgabe.getWidth()-1;
							prio4[1] = ausgabe.getLenght();
						}
						else{
							prio1[0] = ausgabe.getWidth();
							prio1[1] = ausgabe.getLenght()-1;
							prio2[0] = ausgabe.getWidth()+1;
							prio2[1] = ausgabe.getLenght();
							prio3[0] = ausgabe.getWidth()-1;
							prio3[1] = ausgabe.getLenght();
							prio4[0] = ausgabe.getWidth();
							prio4[1] = ausgabe.getLenght()+1;
						}
					}
				}else{
					if (9 - ausgabe.getLenght() > ausgabe.getLenght()) {
						if (ausgabe.getWidth() > 9-ausgabe.getLenght()) {
							prio1[0] = ausgabe.getWidth()-1;
							prio1[1] = ausgabe.getLenght();
							prio2[0] = ausgabe.getWidth();
							prio2[1] = ausgabe.getLenght()-1;
							prio3[0] = ausgabe.getWidth();
							prio3[1] = ausgabe.getLenght()+1;
							prio4[0] = ausgabe.getWidth()+1;
							prio4[1] = ausgabe.getLenght();
						}
						else{
							prio1[0] = ausgabe.getWidth();
							prio1[1] = ausgabe.getLenght()-1;
							prio2[0] = ausgabe.getWidth() - 1;
							prio2[1] = ausgabe.getLenght();
							prio3[0] = ausgabe.getWidth()+1;
							prio3[1] = ausgabe.getLenght();
							prio4[0] = ausgabe.getWidth();
							prio4[1] = ausgabe.getLenght()-1;
						}
					}
					else{
						if (ausgabe.getWidth() > 9-ausgabe.getLenght()) {
							prio1[0] = ausgabe.getWidth()-1;
							prio1[1] = ausgabe.getLenght();
							prio2[0] = ausgabe.getWidth();
							prio2[1] = ausgabe.getLenght()-1;
							prio3[0] = ausgabe.getWidth();
							prio3[1] = ausgabe.getLenght()+1;
							prio4[0] = ausgabe.getWidth()+1;
							prio4[1] = ausgabe.getLenght();
						}
						else{
							prio2[0] = ausgabe.getWidth();
							prio2[1] = ausgabe.getLenght()-1;
							prio1[0] = ausgabe.getWidth()-1;
							prio1[1] = ausgabe.getLenght();
							prio3[0] = ausgabe.getWidth()+1;
							prio3[1] = ausgabe.getLenght();
							prio4[0] = ausgabe.getWidth();
							prio4[1] = ausgabe.getLenght()+1;
						}
					}
				}
				ausgabe.setBreite(prio1[0]);
				ausgabe.setLänge(prio1[1]);
				oldHit[0] = initHit[0];
				oldHit[1] = initHit[1];
			}
		}
		if(ausgabe.getLenght()>9){
			ausgabe.setLänge(9);
		}
		if(ausgabe.getLenght()<0){
			ausgabe.setLänge(0);
		}
		if(ausgabe.getWidth()>9){
			ausgabe.setBreite(0);
		}
		if(ausgabe.getWidth()<0){
			ausgabe.setLänge(0);
		}

	}

	public void save(int länge, int breite) {
		merken[länge][breite] = true;
		if ((länge - 1) < 0) {

		} else {
			merken[länge - 1][breite] = true;
		}
		if ((länge + 1) > 9) {

		} else {
			merken[länge + 1][breite] = true;
		}
		if ((breite - 1) < 0) {

		} else {
			merken[länge][breite - 1] = true;
		}
		if ((breite + 1) > 9) {

		} else {
			merken[länge][breite + 1] = true;
		}
	}

	public void saveNoHit(int länge, int breite) {
		merken[länge][breite] = true;
	}
}