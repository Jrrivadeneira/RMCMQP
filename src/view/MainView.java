package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6313156717813295316L;

	JTextField tfCommand = new JTextField();
	JTextArea taCommandArea = new JTextArea();

	Color bg = Color.BLACK;
	Color fg = Color.LIGHT_GRAY;
	Font f = new Font("Monospaced", Font.BOLD, 16);
	BrokenArrow field;

	public MainView() {

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// buildUI();
		newUI();
		this.setVisible(true);
		BrokenArrow.run = true;
	}

	private JPanel makeMainPanelThree() {
		GridLayout hsplit = new GridLayout(2, 1);
		GridLayout hsplit3 = new GridLayout(3, 1);
		GridLayout vsplit = new GridLayout(1, 2);
		vsplit.setHgap(10);
		hsplit.setVgap(10);
		hsplit3.setVgap(10);
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		rightPanel.setLayout(hsplit);
		rightPanel.add(new JButton("SIX"));

		JPanel auxillaryCommands = new JPanel();
		auxillaryCommands.setLayout(vsplit);
		JPanel rightAux = new JPanel();
		rightAux.setLayout(hsplit3);
		rightAux.add(new JButton("10"));
		rightAux.add(new JButton("11"));
		rightAux.add(new JButton("12"));
		JPanel leftAux = new JPanel();

		leftAux.setLayout(hsplit3);
		JPanel high = new JPanel(), low = new JPanel();
		high.setLayout(hsplit);
		low.setLayout(hsplit);
		high.add(new JButton("16"));
		high.add(new JButton("17"));
		low.add(new JButton("18"));
		low.add(new JButton("19"));

		leftAux.add(high);
		leftAux.add(low);

		JButton seventeen = new JButton("Fifteen.");

		leftAux.add(seventeen);

		auxillaryCommands.add(leftAux);
		auxillaryCommands.add(rightAux);

		rightPanel.add(auxillaryCommands);

		return rightPanel;
	}

	private JPanel makeMainPanelTwo() {
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(new GridLayout(2, 1));

		// For section 4
		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new BorderLayout());
		this.tfCommand.setSize(300, 32);
		this.tfCommand.setPreferredSize(new Dimension(300, 30));
		this.tfCommand.setBorder(new EmptyBorder(0, 0, 0, 0));

		this.tfCommand.setBackground(bg);
		this.tfCommand.setForeground(fg);
		this.tfCommand.setFont(f);

		this.taCommandArea.setEditable(false);
		this.taCommandArea.setBackground(bg);
		this.taCommandArea.setForeground(fg);
		this.taCommandArea.setFont(f);
		this.taCommandArea.setText("Hello World\nAlice + Bob\nOK_COMPUTER");

		commandPanel.add(taCommandArea, BorderLayout.CENTER);
		commandPanel.add(this.tfCommand, BorderLayout.SOUTH);

		// for section 5
		JPanel movementButtons = new JPanel();
		GridLayout ThreeByThree = new GridLayout(3, 3);
		ThreeByThree.setHgap(10);
		ThreeByThree.setVgap(10);
		movementButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
		movementButtons.setLayout(ThreeByThree);

		movementButtons.add(new JButton("FORWARD LEFT"));
		movementButtons.add(new JButton("FORWARD"));
		movementButtons.add(new JButton("FORWARD RIGHT"));

		movementButtons.add(new JButton("LEFT"));
		movementButtons.add(new JButton("STOP"));
		movementButtons.add(new JButton("RIGHT"));

		movementButtons.add(new JButton("BACK LEFT"));
		movementButtons.add(new JButton("BACK"));
		movementButtons.add(new JButton("BACK RIGHT"));

		CenterPanel.add(commandPanel);

		CenterPanel.add(movementButtons);
		return CenterPanel;
	}

	private void newUI() {
		this.setBackground(Color.BLACK);
		// Field Panel (MAIN PANEL 1)
		field = new BrokenArrow();
		field.setBackground(Color.BLACK);

		// Main Center Panel Setup (MAIN PANEL 2)
		JPanel CenterPanel = this.makeMainPanelTwo();
		// MAIN PANEL 3
		JPanel rightPanel = this.makeMainPanelThree();
		// setting the main layout
		this.setLayout(new GridLayout(1, 3));

		// Adding the 3 main panels
		this.add(field);
		this.add(CenterPanel);
		this.add(rightPanel);

	}

	public void dispose() {
		this.field.act.kill();
		super.dispose();
	}

	public static void main(String[] args) {
		new MainView();
	}
}
