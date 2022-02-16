package logic.GameObjects;

import logic.Game;

public class Slayer extends GameObject {
	
	public Slayer(int fila, int col, Game game) {
		super(3, fila, col, game);
	}
	
	public boolean receiveVampireAttack(int danio) {
		boolean ataca = false;
		if(this.isAlive()) {
			if (danio<0) {
				this.quitaVida(this.vida);
			}else {
				this.quitaVida(danio);	
			}
			/*if(!this.isAlive()) {
				game.bajasConfirmadas(this.getCol(),this.getFil());
			}*/
			ataca = true;
		}
			
		return ataca;
	}
	
	public void attack() {
		boolean encontrado=false;
		int x=this.getCol()+1;
		while(!encontrado && x < game.getCols()) {
			if (this.isAlive()) {
				IAttack other = game.getAttackableInPosition(x++, this.getFil());
				if (other != null) {
					encontrado = other.receiveSlayerAttack(1);
				}
			}else {encontrado=true;}
		}
	}
	
	public boolean mover() {
		return false;
	}
	
	public String toString() {
		return "S ["+ this.vida +"]";
	}
	public String toStringSerialized() {
		return "S;"+this.col+";"+this.fila+";"+this.vida;
	}
}	