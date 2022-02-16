package logic;

public class Player {

		private int monedas;
		private int ciclos;


		public Player() {
			this.monedas = 50;
			this.ciclos = 0;
		}
		
		public void addCoins(int n) {
			this.monedas += n;
		}
		
		public int getMonedas() {
			return this.monedas;
		}
		
		public void compra(int n) {
			this.monedas -= n;
		}
		
		public int getCiclos() {
			return this.ciclos;
		}
		
		public void aumentaCiclos(int num) {
			this.ciclos+=num;
		}

}
