import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Karte {
	private PNG img;
	private String name;
	private List<Knoten> knoten;
	private List<Kante> kanten;
	private Map<String, Integer> raums;
	private Map<String, Integer> hauses;
	//STANDORT ODER ETAGEN ID 
	private int id;
	private KartenTyp kartenTyp;
	
	private String SQL = "";
	
	public Karte() {
		//theoretisch kann man Karten abspeichern und mit Punkten einspeichern
	}
	public Karte(String pfad, String name) {
		knoten = new LinkedList<>();
		kanten = new LinkedList<>();
		img = new PNG(pfad);
		this.name = name;
		raums = new HashMap<>();
		hauses = new HashMap<>();
	}
	
	public Karte(String pfad, String name, int id, KartenTyp kartenTyp, int stockwerk) {
		this(pfad, name);
		this.id = id;
		this.kartenTyp = kartenTyp;
		SQL += "INSERT INTO " + kartenTyp.toString() + "(etagen_name, etage)"
				+ "\n Values (" +name + "," + stockwerk + ")";
		id += 1;
	}
	
	public void createRaum(String name, int id) {
		id += 1;
		raums.put(name, id);
	}
	public void addRaum(String name) {
		id += 1;
		raums.put(name, id);
	}
	
	public void createHaus(String name, int id) {
		id += 1;
		hauses.put(name, id);
	}
	public void addHaus(String name) {
		id += 1;
		hauses.put(name, id);
	}
	public void addKnoten(KnotenTyp kT, int x, int y) {
		Knoten k = new Knoten(kT, x, y, id);
		knoten.add(k);
	}
	
	public String generateSQL() {
		if(knoten.size() > 0) {
			SQL += "INSERT INTO knoten (x, y, raum_id, haus_id, standort_id, etagen_id)"
					+ "VALUES\n";
			SQL += knoten.get(0).getString();
			for(int i = 1; i < knoten.size(); i++) {
				SQL += ",\n" + knoten.get(i).getString();
			}
		}else
			return "";
		return SQL;
	}
	
	public Knoten getKnoten(int x, int y) {
		//HIER KOMMT NOCH LOGIK, DIE NÄCHSTEN PUNKT AUSWÄHLT
		return new Knoten(KnotenTyp.ETAGE, x, y);
	}
	
	public PNG getPNG() {
		return img;
	}
	
}
