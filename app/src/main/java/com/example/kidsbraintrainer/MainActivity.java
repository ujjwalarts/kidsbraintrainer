package com.example.kidsbraintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView txtTimer,sumText,txtScore,txtCorrect;
AppCompatButton btn0,btn1,btn2,btn3,btnPlayAgain;
ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score =0;
    int numbersOfGamesPlayed =0;
    CountDownTimer countDownTimer;
public void chooseAnswer(View view){
   if(String.valueOf(locationOfCorrectAnswer).equals(view.getTag())){
       txtCorrect.setText("Correct!!");
       score++;
   }else {
       txtCorrect.setText("Wrong!!");
   }
   numbersOfGamesPlayed++;
   txtScore.setText(score+"/"+numbersOfGamesPlayed);
   newQuestion();
}
public void newQuestion(){
    Random rand = new Random();
    int a = rand.nextInt(21);
    int b = rand.nextInt(21);
    sumText.setText(a +"+" +b);
    locationOfCorrectAnswer = rand.nextInt(4);
    for(int i = 0;i<4;i++){
        if(i==locationOfCorrectAnswer){
            answer.add(a+b);
        }else{
            int wrongAnswer = rand.nextInt(41);
            while (wrongAnswer==a+b){
                wrongAnswer=rand.nextInt(41);
            }
            answer.add(wrongAnswer);
        }
    }
    btn0.setText(String.valueOf(answer.get(0)));
    btn1.setText(String.valueOf(answer.get(1)));
    btn2.setText(String.valueOf(answer.get(2)));
    btn3.setText(String.valueOf(answer.get(3)));
    answer.clear();
}
public  void playAgain(View view){
    txtTimer.setText("30s");
    score=0;
    numbersOfGamesPlayed=0;
    txtScore.setText(score+"/"+numbersOfGamesPlayed);
    newQuestion();
    btnPlayAgain.setVisibility(View.INVISIBLE);
    countDownTimer = new CountDownTimer(30100,1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText((l/1000) + "s");
        }

        @Override
        public void onFinish() {
            txtCorrect.setText("Done!!");
            btnPlayAgain.setVisibility(View.VISIBLE);
        }
    }.start();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTimer = findViewById(R.id.txtTimer);
        txtScore = findViewById(R.id.txtScore);
        txtCorrect = findViewById(R.id.txtCorrect);
        sumText = findViewById(R.id.sumText);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

       playAgain(findViewById(R.id.btnPlayAgain));

    }
}