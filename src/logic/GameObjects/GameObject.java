package logic.GameObjects;
import logic.Game;

public abstract class GameObject implements IAttack {
	protected int vida, fila, col, danio;
	protected Game game;
	
	public GameObject(int vida, int fila, int col, Game game) {
		this.game= game;
		this.vida= vida;
		this.fila= fila;
		this.col= col;
	}
	
	protected boolean isAlive(){
		return (this.vida>0);
	}
	
	public int getVida() {
		return this.vida;
	}
	
	protected void quitaVida(int danio) {
		this.vida-=danio;
	}
	
	public int getDanio() {
		return this.danio;
	}
	
	public abstract boolean mover();
	
	public int getFil() {
		return this.fila;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public boolean hayObjeto(int col, int fil) {
		return(this.fila==fil && this.col==col);
	}
	public abstract String toStringSerialized();

}