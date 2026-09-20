package com.o2.t2;

import java.util.LinkedList;
import java.util.Queue;

public class HamburgerBrett {
    private final int kapasitet;
    private final Queue<Hamburger> brett;
    private int hamburgerNummer = 0;

    public HamburgerBrett(int kapasitet) {
        this.kapasitet = kapasitet;
        this.brett = new LinkedList<>();
    }

    public synchronized void leggPa(String kokkNavn) throws InterruptedException {
        while (brett.size() == kapasitet) {
            System.out.println(kokkNavn + " (kokk) klar med hamburger, men brett fullt. Venter!");
            wait();
        }

        hamburgerNummer++;
        Hamburger hamburger = new Hamburger(hamburgerNummer);
        brett.add(hamburger);
        
        System.out.println(kokkNavn + " (kokk) legger på hamburger " + hamburger.getId() + ". Brett: " + brett);
        
        notifyAll();
    }

    public synchronized Hamburger taAv(String servitorNavn) throws InterruptedException {
        while (brett.isEmpty()) {
            System.out.println(servitorNavn + " (servitør) ønsker å ta hamburger, men brett tomt. Venter!");
            wait();
        }

        Hamburger hamburger = brett.poll();
        System.out.println(servitorNavn + " (servitør) tar av hamburger " + hamburger.getId() + ". Brett: " + brett);
        
        notifyAll();
        return hamburger;
    }
}