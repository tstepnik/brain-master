package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout gameRelativeLayout;

    Button startButton;
    Button playAgainButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    CountDownTimer countDownTimer;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    List<Integer> answers = new ArrayList<>();
    int locationOnCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){
        score= 0;
        numberOfQuestions = 0;
        countDownTimer.start();
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
    }


    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(a + " + " + b);

        locationOnCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOnCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button1.setText(String.valueOf(answers.get(0)));
        button2.setText(String.valueOf(answers.get(1)));
        button3.setText(String.valueOf(answers.get(2)));
        button4.setText(String.valueOf(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(String.valueOf(locationOnCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();

    }


    public void startMethod(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        countDownTimer.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        generateQuestion();

      countDownTimer = new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score " + score + "/" + numberOfQuestions);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        };


    }
}