package com.albert.ludopatycards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.albert.ludopatycards.managers.DeckApiManager;
import com.albert.ludopatycards.model.Card;
import com.albert.ludopatycards.model.Deck;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main__card_image) ImageView cardImage;
    @BindView(R.id.activity_main__cards_left_text) TextView cardLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final DeckApiManager apiManager = new DeckApiManager();
        apiManager.setOnNewDeckListener(new DeckApiManager.DeckApiManagerNewDeckListener() {
            @Override
            public void onNewDeck(Deck deck) {

            }
        });
        apiManager.newDeck(this);
        cardLeft.setText(Deck.getRemaining() + " Cards left!");



        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DeckApiManager cardApiManager = new DeckApiManager();
                cardApiManager.setOnNewCardListener(new DeckApiManager.DeckApiManagerNewCardListener() {
                    @Override
                    public void onNewCard(Card card) {

                    }
                });

                cardApiManager.drawCard(getApplicationContext());
                Picasso.with(getApplicationContext()).load(Card.getImage()).into(cardImage);
                cardLeft.setText(Card.getRemaining() + " Cards left!");

            }
        });

    }
}
