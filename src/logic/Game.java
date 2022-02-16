package logic;

import java.util.Random;

import excepciones.*;
import logic.Level;
import logic.GameObjects.*;
import view.*;


public class Game implements IPrintable{
	private long seed;
	private Level level;
	private GameObjectBoard board; //board tiene dos arrays slayer y vampire y printer guarda en string el board
	private GamePrinter printer;
	private Player player;
	private boolean fin= false;
	private Random aleat;
	
	public Game(long seed, Level level) {
		this.seed = seed;
		this.level = level;
		this.printer = new GamePrinter(this, level.getDimX(), level.getDimY());
		inicializa();
	}
	
	public void inicializa() {
		this.board = new GameObjectBoard(level, this);
		this.player = new Player();
		this.aleat = new Random(this.seed);
	}
	
	public IAttack getAttackableInPosition(int col, int fil) {
		return board.getAttackableInPosition(col, fil);
	}

	public boolean addSlayer(int posX, int posY) throws CommandExecuteException{
		boolean salida = false;
		if(player.getMonedas()>= 50) {
			if(posX>=this.level.getDimX()-1 || posY>=this.level.getDimY()) {
				throw new PositionException("[ERROR]:Position ("+posX+", "+ posY+"): Unvalid position");
			}
			else if(board.addSlayer(posX,posY)) {
				player.compra(50);
				salida = true;
			}
			else {
				throw new PositionException("[ERROR]:Position ("+posX+", "+ posY+"): Unvalid position");
			}
		}
		else throw new CoinsException("[ERROR]:Add Slayer cost is 50: Not enough coins");
		this.update();
		return salida;
	}
	
	public void add() {
		if(this.aleatDouble() < level.getVampireFrequency() &&  (Vampire.getRemainingVampires()>0)) {
			int f = aleatInt();
			f = f % level.getDimY();
			f = Math.abs(f);
			this.addVampire(level.getDimX()-1, f);
		}
		if (!Vampire.getDraculaIsAlive()) {
			if(this.aleatDouble() < level.getVampireFrequency() &&  (Vampire.getRemainingVampires()>0)) {
				int f = aleatInt();
				f = f % level.getDimY();
				f = Math.abs(f);
				this.addDracula(level.getDimX()-1, f);
			}
		}
		if(this.aleatDouble() < level.getVampireFrequency() &&  (Vampire.getRemainingVampires()>0)) {
			int f = aleatInt();
			f = f % level.getDimY();
			f = Math.abs(f);
			this.addExplosiveVampire(level.getDimX()-1, f);
		}
	}
	
	public boolean addVampireType(int col, int fil, String type)throws CommandExecuteException {
		boolean salida = true;
		if(Vampire.getRemainingVampires()>0 ) {
			if(col<this.getCols() && fil<this.getRows() && col>0 && fil>=0) {
			
				switch (type) {
				case "v":
				case "V":
					if(!this.addVampire(col,fil)) {
						throw new PositionException("[ERROR]:Position ("+col+", "+ fil+"): Unvalid position");
					}
				break;
				case "d":
				case "D":
					if(!Vampire.getDraculaIsAlive()) {
						if(!this.addDracula(col,fil)) {
							throw new PositionException("[ERROR]:Position ("+col+", "+ fil+"): Unvalid position");
						}
					}
					else throw new DraculaIsAliveException("[ERROR]:Dracula is already alive");				
				break;
				case "e":
				case "E":
					if(!this.addExplosiveVampire(col,fil)) {
						throw new PositionException("[ERROR]:Position ("+col+", "+ fil+"): Unvalid position");
					}
				break;
				default:
					throw new UnknownTypeException("[ERROR]:Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"V\"|\"D\"|\"E\"}");
					
				}
			}
				
			else {
				throw new PositionException("[ERROR]:Position ("+col+", "+ fil+"): Unvalid position");
				
			}
		}
		else {
			throw new NoMoreVampireException("[ERROR]:");
		}
		return salida;
	}
	
	private boolean addVampire(int col, int fil) {
			return board.addVampire(col,fil);
	}
	
	private boolean addDracula(int col, int fil) {
			return board.addDracula(col, fil);
			//this.dracula=true;
	}
	
	private boolean addExplosiveVampire(int col, int fil) {
			return board.addEV(col,fil);
	}
	
	public boolean addBank(int posX, int posY,int comision) throws CommandExecuteException{
		boolean salida = false;
		if(player.getMonedas()>= comision) {
			if(posX>=this.level.getDimX()-1 || posY>=this.level.getDimY()) {
				throw new PositionException("[ERROR]:Position ("+posX+", "+ posY+"): Unvalid position");
			}
			else if(board.addBank(posX,posY,comision)) {
				player.compra(comision);
				salida = true;
			}
			else {
				throw new PositionException("[ERROR]:Position ("+posX+", "+ posY+"): Unvalid position");
			}
		}
		else throw new CoinsException("[ERROR]:Add Bank cost is "+comision+": Not enough coins");
		this.update();
		return salida;
	}
	
	public void update() {		
		board.updateBoard();
		this.add();
		if(aleatDouble()<0.5) {
			player.addCoins(10);
		}
		player.aumentaCiclos(1);
		this.finPartida();
	}
	
	public void finPartida() {
		if(board.queHaPasao()) {
			this.fin = true;
		}
	}
	
	public boolean modoFin() {
		boolean winner=false;
		if(board.queHaPasao()) {
			winner=true;
		}
		return winner;
	}
	

	public boolean fin() {
		return this.fin;
	}
	
	public void exit() {
		 this.fin=true;
	 }

	public int getCols() {
		return this.level.getDimX();
	}

	public int getRows() {
		return this.level.getDimY();
	}

	private int aleatInt() {
		return aleat.nextInt();
	}

	private double aleatDouble() {
		return aleat.nextDouble();
	}

	private double aleatFloat() {
		return aleat.nextFloat();
	}
	
	public String getWinnerMessage() {
		return "GANASTE";
	}
	
	public String getLoserMessage() {
		return "PERDISTE";
	}

	public String toString() {
		return printer.toString();		
	}
	

	@Override
	public String getPositionToString(int x, int y) {
		// TODO Auto-generated method stub
		return board.getObjectToString(x, y);
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String info= "Number of cycles: "+ player.getCiclos() + "\nCoins: " + player.getMonedas()+"\nRemaining vampires: " + Vampire.getRemainingVampires()+"\nVampires on the board: " + Vampire.getVampiresOnBoard();
		return info;
	}
	
	
	public void addBankCoins(int add) {
		player.addCoins(add);
	}
	
	public boolean garlic()throws CommandExecuteException {
		boolean salida = false;
		if(player.getMonedas()>= 10) {
			board.moveBack();
			player.compra(10);
			salida = true;
			this.update();
		}
		else throw new CoinsException("[ERROR]:Garlic cost is 10: Not enough coins");
		return salida;
	}
	
	public boolean light() throws CommandExecuteException{
		boolean salida = false;
		if(player.getMonedas()>= 50) {
			board.lightFlash();
			player.compra(50);
			salida = true;
			this.update();
		}
		else throw new CoinsException("[ERROR]:Light cost is 50: Not enough coins");
		return salida;
	}
	
	public void superCoins() {
		player.addCoins(1000);
	}
	
	public String serialize() {
		String salida="";
		salida = "Cycles: "+player.getCiclos() + 
				"\nCoins: " + player.getMonedas()+
				"\nLevel: " +level.getName()+
				"\nRemaining Vampires: " +Vampire.getRemainingVampires()+
				"\nVampires on Board: " + Vampire.getVampiresOnBoard()+"\n";
		salida = salida + board.getBoardSerialize();
		
		return salida;
	}
	
}