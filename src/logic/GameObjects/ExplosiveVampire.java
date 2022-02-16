package logic.GameObjects;

import logic.Game;

public class ExplosiveVampire extends Vampire {
	
	public ExplosiveVampire(int fila, int col, Game game) {
		super(fila, col, game);
	}
	
	public boolean receiveSlayerAttack(int danio) { // recibe el vampiro
		boolean ataca = false;
		if(this.isAlive()) {
			this.quitaVida(danio);
				if(!this.isAlive()) {
					this.explosion();
					/*
					game.bajasConfirmadas(this.getCol(),this.getFil());*/
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
	
	private void explosion() {
		for(int i=this.getCol()-1; i<=this.getCol()+1;i++) {
			for(int j=this.getFil()-1; j<=this.getFil()+1;j++) {
				IAttack other = game.getAttackableInPosition(i, j);
				if (other != null) {
					other.receiveVampireAttack(danio);
					other.receiveSlayerAttack(danio);
				}
			}
		}
	}

	
	public String toString() {
		return "EV["+ this.vida +"]";
				
	}
	
	public String toStringSerialized() {
		return "EV;"+this.col+";"+this.fila+";"+this.vida+";"+this.ciclosV%2;
	}
	
}
