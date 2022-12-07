package com.example.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton[][] buttons = new ImageButton[3][3];


    private boolean firstPlayer;

    private int roundCount;

    // set listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "mainactivity_btn" + i + j;
                int resId = getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }

        }
        resetBoard();
        Button buttonReset = findViewById(R.id.mainactivity_play_btn);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("play AGAIN");
                resetBoard();
            }
        });
    }


    @Override
    public void onClick(View view) {

        // if clicked on nonnull place
        if ((view).getTag() != "" )  {
            return;
        }

        // if clicked on initial, set Tag and Image
        if (firstPlayer) {
            (view).setTag("x");
            ((ImageButton) view).setImageResource(R.drawable.x);

        } else {
            (view).setTag("o");
            ((ImageButton) view).setImageResource(R.drawable.o);
        }

        roundCount++;

        // check if the move creates a win
        if (checkFowWin()){
            if (firstPlayer){
                PlayerWins(1);
            } else {
                PlayerWins(2);
            }
            // check for a draw
        } else if(roundCount == 9) {
            PlayerWins(0);
        } else {
            firstPlayer = !firstPlayer;
            if (firstPlayer) {
                ImageView imageView = findViewById(R.id.imageviewStatus);
                imageView.setImageResource(R.drawable.xplay);
            } else {
                ImageView imageView = findViewById(R.id.imageviewStatus);
                imageView.setImageResource(R.drawable.oplay);
            }
        }

    }

    // method to show a winning status
    private void PlayerWins(int playerNum) {
        ImageView imageView = findViewById(R.id.imageviewStatus);
        if (playerNum == 1) {
            imageView.setImageResource(R.drawable.xwin);
        } else if (playerNum == 2) {
            imageView.setImageResource(R.drawable.owin);
        } else {
            imageView.setImageResource(R.drawable.nowin);
        }
    }

    // method to reset the board
    private void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setTag("");
                buttons[i][j].setImageResource(R.drawable.empty);
            }
        }
        for (int i = 1; i < 9; i++) {
            String winLine = "winLine" + i;
            int resId = getResources().getIdentifier(winLine, "id", getPackageName());
            ImageView imageViewLine = findViewById(resId);
            imageViewLine.setVisibility(View.INVISIBLE);
        }

        ImageView imageView = findViewById(R.id.imageviewStatus);
        imageView.setImageResource(R.drawable.xplay);
        roundCount = 0;
        firstPlayer = true;

    }

    private boolean checkFowWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getTag().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0] == (field[i][1]) &&
                    field[i][0] == (field[i][2]) &&
                    field[i][0] != ("")) {

                // set win line
                if ( i == 0 ) {
                    ImageView imageView = findViewById(R.id.winLine6);
                    imageView.setVisibility(View.VISIBLE);
                } else if ( i == 1 ) {
                    ImageView imageView = findViewById(R.id.winLine7);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    ImageView imageView = findViewById(R.id.winLine8);
                    imageView.setVisibility(View.VISIBLE);
                }
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == (field[1][i]) &&
                    field[0][i] == (field[2][i]) &&
                    field[0][i] != ("")) {

                // set win line
                if ( i == 0 ) {
                    ImageView imageView = findViewById(R.id.winLine1);
                    imageView.setVisibility(View.VISIBLE);
                } else if ( i == 1 ) {
                    ImageView imageView = findViewById(R.id.winLine2);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    ImageView imageView = findViewById(R.id.winLine3);
                    imageView.setVisibility(View.VISIBLE);
                }
                return true;
            }
        }

        if (field[0][0] == (field[1][1]) &&
                field[0][0] == (field[2][2]) &&
                field[0][0] != ("")) {

            // set win line
            ImageView imageView = findViewById(R.id.winLine4);
            imageView.setVisibility(View.VISIBLE);
            return true;
        }

        if (field[0][2] == (field[1][1]) &&
                field[0][2] == (field[2][0]) &&
                field[0][2] != ("")) {

            // set win line
            ImageView imageView = findViewById(R.id.winLine5);
            imageView.setVisibility(View.VISIBLE);
            return true;
        }

        return false;
    }
}