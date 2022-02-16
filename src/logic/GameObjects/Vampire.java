package logic.GameObjects;

import logic.Game;

public class Vampire extends GameObject {
	protected int danio=1;
	protected int ciclosV = 1;
	private static int remainingVampires;
	private static int VampiresOnBoard;
	protected static boolean DraculaIsAlive = false;
	
	public Vampire(int fila, int col, Game game) {
		super(5, fila, col, game);
		remainingVampires--;
		VampiresOnBoard++;
	}
	
	public boolean receiveSlayerAttack(int danio) { // recibe el vampiro
		boolean ataca = false;
		if(this.isAlive()) {
			this.quitaVida(danio);
				if(!this.isAlive()) {
					/*game.bajasConfirmadas(this.getCol(),this.getFil());
					GameObjectList.reduceVampiresOnBoard();*/
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
	
	public boolean mover() {
		if(this.ciclosV%2 == 0) {
			this.col--;
			if(this.col==0) {
				game.exit();
			}
		}
		this.ciclosV++;
		return (this.col == 0);
	}
	
	public void retrocede() {
		this.col++;
		this.ciclosV = 1;
		/*if(this.col == n) {
			game.bajasConfirmadas(this.getCol(),this.getFil());
			GameObjectList.reduceVampiresOnBoard();
		}*/
	}
	
	public boolean lightAttack() {
		//game.bajasConfirmadas(this.getCol(),this.getFil());
		this.receiveSlayerAttack(this.vida);
		return true;
	}
	
	public String toString() {
		return "V ["+ this.vida +"]";		
	}
	
	public String toStringSerialized() {
		return "V;"+this.col+";"+this.fila+";"+this.vida+";"+this.ciclosV%2;
	}
	
	public static void inicializaVamp(int n) {
		Vampire.remainingVampires = n;
		Vampire.VampiresOnBoard = 0; 
	}
	
	public static int getRemainingVampires() {
		return remainingVampires;
	}
	
	public static int getVampiresOnBoard() {
		return VampiresOnBoard;
	}
	
	public static void reduceVampiresOnBoard() {
		VampiresOnBoard--;
	}
	
	public static boolean getDraculaIsAlive() {
		return DraculaIsAlive;
	}
}
