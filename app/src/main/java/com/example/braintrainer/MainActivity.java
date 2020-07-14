package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout gameLayout;
    private RelativeLayout endResult;

    private Button play;

    private TextView timer;
    private TextView task;
    private TextView score;
    private TextView resultMessage;
    private TextView finalScoreMessage;

    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;

    private CountDownTimer countDownTimer;

    private final static int timeForPlay = 30;
    private int sec;
    private int points;
    private int rounds;
    private int result;


    public void startGame(View view) {
        play.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);
        startTimer();

    }

    private void startTimer() {

        countDownTimer = new CountDownTimer(timeForPlay * 1000 + 200, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                sec = (int) millisUntilFinished / 1000;
                timer.setText(sec + "s");
                System.out.println(sec + "S");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                endGame();
            }
        }.start();
    }

    public void mainLogic(View view) {

        TextView textView = (TextView)view;
        System.out.println(textView.getText());

        if (textView.getText().equals(String.valueOf(result))) {
            points++;
            rounds++;
            resultMessage.setText("Correct!");

        } else {
            rounds++;
            resultMessage.setText("Wrong");
        }

        score.setText(points + "/" + rounds);
        drawAnswers();

    }

    private void drawAnswers() {

        int firstNumber = new Random().nextInt(21);
        int secondNumber = new Random().nextInt(21);
         result = firstNumber + secondNumber;

        int whereCorrectAnswer = new Random().nextInt(4);

        String correctAnswer = String.valueOf(result);
        if (whereCorrectAnswer == 0) {
            a.setText(correctAnswer);
        } else {

            a.setText(randomNumber(result +1));
        }
        if (whereCorrectAnswer == 1) {
            b.setText(correctAnswer);
        } else {

            b.setText(randomNumber(result -1));
        }
        if (whereCorrectAnswer == 2) {
            c.setText(correctAnswer);
        } else {
            c.setText(randomNumber(result +2));
        }
        if (whereCorrectAnswer == 3) {
            d.setText(correctAnswer);
        } else {
            d.setText(randomNumber(result -2));
        }

        task.setText(firstNumber + " + " + secondNumber);

    }

    private String randomNumber(int correctAnswer) {
        if (new Random().nextBoolean()) {
            return String.valueOf((new Random().nextInt(6) + correctAnswer));
        } else {
            return String.valueOf((correctAnswer - new Random().nextInt(6)));
        }
    }

    private void endGame(){
        finalScoreMessage.setText("Your score: " + points + "/" + rounds);
        endResult.setVisibility(View.VISIBLE);
        resultMessage.setVisibility(View.GONE);
    }

    public void playAgain(View view) {
        endResult.setVisibility(View.GONE);
        countDownTimer.start();
        points = 0;
        rounds = 0;
        score.setText(points + "/" + rounds);
        resultMessage.setVisibility(View.VISIBLE);
        resultMessage.setText("");
    drawAnswers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.playButton);
        gameLayout = findViewById(R.id.gameLayout);
        timer = findViewById(R.id.textViewTimer);
        task = findViewById(R.id.textViewTask);
        score = findViewById(R.id.textViewPoints);
        resultMessage = findViewById(R.id.textViewResult);
        endResult = findViewById(R.id.endResultMessage);
        finalScoreMessage = findViewById(R.id.yourScoreEndResult);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);

        drawAnswers();
    }
}