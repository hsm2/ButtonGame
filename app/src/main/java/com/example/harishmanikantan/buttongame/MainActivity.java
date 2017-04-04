package com.example.harishmanikantan.buttongame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private TextView resultTextView;

    private final int STATUS_ON = 1;
    private final int STATUS_OFF = 0;

    private GameButton gameButton1;
    private GameButton gameButton2;
    private GameButton gameButton3;
    private GameButton gameButton4;

    HashMap<Integer, Integer> connections = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start_game_view);

        resultTextView = (TextView) findViewById(R.id.result_view);

        button1 = (Button) findViewById(R.id.button1_view);
        button2 = (Button) findViewById(R.id.button2_view);
        button3 = (Button) findViewById(R.id.button3_view);
        button4 = (Button) findViewById(R.id.button4_view);

        gameButton1 = new GameButton(button1);
        gameButton2 = new GameButton(button2);
        gameButton3 = new GameButton(button3);
        gameButton4 = new GameButton(button4);

        // At start of game, Game buttons and result is invisible. Start new game button is visible
        setGameButtonsVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);

        // Start game when the button is clicked
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void startGame() {
        // At start of game, all game buttons are on and visible
        setGameButtonsOn();
        setGameButtonsVisibility(View.VISIBLE);

        // At start of game, start new game button and result view is invisible
        startButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);

        // Connect game buttons one way
        connectButtons();

        // For all game buttons
        // When a button is clicked, it's flipped and its connection is flipped
        // After flips, check if game is over
        // If game over, show result and reset game

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameButton1.flip();
                GameButton temp = getButton(connections.get(0));
                temp.flip();
                if (isGameOver()) {
                    showResultAndReset();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameButton2.flip();
                GameButton temp = getButton(connections.get(1));
                temp.flip();
                if (isGameOver()) {
                    showResultAndReset();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameButton3.flip();
                GameButton temp = getButton(connections.get(2));
                temp.flip();
                if (isGameOver()) {
                    showResultAndReset();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameButton4.flip();
                GameButton temp = getButton(connections.get(3));
                temp.flip();
                if (isGameOver()) {
                    showResultAndReset();
                }
            }
        });
    }

    private void setGameButtonsOn() {
        gameButton1.setOn();
        gameButton2.setOn();
        gameButton3.setOn();
        gameButton4.setOn();
    }

    private void showResultAndReset() {
        // When game over, game buttons are invisible and turned on
        setGameButtonsVisibility(View.INVISIBLE);
        setGameButtonsOn();
        // Result shown and option to start game again
        startButton.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
    }

    private boolean isGameOver() {
        // Game over is all game buttons are off
        return gameButton1.getStatus() == STATUS_OFF && gameButton2.getStatus() == STATUS_OFF
                && gameButton3.getStatus() == STATUS_OFF && gameButton4.getStatus() == STATUS_OFF;
    }

    private GameButton getButton(int i) {
        switch (i) {
            case 0:
                return gameButton1;
            case 1:
                return gameButton2;
            case 2:
                return gameButton3;
            case 3:
                return gameButton4;
        }
        return null;
    }

    private void connectButtons() {
        connections.clear();

        // Hashset array where each element is the hashset of elements that a button
        // cannot be connected to
        HashSet<Integer> notPossibleConnections[] = new HashSet[4];
        for (int i = 0; i < 4; i++) {
            HashSet<Integer> temp = new HashSet<Integer>();
            temp.add(i);
            notPossibleConnections[i] = temp;
        }

        int i = 0;
        while (connections.size() != 4) {
            int target = getRandom(notPossibleConnections[i]);
            notPossibleConnections[target].add(i);
            connections.put(i, target);
            i++;
        }

    }

    private int getRandom(HashSet<Integer> notPossibleConnection) {
        Random random = new Random();
        int target;

        do {
            target = random.nextInt(4);
        }
        while (notPossibleConnection.contains(target));

        return target;
    }

    private void setGameButtonsVisibility(int visibility) {
        gameButton1.setVisibility(visibility);
        gameButton2.setVisibility(visibility);
        gameButton3.setVisibility(visibility);
        gameButton4.setVisibility(visibility);
    }

}
