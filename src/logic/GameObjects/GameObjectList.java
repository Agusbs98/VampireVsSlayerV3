package logic.GameObjects;
import java.util.*;
import logic.Level;
import logic.Game;

public class GameObjectList {
	private int maxObjects;
	private ArrayList<GameObject> gameObjects;
	private Game game;
	private boolean gameover = false;

	public GameObjectList(Level level, Game game){
		this.maxObjects= level.getDimX()*level.getDimY();
		this.gameObjects= new ArrayList<GameObject>(maxObjects);
		this.game= game;
		Vampire.inicializaVamp(level.getNumberOfVampires());		
	}
	
	public void add(GameObject objeto) {
		gameObjects.add(objeto);
	}
	
	public void mover() {
		for(int i=0; i<gameObjects.size(); i++) {
			if(gameObjects.get(i).getCol()>0 && this.isPositionEmpty(gameObjects.get(i).getCol()-1, gameObjects.get(i).getFil())) {
				this.gameover = gameObjects.get(i).mover();
			}
		}
	}
	
	public void moveBack() {
		for(int i=gameObjects.size()-1;i>=0; i--) {
			gameObjects.get(i).retrocede();
			/*
			 *  if (gameObjects.get(i).getCol()>col) {
			 *  gameObjects.get(i).quitaVida(gameObjects.get(i).getVida());
			}
			*/
		}
	}
	
	public void lightFlash() {
		for(int i=0; i < gameObjects.size(); i++) {
			gameObjects.get(i).lightAttack();
		}
	}
	
	public IAttack getAttackableInPosition(int col, int fil) {
		return gameObjects.get(getPos(col, fil));
	}

	public void attack() {
		for (int i=0; i<gameObjects.size(); i++) {
			gameObjects.get(i).attack();
		}
	}

	public void removeDead(int col) {
		for (int i=0; i<gameObjects.size();) {
			if(!gameObjects.get(i).isAlive()) {
				gameObjects.remove(i);
			}else if(gameObjects.get(i).getCol()>col) {
				Vampire.reduceVampiresOnBoard();
				gameObjects.remove(i);
			}
			else {
				i++;
			}
		}
	}

	public boolean isPositionEmpty(int col, int fil) {
		boolean empty=true;
		if(getPos(col, fil)!=-1) empty=false;
		return empty;
	}
	
	private int getPos(int col, int fila) {
		int pos = 0;
		boolean encontrado = false;
		while (!encontrado && pos<gameObjects.size()) {
			if(gameObjects.get(pos).hayObjeto(col, fila)) encontrado = true;
			else pos++;
		}
		if (!encontrado) pos = -1;
		
		return pos;
	}
	
	public String getObjectToString(int x ,int y) {
		String salida = "";
		for(int i = 0; i<gameObjects.size();i++) {
			if(gameObjects.get(i).hayObjeto(x, y)) {
				salida = gameObjects.get(i).toString();
			}
		}
		return salida;
	}

	public String getListString() {
		String salida= "\nGame Object List:\n";
		for(int i = 0; i< gameObjects.size();i++) {
				salida = salida + gameObjects.get(i).toStringSerialized()+"\n";
		}
		return salida;
	}
	
}