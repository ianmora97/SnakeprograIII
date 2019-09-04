/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JFrame;
import org.omg.PortableServer.AdapterActivatorOperations;

/**
 *
 * @author Ian Rodriguez
 */
public class View extends JFrame implements Observer {
    Model model;
    Controller control;
    
    GamePanel panel;
    
    BufferedImage bf;
    
    public View() {
        
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        panel = new GamePanel(model);
        this.setContentPane(panel);
        
        this.setBackground(Color.BLACK);
                
        bf = new BufferedImage(650, 650, BufferedImage.TYPE_INT_RGB);
        
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

            int tecla = e.getKeyCode();

            if ((tecla == KeyEvent.VK_LEFT) && (!model.RIGHT)) {
                model.LEFT = true;
                model.UP = false;
                model.DOWN = false;
            }
            if ((tecla == KeyEvent.VK_RIGHT) && (!model.LEFT)) {
                model.RIGHT = true;
                model.UP = false;
                model.DOWN = false;
            }

            if ((tecla == KeyEvent.VK_UP) && (!model.DOWN)) {
                model.UP = true;
                model.RIGHT = false;
                model.LEFT = false;
            }

            if ((tecla == KeyEvent.VK_DOWN) && (!model.UP)) {
                model.DOWN = true;
                model.RIGHT = false;
                model.LEFT = false;
            }
        }
        });
    }
    
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public Controller getControl() {
        return control;
    }

    public void setControl(Controller control) {
        this.control = control;
    }

    @Override
    public void update(Observable o, Object arg) {
        super.repaint();
    }
    @Override
    public void paint(Graphics graphics){
        
        if(model.apple.valor == 15){
            Graphics g = bf.getGraphics();
            super.paint(g);
            panel.renderModel2(model, g);
            graphics.drawImage(bf, 0, 0, null);
        }
        else{
            Graphics g = bf.getGraphics();
            super.paint(g);
            panel.renderModel(model, g);
            graphics.drawImage(bf, 0, 0, null);
        }
        
    }
   
}
