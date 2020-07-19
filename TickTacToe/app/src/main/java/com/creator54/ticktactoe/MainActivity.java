package com.creator54.ticktactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean player1turn = true;
    private int roundcount;
    private int player1Points;
    private int player2Points;

    private TextView textviewPlayer1;
    private TextView textviewPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewPlayer1=findViewById(R.id.text_view_p1);
        textviewPlayer2=findViewById(R.id.text_view_p2 );
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonID = "button_"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonreset=findViewById(R.id.reset);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if(player1turn){
            ((Button) v).setText("X");
        } else{
            ((Button) v).setText("O");
        }
        roundcount++;

        if(checkforwin()){
            if(player1turn){
                player1won();
            }
            else {
                player2won();
            }
        } else if(roundcount==9){
            draw();
        } else{
            player1turn=!player1turn;
        }
    }

    private boolean checkforwin(){
        String[][] field = new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }
        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1won(){
        player1Points++;
        Toast.makeText(this,"Player 1 Wins !", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetboard();
    }

    private void player2won(){
        player2Points++;
        Toast.makeText(this,"Player 2 Wins !", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetboard();
    }

    private void draw(){
        Toast.makeText(this,"Draw !", Toast.LENGTH_SHORT).show();
        resetboard();
    }

    private void updatePointsText(){
        textviewPlayer1.setText("Player 1 : "+ player1Points);
        textviewPlayer2.setText("Player 2 : "+ player2Points);
    }

    private void resetboard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;
    }
}