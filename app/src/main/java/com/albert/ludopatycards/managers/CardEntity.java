package com.albert.ludopatycards.managers;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CardEntity {

    @SerializedName("remaining") private int remaining;
    @SerializedName("cards") private List<Cards> cards;


    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }



    public class Cards {
        @SerializedName("image") Image image;


        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }



    }
    public class Image {
        @SerializedName(".png") String png;

        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }

        @SerializedName(".svg") String svg;

    }
}