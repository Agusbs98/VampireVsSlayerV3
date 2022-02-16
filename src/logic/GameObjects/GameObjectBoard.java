package logic.GameObjects;

import logic.*;

public class GameObjectBoard {

	private int dimX, dimY;
	private GameObjectList list;
	private Game game;
		
	public GameObjectBoard(Level level, Game game) {
		this.dimX = level.getDimX();
		this.dimY = level.getDimY();
		this.game = game;
		this.list = new GameObjectList(level, game);
		
	}
	
	public boolean queHaPasao() {
		boolean salida= false;
		if(Vampire.getRemainingVampires() == 0 && Vampire.getVampiresOnBoard() == 0) {
			salida = true;
		}
		return salida;
	}
	
	public boolean addSlayer(int col, int fil) {
		boolean added=false;
		if(list.isPositionEmpty(col, fil)) {
			Slayer s= new Slayer(fil, col, game);
			list.add(s);
			
			added=true;
		}
		return added;
	}
	
	public boolean addVampire(int col, int fil) {
		boolean added=false;
		if(list.isPositionEmpty(col, fil)) {
			Vampire v= new Vampire(fil, col, game);
			list.add(v);
			added=true;
		}
		return added;
	}
	
	public boolean addDracula(int col, int fil) {
		boolean added=false;
		if(list.isPositionEmpty(col, fil)) {
			Vampire v= new Dracula(fil, col, game);
			list.add(v);
			System.out.println("Dracula is alive.");
			added=true;
		}
		return added;
	}
	
	public boolean addEV(int col, int fil) {
		boolean added=false;
		if(list.isPositionEmpty(col, fil)) {
			Vampire v= new ExplosiveVampire(fil, col, game);
			list.add(v);
			added=true;
		}
		return added;
	}
	
	public boolean addBank(int col, int fil,int comision) {
		boolean added=false;
		if(list.isPositionEmpty(col, fil)) {
			Bank s= new Bank(fil, col, comision, game);
			list.add(s);
			
			added=true;
		}
		return added;
	}
	
	public IAttack getAttackableInPosition(int col, int fil) {
		IAttack objeto = null;
	
		if(!list.isPositionEmpty(col, fil)) {
			objeto = list.getAttackableInPosition(col, fil);
		}
		return objeto;
	}
	
	public void updateBoard() {
		list.mover();
		list.attack();
		list.removeDead(this.dimX-1);
	}
	
	public String getObjectToString(int x ,int y) {
		return list.getObjectToString(x, y);
	}
	
	public void moveBack() {
		list.moveBack();
	}
	
	public void lightFlash() {
		list.lightFlash();
	}

	public String getBoardSerialize() {
		
		return list.getListString();
	}
	
}