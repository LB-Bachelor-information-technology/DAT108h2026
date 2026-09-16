package com.o2;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        JFrame o2 = new JFrame("Oblig 2 deloppgavevelger");
        
        // Get screen dimensions
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        int w = (int) (screen.width * 1.0 / 6.0);
        int h = (int) (screen.height * 1.0 / 7.0);
        
        Font font = new Font("Areal", Font.PLAIN, (int)screen.getHeight()/50);
        o2.setSize(w,h);
        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JButton startT1 = new JButton("Start oppgave 1");
        JButton startT2 = new JButton("Start oppgave 2");
        JButton startT3 = new JButton("Start oppgave 3");


        startT1.setFont(font);
        startT2.setFont(font);
        startT3.setFont(font);

        startT1.addActionListener(e -> StartT1(e));
        startT2.addActionListener(e -> StartT2(e));
        startT3.addActionListener(e -> StartT3(e));


        buttonPanel.add(startT1);
        buttonPanel.add(startT2);
        buttonPanel.add(startT3);
        o2.add(buttonPanel);
        
        o2.setLocationRelativeTo(null); // Center on screen
        o2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        o2.setVisible(true);
    }

    private static void StartT1(ActionEvent e){
        System.out.println("Starting task 1 in oblig 2");
        String[] args = {};
        T1.main(args);
    }

    private static void StartT2(ActionEvent e){
        System.out.println("Starting task 2 in oblig 2");
        String[] args = {};
        T2.main(args);
    }

    private static void StartT3(ActionEvent e){
        System.out.println("Starting task 3 in oblig 2");
        String[] args = {};
        T3.main(args);
    }
}