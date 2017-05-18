package turnier;

import com.nextlevel.nlb.battleship.BattleShipArena2D;
import com.nextlevel.nlb.battleship.BattleShipDoofmanEvenStrategy;
import com.nextlevel.nlb.battleship.api.strategies.BattleShipStrategy;

public class Main {
	public static void main(String[] args){
		BattleShipStrategy player1 = new BattleShipDoofmanEvenStrategy();
		BattleShipStrategy player2 = new StrategieRandom();
		BattleShipArena2D arena = new BattleShipArena2D(player1, player2);
		try {
			arena.startGame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
