package com.o2;

import javax.swing.JOptionPane;

public class T1 {    
    private static volatile boolean running = true;
    public static void main(String[] args) {        
        Thread brann = new Thread(()->{
            while (running) {
                System.out.println("Hallo verden");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }
        });

        Thread solve = new Thread(()->{
            while (running) {
                String svar = JOptionPane.showInputDialog(
                    null,
                    "Skriv inn melding. 'quit' for å slutte",
                    "input",
                    JOptionPane.QUESTION_MESSAGE
                );

                if (svar!=null && !svar.isEmpty()) {
                    if (svar.contains("quit")) {
                        running = false;
                    }
                    else if (svar.contains("quit?")) {
                        System.out.println(svar);
                        System.out.println("spør du?");
                    }
                    else {
                        System.out.println(svar);
                    }
                }

                
            }
        });


        brann.start();
        solve.run();
    }
}
