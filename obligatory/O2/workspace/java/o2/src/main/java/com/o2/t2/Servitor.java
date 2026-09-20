package com.o2.t2;

import java.util.Random;

public class Servitor extends Thread {
    private final HamburgerBrett brett;
    private final String navn;
    private final Random random = new Random();

    public Servitor(HamburgerBrett brett, String navn) {
        this.brett = brett;
        this.navn = navn;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Tilfeldig tid mellom 2 og 6 sekunder (2000 ms - 6000 ms)
                int tid = 2000 + random.nextInt(4001);
                Thread.sleep(tid);
                
                brett.taAv(navn);
            } catch (InterruptedException e) {
                System.out.println(navn + " ble avbrutt.");
                break;
            }
        }
    }
}