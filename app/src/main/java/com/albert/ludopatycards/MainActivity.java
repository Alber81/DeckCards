package com.albert.ludopatycards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.albert.ludopatycards.managers.CardApiManager;
import com.albert.ludopatycards.managers.DeckApiManager;
import com.albert.ludopatycards.model.Card;
import com.albert.ludopatycards.model.Deck;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main__card_image) ImageView cardImage;
    @BindView(R.id.activity_main__cards_left_text) TextView cardLeft;
    @BindView(R.id.activity_main___newDeck_button) Button newDeck;
    private Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeckApiManager apiManager = new DeckApiManager();
                apiManager.setOnNewDeckListener(new DeckApiManager.DeckApiManagerNewDeckListener() {
                    @Override
                    public void onNewDeck(Deck deckFromJson) {
                        cardLeft.setText(deckFromJson.getRemaining() + " Cards left!");
                        deck = deckFromJson;
                        cardImage.setVisibility(View.VISIBLE);
                    }
                });
                apiManager.newDeck(v.getContext());
                newDeck.setVisibility(View.INVISIBLE);
                cardImage.setImageResource(R.drawable.card_back_blue);

            }
        });

        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardApiManager cardApiManager = new CardApiManager();
                cardApiManager.setListener(new CardApiManager.CardApiManagerNewCardListener() {
                    @Override
                    public void onNewCard(Card card) {
                        Picasso.with(MainActivity.this).load(card.getImage()).placeholder(R.drawable.card_back_blue).into(cardImage);
                        cardLeft.setText(card.getRemaining()+ " Cards left!");

                        if (card.getRemaining()==0){
                            newDeck.setVisibility(View.VISIBLE);
                            Picasso.with(MainActivity.this).load(card.getImage()).placeholder(R.drawable.card_back_blue).into(cardImage);
                        }
                    }
                });
                cardApiManager.newCard(v.getContext(), deck);
            }
        });
    }

}
