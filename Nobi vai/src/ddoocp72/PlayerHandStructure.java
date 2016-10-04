/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddoocp72;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class PlayerHandStructure extends JPanel{
    
    private JButton btnPutSlugsOnBoard;
    private JButton btnShowSlugsOnBoard;
    private JButton btnHideSlugsFromBoard;
    private JPanel pnlCenter;
    private JPanel pnlTopPanel;
    private JPanel pnlButtonPanel;
    private JLabel lblPlayerHand;
    private int playerState;
    private JButton btnGrids[][];
    private byte[][] slugs;
    private int clickPutCount;
    private HashMap<String, Integer> playerSlugs = new HashMap<String, Integer>();
    private GenerateRandomSlugs grs = new GenerateRandomSlugs();
    private ClickEvent ce = new ClickEvent();
    
    public int getPlayerHand(){
        return this.playerState;
    }
    
    public JButton[][] getPlayerGrids(){
        return this.btnGrids;
    }
    
    public byte[][] getPlayerSlugs(){
        return this.slugs;
    }
    
    public PlayerHandStructure(int player) {
        this.playerState = player;
        this.setLayout(new BorderLayout());
        btnGrids = new JButton[12][12];
        slugs = new byte[12][12];
        btnPutSlugsOnBoard = new JButton("Put Slugs");
        btnPutSlugsOnBoard.setBackground(Color.red);
        btnPutSlugsOnBoard.setForeground(Color.WHITE);
        btnPutSlugsOnBoard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                playerSlugs = grs.generateSlugLocations(playerSlugs, ++clickPutCount);
                slugs = grs.setSlugsToButtons(slugs, playerSlugs);
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
                        if (slugs[x][y] != 0) {
                            btnGrids[x][y].setText("" + slugs[x][y]);
                        }
                    }
                }
            }
        });

        btnShowSlugsOnBoard = new JButton("Show Slugs");
        btnShowSlugsOnBoard.setBackground(Color.red);
        btnShowSlugsOnBoard.setForeground(Color.WHITE);
        btnShowSlugsOnBoard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
                        if (slugs[x][y] != 0) {
                            btnGrids[x][y].setText("" + slugs[x][y]);
                        }
                    }
                }
            }
        });
        btnHideSlugsFromBoard = new JButton("Hide Slugs");
        btnHideSlugsFromBoard.setBackground(Color.red);
        btnHideSlugsFromBoard.setForeground(Color.WHITE);
        btnHideSlugsFromBoard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                for (int x = 0; x < 12; x++) {
                    for (int y = 0; y < 12; y++) {
                        if (slugs[x][y] != 0) {
                            btnGrids[x][y].setText("  ");
                        }
                    }
                }
            }
        });
        pnlButtonPanel = new JPanel(new GridLayout(0, 3));
        pnlButtonPanel.add(btnPutSlugsOnBoard);
        pnlButtonPanel.add(btnShowSlugsOnBoard);
        pnlButtonPanel.add(btnHideSlugsFromBoard);
        
        pnlTopPanel = new JPanel(new GridLayout());
        lblPlayerHand = new JLabel("Player Hand "+playerState);
        pnlTopPanel.add(lblPlayerHand);
        
        pnlCenter = new JPanel(new GridLayout(12, 12));
        
        for(int x = 0; x<12; x++){
            for(int y = 0; y<12; y++){
                btnGrids[x][y] = new JButton("  ");
                btnGrids[x][y].addActionListener(ce);
                pnlCenter.add(btnGrids[x][y]);
            }
        }
        
        
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlButtonPanel, BorderLayout.SOUTH);
        this.add(pnlTopPanel, BorderLayout.NORTH);
    }
}
