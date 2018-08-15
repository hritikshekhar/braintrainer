package com.crichood.braintrainer;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView result;
    int score=0;
    int numberOfQuestions=0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumtextview;
    TextView pointTextView;
    TextView timerTextView;
    Button playAgain;
    RelativeLayout gameLayoutview;

    public void playAgain(View view){

        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        generateQuestions();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                result.setText("Your score: "+Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();


    }

    public void generateQuestions(){

        Random rand  = new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumtextview.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int IncorrectAnswer;
        for(int i=0;i<4;i++){

            if(i==locationOfCorrectAnswer){

                answers.add(a+b);
            }
            else{

                IncorrectAnswer=rand.nextInt(41);

                while(IncorrectAnswer == a+b){

                    IncorrectAnswer = rand.nextInt(41);
                }
                answers.add(IncorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameLayoutview.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playagain));
    }
    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            result.setText("Correct!");
        }
        else{
            result.setText("Wrong!");
        }
        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestions();

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        sumtextview = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        result = findViewById(R.id.resultTextView);
        pointTextView = findViewById(R.id.points);
        timerTextView = findViewById(R.id.timer);
        playAgain = findViewById(R.id.playagain);
        gameLayoutview = findViewById(R.id.gameLayout);



    }


}
