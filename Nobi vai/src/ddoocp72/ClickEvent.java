/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddoocp72;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ClickEvent implements ActionListener {

    static int player1Hits = 0, player1Miss = 0, player2Hits = 0, player2Miss = 0, player1TotalClicks = 1,
            player2TotalClicks = 1;

    private static byte turn = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            for (int x = 0; x < 12; x++) {
                for (int y = 0; y < 12; y++) {

                    if (e.getSource() == GameWindowStructure.phs2.getPlayerGrids()[x][y]) {
                        if (turn == 1) {
                            if (GameWindowStructure.phs1.getPlayerSlugs()[x][y] != 0) {
//							MainGameGUI.getBoardTwoButtons()[x][y].setEnabled(false);
                                System.out.println("Hit player 2");
                                JOptionPane.showMessageDialog(null, "Hit player 2");
                                if (GameWindowStructure.phs1.getPlayerGrids()[x][y].getBackground() != Color.BLUE) {
                                    player2Hits++;
                                } else {
                                    player2Miss++;
                                }
                                GameWindowStructure.phs1.getPlayerGrids()[x][y].setBackground(Color.BLUE);
                            } else {
                                System.out.println("Missed player 2");
                                JOptionPane.showMessageDialog(null, "Missed player 2 ");
                                player2Miss++;
                            }
                            player2TotalClicks++;
                            turn = 0;
                        } else {
                            JOptionPane.showMessageDialog(null, "It's turn for player 1");
                        }

                    }
                    if (e.getSource() == GameWindowStructure.phs1.getPlayerGrids()[x][y]) {
                        if (turn == 0) {
                            if (GameWindowStructure.phs2.getPlayerSlugs()[x][y] != 0) {
//							MainGameGUI.getBoardOneButtons()[x][y].setEnabled(false);
                                System.out.println("Hit player 1");
                                JOptionPane.showMessageDialog(null, "Hit player 1");
                                if (GameWindowStructure.phs2.getPlayerGrids()[x][y].getBackground() != Color.BLUE) {
                                    player1Hits++;
                                } else {
                                    player1Miss++;
                                }
                                GameWindowStructure.phs2.getPlayerGrids()[x][y].setBackground(Color.BLUE);
                            } else {
                                System.out.println("Missed Player 1");
                                JOptionPane.showMessageDialog(null, "Missed player 1");
                                player1Miss++;
                            }
                            player1TotalClicks++;
                            turn = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "It's turn for player 2");
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
        }
        try {
            if (player1Hits >= 25) {
                JOptionPane.showMessageDialog(null,
                        "Player 1 wins\n" + "Player 1 total hits :\t" + player1Hits + "\nPlayer 2 total hits :\t"
                        + player2Hits + "\nPlayer 1 total miss :\t" + player1Miss
                        + "\nPlayer 2 total miss :\t" + player2Miss + "\n" + "Player 1 total clicks :\t"
                        + player1TotalClicks + "\nPlayer 2 total clicks :\t" + player2TotalClicks
                        + "\nWinning percentage for player 1 :\t" + ((player1Hits * 100) / player1TotalClicks)
                        + "\nWinning percentage for player 2 :\t" + ((player2Hits * 100) / player2TotalClicks));
            } else if (player2Hits >= 25) {
                JOptionPane.showMessageDialog(null,
                        "Player 2 wins\n" + "Player 1 total hits :\t" + player1Hits + "\nPlayer 2 total hits :\t"
                        + player2Hits + "\nPlayer 1 total miss :\t" + player1Miss + "\n"
                        + "\nPlayer 2 total miss :\t" + player2Miss + "\n" + "Player 1 total clicks :\t"
                        + player1TotalClicks + "\nPlayer 2 total clicks :\t" + player2TotalClicks
                        + "\nWinning percentage for player 1 :\t" + ((player1Hits * 100) / (player1TotalClicks))
                        + "\nWinning percentage for player 2 :\t" + ((player2Hits * 100) / (player2TotalClicks)));
            }
        } catch (ArithmeticException ae) {
        }
    }
}
