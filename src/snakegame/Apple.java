/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.Random;

/**
 *
 * @author Ian Rodriguez
 */
public class Apple {
    
    public int x;
    public int y;
    
    public int tamanio;
    Random rand = new Random();
    public int valor;

    public Apple() {
        this.move();
        this.tamanio = 20;
    }
    public void move(){
        valor = rand.nextInt(20);
        System.out.println("Valor: "+valor);
        Random r = new Random();
        x = r.nextInt(25)*20+40;
        y = r.nextInt(25)*20+60;
    }
    public void checkCollision(Model m) {

        for (int z = m.arrayDots.cant; z > 0; z--) {
            if ((z > 4) && (m.arrayDots.getIndexX(0) == m.arrayDots.getIndexX(z)) 
                    && (m.arrayDots.getIndexY(0) == m.arrayDots.getIndexY(z))) {
                m.flag = false;
            }
        }

        if (m.arrayDots.getIndexY(0) >= 560) {
            m.flag = false;
        }
        if (m.arrayDots.getIndexY(0) < 60) {
            m.flag = false;
        }
        if (m.arrayDots.getIndexX(0) >= 550) {
            m.flag = false;
        }
        if (m.arrayDots.getIndexX(0) < 40) {
            m.flag = false;
        }
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }   
}
