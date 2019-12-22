/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ia.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @authors Iarley Moraes, Emanuel Souza, Caio Barreto
 */
@SuppressWarnings("serial")
public class MainFrameBase extends JFrame{
    public MainFrameBase(){
        setTitle("Projeto Base");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new Map());
        setSize(800, 800);
        setLocationRelativeTo(null);
    } 
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Posicao().setVisible(true);
                //new MainFrameBase().setVisible(true);
            }
        });
    }
}
