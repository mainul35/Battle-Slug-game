package GameCode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.*;

public class MainGameGUI extends JFrame {

	private static JPanel pnlOneButtons;
	private static JPanel pnlSecondButtons;
	private static JPanel pnlBtnTotal; // panel For game button

	private int player1Click=0, player2Click=0;
	private HashMap<String, Integer> player1Slugs = new HashMap<String, Integer>();
	private HashMap<String, Integer> player2Slugs = new HashMap<String, Integer>();
	
	private GenerateRandomSlugs grs = new GenerateRandomSlugs();
	private PlayerGUI pg = new PlayerGUI();
	private ClickActions ca = new ClickActions();
	private static JPanel pnlTotal;

	private static JButton[][] boardOneButton;
	private static JButton[][] boardSecondButton;

	private static byte[][] boardOneValues;
	private static byte[][] boardSecondValues;

	public static byte[][] getBoardOneValues() {
		return boardOneValues;
	}

	public static byte[][] getBoardTwoValues() {
		return boardSecondValues;
	}

	public static JButton[][] getBoardOneButtons() {
		return boardOneButton;
	}

	public static JButton[][] getBoardTwoButtons() {
		return boardSecondButton;
	}

	MainGameGUI() {

		// Creating Game Board GUI
		this.setTitle("Battle Slugs Game");
		this.setBounds(0, 40, 1200, 700);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnlOneButtons = new JPanel();
		JPanel pnlSecondButtons = new JPanel();
		JPanel pnlBtnTotal = new JPanel();
		JPanel pnlPlyTotal = new JPanel();
		JPanel pnlSlugsTotal = new JPanel();
		JPanel pnlTotal = new JPanel();

		boardOneButton = new JButton[12][12];
		boardSecondButton = new JButton[12][12];
		boardOneValues = new byte[12][12];
		boardSecondValues = new byte[12][12];

		// initializing the int arrays
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 12; y++) {
				boardOneValues[x][y] = 0;
				boardSecondValues[x][y] = 0;
			}
		}

		// inserting values into 12x12 int arrays
		pg.getPlayer1PlacementButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				player1Slugs = grs.generateSlugLocations(player1Slugs, ++player1Click);
				boardOneValues = grs.setSlugsToButtons(boardOneValues, player1Slugs);
				for(int x=0; x<12; x++){
					for(int y =0; y<12; y++){
						if(MainGameGUI.getBoardOneValues()[x][y]!=0){
							MainGameGUI.getBoardOneButtons()[x][y].setText(""+MainGameGUI.getBoardOneValues()[x][y]);
						}
					}
				}
			}
		});

		pg.getPlayer2PlacementButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				player2Slugs = grs.generateSlugLocations(player2Slugs, ++player2Click);
				boardSecondValues = grs.setSlugsToButtons(boardSecondValues, player2Slugs);
				for(int x=0; x<12; x++){
					for(int y =0; y<12; y++){
						if(MainGameGUI.getBoardTwoValues()[x][y]!=0){
							MainGameGUI.getBoardTwoButtons()[x][y].setText(""+MainGameGUI.getBoardTwoValues()[x][y]);
						}
					}
				}
			}
		});

		// Checking if the slugs has existence
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 12; y++) {
				System.out.print(boardOneValues[x][y]);
			}
			System.out.println();
		}
		System.out.println();
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 12; y++) {
				System.out.print(boardSecondValues[x][y]);
			}
			System.out.println();
		}

		// Making an object of PlayerGUI class for using pnlOthers.

		// Button Design.
		pnlBtnTotal.setLayout(new BorderLayout());
		pnlBtnTotal.add(pnlOneButtons, BorderLayout.EAST);
		pnlBtnTotal.add(pnlSecondButtons, BorderLayout.WEST);

		// Player Design.
		pnlPlyTotal.setLayout(new BorderLayout());

		// Total Panel Design.
		pnlTotal.setLayout(new BorderLayout());
		pnlTotal.add(pg.pnlPlyTotal, BorderLayout.NORTH);
		pnlTotal.add(pnlBtnTotal, BorderLayout.CENTER);
		pnlTotal.add(pg.pnlOthers, BorderLayout.SOUTH);

		this.add(pnlTotal);

		// Creating Game Board Button
		// For Player1 Board
		pnlOneButtons.setLayout(new GridLayout(12, 12));

		for (int col = 0; col < 12; col++) {
			for (int row = 0; row < 12; row++) {
				boardOneButton[col][row] = new JButton();
				boardOneButton[col][row].setText("  ");
				boardOneButton[col][row].addActionListener(ca);

				pnlOneButtons.add(boardOneButton[col][row]);
			}
		}
		// For Player2 Board
		pnlSecondButtons.setLayout(new GridLayout(12, 12));
		for (int col = 0; col < 12; col++) {
			for (int row = 0; row < 12; row++) {
				boardSecondButton[col][row] = new JButton();
				boardSecondButton[col][row].setText("  ");
				boardSecondButton[col][row].addActionListener(ca);
				pnlSecondButtons.add(boardSecondButton[col][row]);
			}
		}

	}
}