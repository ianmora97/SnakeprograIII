/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Ian Rodriguez
 */
public class Model extends Observable{
    
    public boolean flag;
    
    public Dot arrayDots;
    public Apple apple;
    int temp = 0;
    public boolean star;
    
    public boolean comeAzul = false, comeVerde = false;
    public boolean UP, DOWN, LEFT, RIGHT;
    
    public final int WIDTH = 600;
    public final int HEIGHT = 600;
    
    int delay = 120;
    
    public Model() {
        arrayDots = new Dot(800);
        apple = new Apple();
        
        star = false;
        
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = true;
        
        flag = true;
    }
    public void comioAzul(final int del){
        if ((arrayDots.getIndexX(0) == apple.x) 
                && (arrayDots.getIndexY(0) == apple.y)) {

            arrayDots.cant++;
            apple.move();
            delay = del;
            temp = arrayDots.cant;
            comeAzul = true;
        }
    }
    public void comio(){
        if ((arrayDots.getIndexX(0) == apple.x) 
                && (arrayDots.getIndexY(0) == apple.y)) {

            arrayDots.cant++;
            apple.move();
            if(arrayDots.cant - temp == 3){
                delay = 90;
            }
            comeVerde = true;
        }
    }
    
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();
    }
    public void start(){
        
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(flag){
                    step();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                    }
                }
                
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    
    
    public void step(){
        arrayDots.move(this);
        apple.checkCollision(this);
        if(apple.valor == 15){
            comioAzul(50);
        }
        else{
            comio();
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    
}
