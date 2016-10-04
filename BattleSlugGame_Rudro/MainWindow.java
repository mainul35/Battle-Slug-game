/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleSlugGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class MainWindow extends JFrame {

    private JPanel pnlPlayer1, pnlPlayer2;
    private JPanel mainPanel;
    private JPanel topPanel, topLeft, topRight;
    private JPanel bottomPanel, bottomLeft, bottomRight;
    private JButton btnshowPlayer1, btnShowPlayer2,btnPositionSlugP1, btnHidePlayer1, btnHidePlayer2, btnPositionSlugP2;
    private JLabel lblNameP1, lblTotalHitP1, lblTotalMissP1, lblTotalCountP1, lblNameP2, lblTotalHitP2, lblTotalMissP2, lblTotalCountP2;
    private int playerTurn = 1;
    private GenerateRandomSlugs grs = new GenerateRandomSlugs();
    private static HashMap<String, Integer> player1Slugs = new HashMap<String, Integer>();
    private static HashMap<String, Integer> player2Slugs = new HashMap<String, Integer>();
    private static int hitP1, hitP2, missP1, missP2, countTotalClicksP1, countTotalClicksP2;
    private byte[][] player1SlugPositions, player2SlugPositions;
    private JButton[][] player1GameButtons, player2GameButtons;
    private int clickP1 = 0, clickP2=0;
    
    public MainWindow() {
        pnlPlayer1 = new JPanel(new GridLayout(12, 12));
        pnlPlayer2 = new JPanel(new GridLayout(12, 12));
        topPanel = new JPanel(new GridLayout(0, 2));
        topLeft = new JPanel(new GridLayout(0, 3));
        topRight = new JPanel(new GridLayout(0, 3));
        
        btnshowPlayer1 = new JButton("Show Slugs Player 1");
        btnShowPlayer2 = new JButton("Show Slugs Player 2");
        btnHidePlayer1 = new JButton("Hide Slugs Player 1");
        btnHidePlayer2 = new JButton("Hide Slugs Player 2");
        btnPositionSlugP1 = new JButton("Position the slug");
        btnPositionSlugP2 = new JButton("Position the slug");
        btnshowPlayer1.setBounds(10, 10, 200, 30);
        btnShowPlayer2.setBounds(10, 10, 200, 30);
        btnHidePlayer1.setBounds(10, 10, 230, 30);
        btnHidePlayer2.setBounds(10, 10, 230, 30);
        
        topLeft.add(btnPositionSlugP1);
        topLeft.add(btnshowPlayer1);
        topLeft.add(btnHidePlayer1);
        topRight.add(btnShowPlayer2);
        topRight.add(btnHidePlayer2);
        topRight.add(btnPositionSlugP2);
        
        topPanel.add(topLeft);
        topPanel.add(topRight);

        bottomPanel = new JPanel(new GridLayout(0, 2));
        bottomLeft = new JPanel(new GridLayout(3, 0));
        bottomRight = new JPanel(new GridLayout(3, 0));

        lblNameP1 = new JLabel("Player 1");
        lblTotalHitP1 = new JLabel();
        lblTotalMissP1 = new JLabel();
        lblTotalCountP1 = new JLabel();

        bottomLeft.add(lblNameP1);
        bottomLeft.add(lblTotalHitP1);
        bottomLeft.add(lblTotalMissP1);
        bottomLeft.add(lblTotalCountP1);

        lblNameP2 = new JLabel("Player 2");
        lblTotalHitP2 = new JLabel();
        lblTotalMissP2 = new JLabel();
        lblTotalCountP2 = new JLabel();

        bottomRight.add(lblNameP2);
        bottomRight.add(lblTotalHitP2);
        bottomRight.add(lblTotalMissP2);
        bottomRight.add(lblTotalCountP2);

        bottomPanel.add(bottomLeft);
        bottomPanel.add(bottomRight);
        this.setLayout(new BorderLayout());
        this.add(bottomPanel, BorderLayout.SOUTH);

        player1GameButtons = new JButton[12][12];
        player2GameButtons = new JButton[12][12];
        player1SlugPositions = new byte[12][12];
        player2SlugPositions = new byte[12][12];


        btnPositionSlugP1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                player1Slugs = grs.generateSlugLocations(player1Slugs, ++clickP1);
                player1SlugPositions = grs.setSlugsToButtons(player1SlugPositions, player1Slugs);
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
                        if (player1SlugPositions[x][y] != 0) {
                            player1GameButtons[x][y].setText("" + player1SlugPositions[x][y]);
                        }
                    }
                }
            }
        });

        btnPositionSlugP2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                player2Slugs = grs.generateSlugLocations(player2Slugs, ++clickP2);
                player2SlugPositions = grs.setSlugsToButtons(player2SlugPositions, player2Slugs);
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
                        if (player2SlugPositions[x][y] != 0) {
                            player2GameButtons[x][y].setText("" + player2SlugPositions[x][y]);
                        }
                    }
                }
            }
        });

        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 12; y++) {
                player2GameButtons[x][y] = new JButton("  ");
                player1GameButtons[x][y] = new JButton("  ");
            }
        }
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 12; y++) {
//                player2GameButtons[x][y] = new JButton("  ");
                player2GameButtons[x][y].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        
                        // TODO Auto-generated method stub
                        if (playerTurn == 1) {
                            for (int x = 0; x < 12; x++) {
                                for (int y = 0; y < 12; y++) {
                                    if (e.getSource() == player2GameButtons[x][y]) {

                                        if (player1SlugPositions[x][y] != 0) {
                                            System.out.println("Hit player 1");
                                            if (player1GameButtons[x][y].getBackground() != Color.BLACK) {
                                                hitP2++;
                                            }else{
                                                missP2++;
                                            }
                                            player1GameButtons[x][y].setBackground(Color.BLACK);
//                                            JOptionPane.showMessageDialog(null, "Hit player 1");
                                        } else {
                                            System.out.println("Missed player 2");
                                            //										JOptionPane.showMessageDialog(null, "Missed player 2 ");
                                            missP2++;
                                        }
                                        countTotalClicksP2++;
                                        playerTurn = 2;
                                    }

                                    lblTotalHitP1.setText("Total Hits " + hitP2);
                                    lblTotalMissP1.setText("Total Miss " + missP2);
                                    lblTotalCountP1.setText("Total Counts " + countTotalClicksP2);

                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "It's Player 2's Turn");
                        }
                        try{
                            String s = "";
                            if (hitP2 >= 25) {
                                s = "Player 2 wins\n"
                                        + "Player 1 total hits :\t" + hitP1
                                        + "\nPlayer 2 total hits :\t" + hitP2
                                        + "\nPlayer 1 total miss :\t" + missP1 + "\n"
                                        + "Player 2 total miss :\t" + missP2 + "\n"
                                        + "Player 1 total clicks :\t" + countTotalClicksP1
                                        + "\nPlayer 2 total clicks :\t" + countTotalClicksP2
                                        + "\nWinning percentage for player 1 :\t" + ((hitP1 * 100) / (countTotalClicksP1))
                                        + "\nWinning percentage for player 2 :\t" + ((hitP2 * 100) / (countTotalClicksP2));

                                JOptionPane.showMessageDialog(null, s);
                            }else if(hitP1 >= 25){
                                s = "Player 2 wins\n"
                                            + "Player 1 total hits :\t" + hitP1
                                            + "\nPlayer 2 total hits :\t" + hitP2
                                            + "\nPlayer 1 total miss :\t" + missP1 + "\n"
                                            + "Player 2 total miss :\t" + missP2 + "\n"
                                            + "Player 1 total clicks :\t" + countTotalClicksP1
                                            + "\nPlayer 2 total clicks :\t" + countTotalClicksP2
                                            + "\nWinning percentage for player 1 :\t" + ((hitP1 * 100) / (countTotalClicksP1))
                                            + "\nWinning percentage for player 2 :\t" + ((hitP2 * 100) / (countTotalClicksP2));
                                JOptionPane.showMessageDialog(null, s);
                            }

                        }catch(ArrayIndexOutOfBoundsException ae){
                        
                        }
                    }
                });
                pnlPlayer2.add(player2GameButtons[x][y]);
            }
        }

        btnshowPlayer1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
                        if (player1SlugPositions[x][y] != 0) {
                            player1GameButtons[x][y].setText("" + player1SlugPositions[x][y]);
                        }
                    }
                }
            }
        });

        btnHidePlayer1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
//                        if (player1SlugPositions[x][y] != 0) {
                        player1GameButtons[x][y].setText("  ");
//                        }
                    }
                }
            }
        });

        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 12; y++) {

//                player1GameButtons[x][y] = new JButton("  ");
                player1GameButtons[x][y].addActionListener(new ActionListener() {
                        

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        if (playerTurn == 2) {
                            for (int x = 0; x < 12; x++) {
                                for (int y = 0; y < 12; y++) {
                                    if (e.getSource() == player1GameButtons[x][y]) {

                                        if (player2SlugPositions[x][y] != 0) {
                                            System.out.println("Hit player 2");
//                                            JOptionPane.showMessageDialog(null, "Hit player 2");
                                            if (player2GameButtons[x][y].getBackground() != Color.BLACK) {
                                                hitP1++;
                                            }else{
                                                missP1++;
                                            }
                                            player2GameButtons[x][y].setBackground(Color.BLACK);
                                            
                                        } else {
                                            System.out.println("Missed Player 1");
//										JOptionPane.showMessageDialog(null, "Missed player 1");
                                            missP1++;
                                        }
                                        countTotalClicksP1++;
                                        playerTurn = 1;
                                    }
                                    lblTotalHitP2.setText("Total Hits " + hitP1);
                                    lblTotalMissP2.setText("Total Miss " + missP1);
                                    lblTotalCountP2.setText("Total Counts " + countTotalClicksP1);

                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "It's Player 1's Turn.");
                        }
                        String s = "";
                        try{
                            if (hitP2 >= 25) {
                                s = "Player 2 wins\n"
                                        + "Player 1 total hits :\t" + hitP1
                                        + "\nPlayer 2 total hits :\t" + hitP2
                                        + "\nPlayer 1 total miss :\t" + missP1 + "\n"
                                        + "Player 2 total miss :\t" + missP2 + "\n"
                                        + "Player 1 total clicks :\t" + countTotalClicksP1
                                        + "\nPlayer 2 total clicks :\t" + countTotalClicksP2
                                        + "\nWinning percentage for player 1 :\t" + ((hitP1 * 100) / (countTotalClicksP1))
                                        + "\nWinning percentage for player 2 :\t" + ((hitP2 * 100) / (countTotalClicksP2));
                            JOptionPane.showMessageDialog(null, s);
                            }else if(hitP1 >=25){
                                s = "Player 2 wins\n"
                                            + "Player 1 total hits :\t" + hitP1
                                            + "\nPlayer 2 total hits :\t" + hitP2
                                            + "\nPlayer 1 total miss :\t" + missP1 + "\n"
                                            + "Player 2 total miss :\t" + missP2 + "\n"
                                            + "Player 1 total clicks :\t" + countTotalClicksP1
                                            + "\nPlayer 2 total clicks :\t" + countTotalClicksP2
                                            + "\nWinning percentage for player 1 :\t" + ((hitP1 * 100) / (countTotalClicksP1))
                                            + "\nWinning percentage for player 2 :\t" + ((hitP2 * 100) / (countTotalClicksP2));
                            JOptionPane.showMessageDialog(null, s);
                            }
                        }catch(ArrayIndexOutOfBoundsException ae){}
                        
                    }

                });
                pnlPlayer1.add(player1GameButtons[x][y]);
            }
        }
        btnShowPlayer2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
                        if (player2SlugPositions[x][y] != 0) {
                            player2GameButtons[x][y].setText("" + player2SlugPositions[x][y]);
                        }
                    }
                }
            }
        });

        btnHidePlayer2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
//                        if (player1SlugPositions[x][y] != 0) {
                        player2GameButtons[x][y].setText("  ");
//                        }
                    }
                }
            }
        });

        this.setTitle("Battle Slug Game");
        this.add(pnlPlayer1, BorderLayout.WEST);

        this.add(pnlPlayer2, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        this.setDefaultCloseOperation(
                3);

        this.setSize(
                1200, 700);
    }
}
