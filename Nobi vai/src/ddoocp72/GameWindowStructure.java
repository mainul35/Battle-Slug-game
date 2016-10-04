/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddoocp72;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author root
 */
public class GameWindowStructure extends JFrame{

    static PlayerHandStructure phs1 = new PlayerHandStructure(1);
    static PlayerHandStructure phs2 = new PlayerHandStructure(2);

    public GameWindowStructure() throws HeadlessException {
        this.setSize(1200, 800);
        this.setLayout(new BorderLayout());
        this.add(phs1, BorderLayout.WEST);
        this.add(phs2, BorderLayout.EAST);
        this.setTitle("Battle Slug");
    }
    
}