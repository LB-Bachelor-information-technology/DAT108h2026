package com.o2;
public class T1 {
    private static final boolean erFraBergen = false;
    private static final String message = erFraBergen? "Hallo verden" : "å nei! Det brenner! hva skal jeg rope for å varsle de andre?";
    private static volatile boolean running = true;
    public static void main(String[] args) {
        Thread brann = new Thread(()->{
            while (running) {
                System.out.println(message);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }
        });

        brann.start();
    }
}
