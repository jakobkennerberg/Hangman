package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import server.ClientHandler;

public class ViewerOnlineList extends JPanel implements MouseListener {
	private ContinueListener continueListener;
	private Controller controller;
	private JLabel lblHeader = new JLabel("Choose a player     ", SwingConstants.CENTER);
	private JLabel lblOnline = new JLabel("Online");
	private JLabel lblSettings = new JLabel("");	//Should show what gamemode the player has chosen.
	private JButton btnBack = new JButton("<-- Back");
	private JButton btnInvite = new JButton("Invite");
	private ButtonGroup bg = new ButtonGroup();
	private JPanel pnlOnlineList;

	public ViewerOnlineList() {
		setPreferredSize(new Dimension(1200, 800));
		setLayout(new BorderLayout());
		add(titlePanel(), BorderLayout.NORTH);
		add(mainPanel(), BorderLayout.CENTER);
	}

	private JPanel titlePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		Font titleFont = new Font("SansSerif", Font.PLAIN, 100);
		Font btnFont = new Font("SansSerif", Font.BOLD, 30);

		panel.setBackground(Color.DARK_GRAY);
		lblHeader.setFont(titleFont);
		lblHeader.setForeground(Color.WHITE);

		btnBack.setFont(btnFont);
		btnBack.setForeground(Color.WHITE);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);

		panel.add(btnBack, BorderLayout.WEST);
		panel.add(lblHeader, BorderLayout.CENTER);
		return panel;
	}

	private JPanel mainPanel() {
		JPanel main = new JPanel();
		main.setLayout(null);

		main.setBackground(Color.DARK_GRAY);
		Font btnFont = new Font("SansSerif", Font.BOLD, 30);

		pnlOnlineList = new JPanel();
		pnlOnlineList.setLayout(new GridLayout(100,1)); // Change from gridLayout to something better?? + change the values to onlinelist.size to
													// make it only do as many as needed.

		JScrollPane scroll = new JScrollPane(pnlOnlineList);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(100, 50, 400, 500);


		btnInvite.setBounds(800, 400, 200, 100);
		btnInvite.setFont(btnFont);
		
		lblSettings.setBounds(650, 20, 400, 100);
		lblSettings.setFont(btnFont);
		lblSettings.setForeground(Color.WHITE);

		getGameModeText();
		main.add(lblSettings);
		main.add(btnInvite);
		main.add(scroll);

		return main;
	} 
	
	public void updateOnlineList(ArrayList<ClientHandler> onlineList) {
		pnlOnlineList.removeAll();
		for (int i = 0; i < onlineList.size(); i++) { // Change value to onlinelist.size so that it can only show as many as needed
			JRadioButton btn = new JRadioButton("FIX USERNAME HERE");// Change so that the server can read how many buttons it needs.
			btn.setSize(new Dimension(400, 60));
			bg.add(btn);
			pnlOnlineList.add(btn);
		}
	}
	
	public void setGameModeText(String text) {
		lblSettings.setText("Game Mode: " + text );
	}
	
	public String getGameModeText() {
		return lblSettings.getText();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setListener(ContinueListener listener) {
		continueListener = listener;
	}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
		if(e.getComponent()==btnBack) {
			btnBack.setForeground(Color.RED);
		}
	}

	public void mouseExited(MouseEvent e) {
		if(e.getComponent()==btnBack) {
			btnBack.setForeground(Color.WHITE);
		}
	}

	public void mouseClicked(MouseEvent e) {}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Test of online list");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ViewerOnlineList());
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
