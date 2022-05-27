package com.example.gameconnect3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;
    int flag = 0;

    @SuppressLint("SetTextI18n")
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        String msg = " ";
        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            } flag++;

            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Someone has won!

                    gameActive = false;

                    String winner;

                    TextView winnerTextView = findViewById(R.id.winnerTextView);

                    if (activePlayer == 1) {

                        winner = "Yellow";
                        winnerTextView.setTextColor(Color.YELLOW);

                    } else {

                        winner = "Red";
                        winnerTextView.setTextColor(Color.RED);

                    }

                    Button playAgainButton = findViewById(R.id.playAgainButton);

                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }

                if (flag == 9 && msg == " ") {

                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText("Match is a draw");
                    winnerTextView.setTextColor(Color.GREEN);
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view) {

        Button playAgainButton = findViewById(R.id.playAgainButton);

        TextView winnerTextView = findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

        flag = 0;

    }

//    public void reset() {
//
//        GridLayout gridLayout = findViewById(R.id.gridLayout);
//
//        for(int i=0; i<gridLayout.getChildCount(); i++) {
//
//            ImageView counter = (ImageView) gridLayout.getChildAt(i);
//
//            counter.setImageDrawable(null);
//
//        }
//
//        for (int i = 0; i < gameState.length; i++)
//
//        {
//
//            gameState[i] = 2;
//
//        }
//
//        activePlayer = 0;
//
//        gameActive = true;
//
//        countClicks = 1;
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}