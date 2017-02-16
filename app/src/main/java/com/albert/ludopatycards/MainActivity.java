package com.albert.ludopatycards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.albert.ludopatycards.managers.DeckApiManager;
import com.albert.ludopatycards.model.Deck;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeckApiManager apiManager = new DeckApiManager();
        apiManager.setOnNewDeckListener(new DeckApiManager.DeckApiManagerNewDeckListener() {
            @Override
            public void onNewDeck(Deck deck) {

            }
        });
        apiManager.newDeck(this);
    }
}
