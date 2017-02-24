package com.albert.ludopatycards.managers;


import android.content.Context;
import android.util.Log;

import com.albert.ludopatycards.model.Card;
import com.albert.ludopatycards.model.Deck;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;

public class DeckApiManager {

    public interface DeckApiManagerNewDeckListener {
        public void onNewDeck(Deck deck);
    }

    private DeckApiManagerNewDeckListener listener;

    public void setOnNewDeckListener(DeckApiManagerNewDeckListener listener) {
        this.listener = listener;
    }

    private static final String NEW_DECK_REQUEST = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
    private static final String NEW_CARD_REQUEST = "https://deckofcardsapi.com/api/deck/" + Deck.getId() + "/draw/?count=1";

    public void newDeck(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(NEW_DECK_REQUEST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // todotodito ok
                Log.d("RESPONSE", response);
                parseJSON(response);

            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                // Houston, tenemos un problema
                Log.e("HORROR", "Connection went to shit to the tracks");
            }
        });

        queue.add(request);
    }

    private void parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Reader reader = new StringReader(response);

        DeckEntity deckEntity = gson.fromJson(reader, DeckEntity.class);

        Deck deck = new Deck();
        deck.setId(deckEntity.getDeck_id()); // pasa lo ke hemos cogido del texto de internet (deck_id) a nuestro metodo set en Deck; esto es parsear
        deck.setRemaining(deckEntity.getRemaining());

        if (listener !=null) {
            listener.onNewDeck(deck);
        }
    }



    public interface DeckApiManagerNewCardListener {
        public void onNewCard(Card card);
    }

    private DeckApiManagerNewCardListener cardListener;

    public void setOnNewCardListener(DeckApiManagerNewCardListener cardListener) {
        this.cardListener = cardListener;
    }
    public void drawCard (Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(NEW_CARD_REQUEST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // todotodito ok
                Log.d("RESPONSE", response);
                cardParseJSON(response);

            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                // Houston, tenemos un problema
                Log.e("HORROR", "Connection went to shit to the tracks");
            }
        });

        queue.add(request);
    }

    private void cardParseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Reader reader = new StringReader(response);

        CardEntity cardEntity = gson.fromJson(reader, CardEntity.class);

        Card card = new Card();
        card.setRemaining(cardEntity.getRemaining());

        CardEntity.Image cardImageEntity = gson.fromJson(reader, CardEntity.Image.class);


        card.setImage(cardImageEntity.getPng());

        if (cardListener !=null) {
            cardListener.onNewCard(card);
        }
    }
}
