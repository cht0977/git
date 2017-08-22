import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PunkteEintragenGUI extends JFrame implements ActionListener {
	
	private Karte karte;
	private JComboBox<KnotenTyp> kTypAuswahl;
	private JButton addNode;
	private JButton addEdge;
	private JButton generateSQL;
	private KartenTyp kartenTyp;
	private int stockwerk;
	private boolean schonAngelegt;
	private int id;
	private int mapID;
	private String name;
	private int raumID = -1;
	private int hausID = -1;
	
	public PunkteEintragenGUI(String pfad, String name) {
		karte = new Karte(pfad, name);
		this.name = name;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel buttonLeiste = new JPanel();
		buttonLeiste.setLayout(new GridLayout(0, 2));
		kTypAuswahl = new JComboBox<>(KnotenTyp.values());
		buttonLeiste.add(kTypAuswahl);
		
		generateSQL = new JButton("generateSQL");
		generateSQL.setActionCommand("GENERIERE");
		generateSQL.addActionListener(this);
		buttonLeiste.add(generateSQL);
		
		JPanel hintergrund = new JPanel() {
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.drawImage(karte.getPNG().getImage(), 0, 0, this); // see javadoc for more info on the parameters            
		    }
		};
		hintergrund.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					if(kTypAuswahl.getSelectedItem().toString().equals("RAUM")) {
						String roomName = JOptionPane.showInputDialog("Raumname");
						if(raumID == -1) {
							raumID = Integer.parseInt(JOptionPane.showInputDialog("LetzteRaumID"));
							karte.createRaum(roomName, raumID);
							System.out.println("GEADDED");
							karte.addKnoten((KnotenTyp)kTypAuswahl.getSelectedItem(), e.getX(), e.getY());
							System.out.println("Knoten hinzugefügt: " + (KnotenTyp)kTypAuswahl.getSelectedItem());
							hintergrund.getGraphics().fillOval(e.getX(), e.getY(), 10, 10);
							return;
						}
						karte.addRaum(roomName);
					}
					karte.addKnoten((KnotenTyp)kTypAuswahl.getSelectedItem(), e.getX(), e.getY());
					System.out.println("Knoten hinzugefügt: " + (KnotenTyp)kTypAuswahl.getSelectedItem());
					hintergrund.getGraphics().fillOval(e.getX(), e.getY(), 10, 10);
				}
			}	
		});
		hintergrund.setPreferredSize(new Dimension(karte.getPNG().getWidth(), karte.getPNG().getHeight()));
		this.add(hintergrund, BorderLayout.NORTH);
		this.add(buttonLeiste, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}
	
	public PunkteEintragenGUI(String pfad, String name, KartenTyp kartenTyp, boolean schonAngelegt, int id, int mapID, int stockwerk) {
		this(pfad, name);
		this.kartenTyp = kartenTyp;
		this.schonAngelegt = schonAngelegt;
		this.id = id;
		this.stockwerk = stockwerk;
		this.mapID = mapID;
		karte = new Karte(pfad, name, id, kartenTyp,stockwerk);
		this.name = name;
	}
	
	public PunkteEintragenGUI(String pfad, String name, KartenTyp kartenTyp, boolean schonAngelegt, int mapID, int id) {
		this(pfad, name);
		this.kartenTyp = kartenTyp;
		this.schonAngelegt = schonAngelegt;
		this.id = id;
		this.mapID = mapID;
		karte = new Karte(pfad, name, id, kartenTyp, 0);
		this.name = name;
	}
	
	public String generateSQL() {
		String sql = "";
		sql += "INSERT INTO map (map_name) VALUES (" + name + ");\n";
		if(!schonAngelegt) {
			if(kartenTyp.toString().equals("standort"))
				sql += "INSERT INTO standort (standort_name, map_id) VALUES (" + name + "," +(mapID++) + ");\n";
			else
				sql += "INSERT INTO etage (etagen_name, map_id, etage) VALUES (" + name + "," +(mapID++) + "," + stockwerk + ");\n";
		}
		sql += karte.generateSQL();
		return sql;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("GENERIERE")) {
			System.out.println(karte.generateSQL());
		}
		
	}
	
	public static void main(String[] args) {
		new PunkteEintragenGUI("D:\\Bilder\\test.png", "utfp");
	}
}
