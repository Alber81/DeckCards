package com.albert.ludopatycards.managers;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardEntity {

    @SerializedName("remaining") private int remaining;
    @SerializedName("cards") private List<Cards> cards;


    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }


    public class Cards {

        @SerializedName("image") String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}