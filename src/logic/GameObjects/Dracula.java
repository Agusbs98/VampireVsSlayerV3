package logic.GameObjects;

import logic.Game;

public class Dracula extends Vampire{
	
	private int danio=3;

	public Dracula(int fila, int col, Game game) {
		super(fila, col, game);
		Vampire.DraculaIsAlive = true;
	}
	
	public boolean receiveSlayerAttack(int danio) { // recibe el vampiro
		boolean ataca = false;
		if(this.isAlive()) {
			this.quitaVida(danio);
				if(!this.isAlive()) {
					Vampire.DraculaIsAlive = false;
					Vampire.reduceVampiresOnBoard();
				}
			ataca = true;
		}
		return ataca;
	}
	
	public void attack() {
		if (this.isAlive()) {
			IAttack other = game.getAttackableInPosition(this.col-1, this.fila);
			if (other != null) {
				other.receiveVampireAttack(danio);
			}
		}
	}
	
	public boolean lightAttack() {return false;}
	
	public String toString() {
		return "D ["+ this.vida +"]";
	}
	
	public String toStringSerialized() {
		return "D;"+this.col+";"+this.fila+";"+this.vida+";"+this.ciclosV%2;
	}
	
	/*public boolean mover() {
		if(this.ciclosV%2 == 0) {
			this.col--;
			if(this.col==0) {
				game.exit();
			}
		}
		this.ciclosV++;
		return (this.col == 0);
	}*/
}

