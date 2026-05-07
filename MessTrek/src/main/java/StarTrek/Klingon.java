package StarTrek;

import java.util.Random;

public class Klingon implements Enemy {
	private int distance;
	private int energy;
	
	public Klingon() {
		Random x = new Random();
		distance = 100 + x.nextInt(4000);
		energy = 1000 + x.nextInt(2000);
	}

	public Klingon(int distance, int energy) {
		this.distance = distance;
		this.energy = energy;
	}

	@Override
	public int distance() {
		return distance;
	}

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public void setEnergy(int e) {
		energy = e;
	}

	@Override
	public void delete() {
	}

}
