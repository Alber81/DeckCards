package com.albert.ludopatycards.model;



public class Deck {

    private static int remaining;
    private static String id;

    public Deck() {
    }

    public Deck(int remaining) {
        this.remaining = remaining;
    }

    public Deck(String id) {                // con realm hay k poner un constructor vacío
        this.id = id;
    }

    public static int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public static String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
