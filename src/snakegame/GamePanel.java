/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JPanel;

/**
 *
 * @author Ian Rodriguez
 */
public class GamePanel extends JPanel implements ActionListener {

    Model model;

    public static final String DOT_PATH = "dot.png";
    public static final String APPLE_PATH = "apple.png";
    public static final String HEAD_PATH = "src/media/head.png";
    
    public static final String GAMEOVER = "media/gameover.wav";
    public static final String MARIO = "media/mario.wav";
    public static final String COIN = "media/coin.wav";
    public static final String STARMUSIC = "media/star.wav";
    public static final String TAKESTAR = "media/powerup.wav";
    
    Clip musicBack, lose, takeApple, takeStar, star;
    
    BufferedImage dot, apple1, head;

    public GamePanel(Model m) {
        this.model = m;
        
        try {
            star = this.loadSound(STARMUSIC);
            lose = this.loadSound(GAMEOVER);
            musicBack = this.loadSound(MARIO);
            takeApple = this.loadSound(COIN);
            takeStar = this.loadSound(TAKESTAR);
        } catch (Exception e) {
        }
        
        try {
            dot = new BufferedImage(model.arrayDots.distance, model.arrayDots.distance, BufferedImage.TYPE_INT_RGB);
            apple1 = new BufferedImage(model.apple.tamanio, model.apple.tamanio, BufferedImage.TYPE_INT_RGB);
            head = new BufferedImage(model.arrayDots.distance, model.arrayDots.distance, BufferedImage.TYPE_INT_RGB);
        } catch (Exception e) {
        }
        try {
            dot = ImageIO.read(getClass().getResourceAsStream(DOT_PATH));
            apple1 = ImageIO.read(getClass().getResourceAsStream(APPLE_PATH));
            head = ImageIO.read(getClass().getResourceAsStream(HEAD_PATH));
        } catch (Exception e) {
        }
        this.setBackground(Color.BLACK);
        
        
        musicBack.start();
    }
     Clip loadSound(String path){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream(path));
            AudioFormat soundFormat = audio.getFormat();
            int soundSize = (int)(soundFormat.getFrameSize() * audio.getFrameLength());
            byte[] soundData = new byte[soundSize];
            DataLine.Info soundInfo = new DataLine.Info(Clip.class, soundFormat,soundSize);
            audio.read(soundData,0,soundSize);
            Clip clip = (Clip)(AudioSystem.getLine(soundInfo));
            clip.open(soundFormat,soundData,0,soundSize);
            
           return clip;
        } catch(Exception e){return null;}
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (model.flag) {
            model.comio();
            model.apple.checkCollision(model);
            model.arrayDots.move(model);
        }

        repaint();
    }
    public void renderModel2(Model m, Graphics g){
        if (m.flag) {
            renderStar(m.apple, g);
            renderDots(m.arrayDots, g);
            
        } else {
            renderGameOver(m.arrayDots, g);
        }
        g.setColor(Color.red);
        g.drawRect(40, 60, 520, 500);
        
    }
    public void renderModel(Model m, Graphics g) {
        if (m.flag) {
            renderApple(m.apple, g);
            renderDots(m.arrayDots, g);
        } else {
            renderGameOver(m.arrayDots, g);
        }
        g.setColor(Color.red);
        g.drawRect(40, 60, 520, 500);
        if(m.comeVerde){
            
            takeApple.start();
            takeApple.setFramePosition(0);
            if(m.arrayDots.cant - m.temp == 3){
                star.stop();
                star.setFramePosition(0);
            }

            m.comeVerde = false;
        }
        if(m.comeAzul){
            musicBack.stop();
            
            takeStar.start();
            takeStar.setFramePosition(0);
            
            star.start();
            star.loop(40);
            
            m.comeAzul = false;
        }
    }

    public void renderDots(Dot d, Graphics g) {
        for (int z = 0; z < d.cant; z++) {
            if (z == 0) {
                g.setColor(Color.red);
                g.fillOval(d.getIndexX(z), d.getIndexY(z), d.distance, d.distance);
            } else {
                g.setColor(Color.green);
                g.fillOval(d.getIndexX(z), d.getIndexY(z), d.distance, d.distance);
            }
        }
    }
    
    public void renderApple(Apple a, Graphics g) {
        //g.drawImage(apple1, a.x, a.y, a.tamanio, a.tamanio, this);
        g.setColor(Color.green);
        g.fillOval(a.x, a.y, a.tamanio, a.tamanio);
    }
    public void renderStar(Apple a, Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(a.x, a.y, a.tamanio, a.tamanio);

    }
    public void renderGameOver(Dot d, Graphics g) {

        String score = "Score: " + d.cant;
        String msg = "Game Over";

        Font small = new Font("Helvetica", Font.BOLD, 30);
        Font sc = new Font("Helvetica", Font.BOLD, 40);

        FontMetrics metr = getFontMetrics(small);
        FontMetrics scr = getFontMetrics(sc);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (600 - metr.stringWidth(msg)) / 2, 600 / 2);
        g.setColor(Color.green);
        g.drawString(score, ((600 - scr.stringWidth(score)) / 2) + 30, 400);
        musicBack.stop();
        star.stop();
        lose.start();
    }

    

}
