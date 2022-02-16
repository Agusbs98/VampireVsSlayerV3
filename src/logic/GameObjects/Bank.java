package logic.GameObjects;

import logic.Game;

public class Bank extends GameObject{
	private int comision;
	public Bank(int fila, int col,int comision, Game game) {
		super(1, fila, col, game);
		this.comision = comision;
	}
	
	public boolean receiveVampireAttack(int danio) {
		boolean ataca = false;
		if(this.isAlive()) {
			if (danio<0) {
				this.quitaVida(this.vida);
			}else {
				this.quitaVida(danio);	
			}
			ataca = true;
		}
			
		return ataca;
	}
	
	public void attack() {
		game.addBankCoins(this.comision/10);
	}
	
	public boolean mover() {return false;}
	
	public String toString() {
		return "B ["+ this.comision/10 +"]";
				
	}
	public String toStringSerialized() {
		return "B;"+this.col+";"+this.fila+";"+this.vida+";"+this.comision;
	}


}
