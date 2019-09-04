/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ian Rodriguez
 */
public class TAdapter extends KeyAdapter{
    Model model;
    public TAdapter(Model m){
        this.model = m;
    }
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
}
