package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button rollButton;
    ImageView left_dice, right_dice;
    TextView sumAtv, sumBtv, attemptsAtv, attemptsBtv, resulttv;
    ImageView statusAiv, statusBiv;
    int sumA = 0;
    int sumB = 0;
    int attemptsA = 6;
    int attemptsB = 6;
    boolean playerATurn = true;
    boolean playerBTurn = false;

    final int[] statusImage = {
            R.drawable.green_circle,
            R.drawable.black_circle
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        final int[] diceArray = {
                R.drawable.dice11,
                R.drawable.dice22,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };


        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Random random = new Random();
                    int number1 = random.nextInt(6);
                    left_dice.setImageResource(diceArray[number1]);
                    int number2 = random.nextInt(6);
                    right_dice.setImageResource(diceArray[number2]);

                    if (playerATurn) {

                        sumA = sumA + number1 + number2 + 2;
                        sumAtv.setText(sumA + "");
                    } else {

                        sumB = sumB + number1 + number2 + 2;
                        sumBtv.setText(sumB + "");
                    }

                    if (number1 == number2) {
                        if (playerATurn) {
                            attemptsA--;
                            if(attemptsA==0)
                                statusAiv.setImageResource(statusImage[1]);
                            attemptsAtv.setText(attemptsA + "");
                            changeStatusB();
                            playerATurn = false;
                            playerBTurn = true;
                        } else {
                            attemptsB--;
                            if(attemptsB==0) {
                                statusBiv.setImageResource(statusImage[1]);
                                rollButton.setEnabled(false);

                                if (sumA > sumB) {
                                    resulttv.setText("PLAYER A won the Match!");
                                } else if (sumB > sumA) {
                                    resulttv.setText("PLAYER B won the Match!");
                                } else {
                                    resulttv.setText("Match Tied!");
                                }
                            }

                            else
                                changeStatusA();
                            attemptsBtv.setText(attemptsB + "");
                            playerBTurn = false;
                            playerATurn = true;
                        }
                    }


                }

        });

    }


    private void changeStatusB() {
        statusBiv.setImageResource(statusImage[0]);
        statusAiv.setImageResource(statusImage[1]);
    }

    private void changeStatusA() {
        statusAiv.setImageResource(statusImage[0]);
        statusBiv.setImageResource(statusImage[1]);
    }

    private void initializeViews() {
        rollButton = findViewById(R.id.rollButton);
        left_dice = findViewById(R.id.left_dice);
        right_dice = findViewById(R.id.right_dice);
        sumAtv = findViewById(R.id.sumAtv);
        sumBtv = findViewById(R.id.sumBtv);
        statusAiv = findViewById(R.id.statusAiv);
        statusBiv = findViewById(R.id.statusBiv);
        attemptsAtv = findViewById(R.id.attemptsAtv);
        attemptsBtv = findViewById(R.id.attemptsBtv);
        resulttv = findViewById(R.id.resulttv);
    }


}