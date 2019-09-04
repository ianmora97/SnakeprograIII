/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author Ian Rodriguez
 */
public class Dot {
    
    public int x[];
    public int y[];
    
    public int tam, cant;
    
    public int distance; //dot size

    public Dot(int tam) {
        
        x = new int[tam];
        y = new int[tam];
        
        this.tam = tam;
        this.cant = 1;
        this.distance = 20;
        
        for (int z = 0; z < cant; z++) {
            x[z] = 100 - z * distance;
            y[z] = 100;
        }
    }
    public void move(Model m) {

        for (int z = cant; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (m.LEFT) {
            x[0] -= distance;
        }

        else if (m.RIGHT) {
            x[0] += distance;
        }

        else if (m.UP) {
            y[0] -= distance;
        }

        else if (m.DOWN) {
            y[0] += distance;
        }
    }
    public int getIndexX(int i){
        return x[i];
    }
    public int getIndexY(int i){
        return y[i];
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    
    
    
    
    
}
