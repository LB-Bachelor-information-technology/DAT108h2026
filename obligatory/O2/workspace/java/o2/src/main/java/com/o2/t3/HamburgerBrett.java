package com.o2.t3;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class HamburgerBrett {
    private final BlockingQueue<Hamburger> brett;
    private final AtomicInteger hamburgerTeller = new AtomicInteger(0);

    public HamburgerBrett(int kapasitet) {
        this.brett = new ArrayBlockingQueue<>(kapasitet);
    }

    public void leggPa(String kokkNavn) throws InterruptedException {
        int id = hamburgerTeller.incrementAndGet();
        Hamburger hamburger = new Hamburger(id);
        
        // put() blokkerer automatisk dersom køen er full
        brett.put(hamburger);
        
        System.out.println(kokkNavn + " (kokk) legger på hamburger " + id + ". Brett: " + brett);
    }

    public Hamburger taAv(String servitorNavn) throws InterruptedException {
        // take() blokkerer automatisk dersom køen er tom
        Hamburger hamburger = brett.take();
        
        System.out.println(servitorNavn + " (servitør) tar av hamburger " + hamburger.getId() + ". Brett: " + brett);
        return hamburger;
    }
}