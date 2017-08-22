import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AngabenGUI extends JFrame implements ActionListener{

	private JTextField pfad;
	private JComboBox<KartenTyp> kartenArt;
	private JTextField kartenName;
	private JLabel existiertSQL;
	private JCheckBox existiert;
	private JTextField id;
	private JTextField etage;
	private JTextField mapID;
	private JButton go;
	
	public AngabenGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(9, 0));
		
		pfad = new JTextField("Bildpfad");
		this.add(pfad);
		kartenArt = new JComboBox();
		kartenArt.addItem(KartenTyp.standort);
		kartenArt.addItem(KartenTyp.etage);
		kartenArt.setActionCommand("KOMMANDO");
		kartenArt.addActionListener(this);
		this.add(kartenArt);
		etage = new JTextField("Etage");
		this.add(etage);
		kartenName = new JTextField("kartenName");
		this.add(kartenName);
		existiertSQL = new JLabel("Schon angelegt?");
		this.add(existiertSQL);
		existiert = new JCheckBox();
		existiert.setActionCommand("CHECKBOX");
		existiert.addActionListener(this);
		this.add(existiert);
		id = new JTextField("ID");
		this.add(id);
		go = new JButton("GO");
		go.setActionCommand("GO");
		go.addActionListener(this);
		mapID = new JTextField("letzteMapID");
		this.add(mapID);
		this.add(go);
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("GO")) {
			new PunkteEintragenGUI(pfad.getText(), kartenName.getText());
		}
		if(e.getActionCommand().equals("CHECKBOX")) {
			if(existiert.isSelected()) {
				id.setText("ID");
			}else {
				id.setText("letzteID");
			}
		}
		if(e.getActionCommand().equals("KOMMANDO")) {
			System.out.println("TEST");
			System.out.println(((KartenTyp)kartenArt.getSelectedItem()));
			if(((KartenTyp)kartenArt.getSelectedItem()).toString().equals("standort")) {
				this.remove(etage);
				this.pack();
			}else {
				this.add(etage, 2);
				this.pack();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new AngabenGUI();
	}
	
}
