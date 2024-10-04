package de.dhbwka.stadt_land_fluss.classes;

public class StadtLandFluss {
		
	public static void main(String[] args) {
		Game slf = new Game(4,6,60);
		slf.register(new Sheet(new Player("Otto"),slf));
		slf.register(new Sheet(new Player("Anna"),slf));
	}
	
}
