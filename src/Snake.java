/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import snakegame.Controller;
import snakegame.Model;
import snakegame.View;



/**
 *
 * @author Ian Mora
 */

public class Snake  {

    public static void main(String[] args) throws Exception{
        Model model = new Model();
        View view = new View();
        view.setModel(model);
        Controller controller = new Controller(model, view);
        view.setVisible(true);
        model.start();
    }
}
