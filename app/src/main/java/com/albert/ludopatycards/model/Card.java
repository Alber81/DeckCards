package com.albert.ludopatycards.model;

// black spades(♠), red hearts(♥), blue diamonds(♦) and green clubs (♣)
// suit es baraja

public class Card {

    public enum Suit {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }

    private Suit suit;
    private static String image;
    private static int remaining;

    public Card() {
    }

    public Card(int remaining) {
        this.remaining = remaining;
    }

    public Card(String image) {                // con realm hay k poner un constructor vacío
        this.image = image;
    }

    public static int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }


    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public static String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
