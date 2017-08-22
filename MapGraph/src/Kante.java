
public class Kante {
	private Knoten knoten1;
	private Knoten knoten2;
	private int distanz;
	
	public Kante(Knoten k1, Knoten k2) {
		this.knoten1 = k1;
		this.knoten2 = k2;
		int x1 = k1.getX();
		int x2 = k2.getX();
		int y1 = k1.getY();
		int y2 = k2.getY();
		this.distanz = (int) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}

	public Knoten getKnoten1() {
		return knoten1;
	}

	public void setKnoten1(Knoten knoten1) {
		this.knoten1 = knoten1;
	}

	public Knoten getKnoten2() {
		return knoten2;
	}

	public void setKnoten2(Knoten knoten2) {
		this.knoten2 = knoten2;
	}

	public int getDistanz() {
		return distanz;
	}
}
