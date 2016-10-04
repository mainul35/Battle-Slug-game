package GameCode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PlayerGUI {
	
//Player Changing
//	static int player = 1;
		
	private static JPanel pnlFirst;
	private static JPanel pnlSecond;
	static JPanel pnlPlyTotal;	//panel For Player
	private static JPanel pnlSlugs1Total;	//panel For player1 Slugs
	private static JPanel pnlSlugs2Total;	//panel For player2 Slugs
	
	static JPanel pnlOthers = new JPanel();
	
	private static JButton placePlayer1Slug;
	private static JButton placePlayer2Slug;
	private static JButton showPlayer1Slugs;
	private static JButton hidePlayer1Slugs;
	private static JButton showPlayer2Slugs;
	private static JButton hidePlayer2Slugs;
	private JPanel pnlTop = new JPanel();
	
	
	public JButton getPlayer2PlacementButton(){
		return this.placePlayer1Slug;
	}
	
	public JButton getPlayer1PlacementButton(){
		return this.placePlayer2Slug;
	}
	
	PlayerGUI(){
	
		
	//First Player Side 
		pnlFirst = new JPanel();//For designing the First Player side layout

	//Second Player Side 
		pnlSecond = new JPanel();//For designing the Second Player side layout

	//Adding AllPlayer to the pnlOthers.
		pnlPlyTotal = new JPanel();

		pnlPlyTotal.setLayout(new GridLayout(1, 3, 40, 40));
		pnlPlyTotal.add(pnlFirst);
//		pnlPlyTotal.add(btnRestart);
		pnlPlyTotal.add(pnlSecond);
		
//	Showing slugs
		showPlayer1Slugs = new JButton();
		showPlayer1Slugs.setText("Show Player 1 Slugs");
		showPlayer1Slugs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(int x=0; x<12; x++){
					for(int y =0; y<12; y++){
						if(MainGameGUI.getBoardTwoValues()[x][y]!=0){
							MainGameGUI.getBoardTwoButtons()[x][y].setText(""+MainGameGUI.getBoardTwoValues()[x][y]);
						}
					}
				}
			}
		});		
		
		showPlayer2Slugs = new JButton();
		showPlayer2Slugs.setText("Show Player 2 Slugs");
		showPlayer2Slugs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int x=0; x<12; x++){
					for(int y =0; y<12; y++){
						if(MainGameGUI.getBoardOneValues()[x][y]!=0){
							MainGameGUI.getBoardOneButtons()[x][y].setText(""+MainGameGUI.getBoardOneValues()[x][y]);
						}
					}
				}
			}
		});
		hidePlayer1Slugs = new JButton();
		hidePlayer1Slugs.setText("Hide Player 1 Slugs");
		hidePlayer1Slugs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int x=0; x<12; x++){
					for(int y =0; y<12; y++){
						MainGameGUI.getBoardTwoButtons()[x][y].setText("  ");
					}
				}
				
			}
		});
		hidePlayer2Slugs = new JButton();
		hidePlayer2Slugs.setText("Hide Player 2 Slugs");
		hidePlayer2Slugs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int x=0; x<12; x++){
					for(int y =0; y<12; y++){
						MainGameGUI.getBoardOneButtons()[x][y].setText("  ");
					}
				}
			}
		});
		
		placePlayer1Slug = new JButton("Place Player 1 Slug");
		placePlayer2Slug = new JButton("Place Player 2 Slug");
		
		//For player 1 slugs
		pnlSlugs1Total = new JPanel();
		pnlSlugs1Total.setLayout(new GridLayout(1, 3, 40, 80));
		
		pnlSlugs1Total.add(placePlayer1Slug);
		pnlSlugs1Total.add(showPlayer1Slugs);
		pnlSlugs1Total.add(hidePlayer1Slugs);
		
		//For player 2 slugs
		pnlSlugs2Total = new JPanel();
		pnlSlugs2Total.setLayout(new GridLayout(1, 3, 30, 80));
		
		pnlSlugs2Total.add(showPlayer2Slugs);
		pnlSlugs2Total.add(hidePlayer2Slugs);
		pnlSlugs2Total.add(placePlayer2Slug);
		
		//All Slugs are added to this other panel
//		pnlOthers = new JPanel();
		pnlOthers.setLayout(new GridLayout(1, 6, 10, 0));
		pnlOthers.add(pnlSlugs1Total);
		pnlOthers.add(pnlSlugs2Total);
	}
}
