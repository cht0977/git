
public class Knoten {
	private KnotenTyp knotenTyp;
	private int x;
	private int y;
	private int haus_id;
	private int room_id;
	private int etagen_id;
	private int standort_id;
	
	public Knoten(KnotenTyp kT, int x, int y) {
		this.knotenTyp = kT;
		this.x = x;
		this.y = y;
	}
	
	public Knoten(KnotenTyp kT, int x, int y, int id) {
		this(kT, x, y);
		switch(kT) {
		case RAUM: 
			this.room_id = id; break;
		case STANDORT:
			this.standort_id = id; break;
		case HAUS:
			this.haus_id = id; break;
		case ETAGE:
			this.etagen_id = id; break;
		}
	}
	

	public KnotenTyp getKnotenTyp() {
		return knotenTyp;
	}
	
	public String getString() {
		switch(knotenTyp) {
		case RAUM: 
			return "("+x+","+y+"," + room_id + ",NULL,NULL,NULL" + ")"; 
		case STANDORT:
			return "("+x+","+y+",NULL,NULL" + standort_id + ",NULL" + ")"; 
		case HAUS:
			return "("+x+","+y+",NULL" + haus_id + ",NULL,NULL" + ")";
		case ETAGE:
			return "("+x+","+y+",NULL,NULL,NULL" + etagen_id  + ")";
		default: return "";
		}
	}
	
	public void setKnotenTyp(KnotenTyp knotenTyp) {
		this.knotenTyp = knotenTyp;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
