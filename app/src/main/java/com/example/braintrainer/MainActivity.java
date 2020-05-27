package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ImageView imageView;
    TextView titleTextView;
    TextView scoreTextView;
    TextView resultTextView;
    TextView sumTextView;
    TextView timerTextView;
    GridLayout gridLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Button playAgainButton;
    ConstraintLayout mainContent;
    boolean gameActive;

    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain (View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("0:30");
        scoreTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long l) {
                timerTextView.setText("0:" + String.valueOf(l / 1000 ));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Finished");
                playAgainButton.setVisibility(View.VISIBLE);
                gameActive = false;
                //resultTextView.animate().alpha(0).setDuration(1000);

            }
        }.start();



    }
   /* public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }*/


    public void Start (View view){


        goButton.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        titleTextView.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        mainContent.setVisibility(View.VISIBLE);



    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b) + " = ?");

        locationOfCorrectAnswer = rand.nextInt (4);

        answers.clear();

        for (int i = 0; i<4; i++) {
            if (i == locationOfCorrectAnswer && gameActive){
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt( 41);
                while ( wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt( 41);

                }
                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    private void animationResetSum () {
        //sumTextView = findViewById (R.id.sumTextView); find view by id in on create method
        ObjectAnimator.ofFloat (sumTextView, "translationX", 0.0f, 0.0f) .start ();
        ObjectAnimator.ofFloat (sumTextView, "translationY", 200.0f, 0.0f) .start ();
    }

    private void animationResetResult () {
        //sumTextView = findViewById (R.id.sumTextView); find view by id in on create method
        ObjectAnimator.ofFloat (resultTextView, "translationX", 0.0f, 0.0f) .start ();
        ObjectAnimator.ofFloat (resultTextView, "alpha", 0.0f, 1.0f) .start ();
    }

    private void animationResetBulb () {
        //sumTextView = findViewById (R.id.sumTextView); find view by id in on create method
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(goButton,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        scaleDown.setDuration(500);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();
    }

    public void chooseAnswer (View view){


        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct");
            score++;
        } else {
            resultTextView.setText("Incorrect");

        }
        //resultTextView.animate().alpha(1).setDuration(500); old animation code. Created method for animation instead
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        resultTextView.setVisibility(View.VISIBLE);
        animationResetSum();
        animationResetResult();
        newQuestion();
        //sumTextView.animate().translationYBy(200).setDuration(1000); same as above



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.titleTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        mainContent = findViewById(R.id.mainContent);

        //gridLayout = findViewById(R.id.gridLayout); ** OLD CODE**
        //mainContent.setVisibility(View.INVISIBLE);
        //goButton.setVisibility(View.VISIBLE);
        //imageView.setVisibility(View.VISIBLE);
        //titleTextView.setVisibility(View.VISIBLE);


        //goButton.animate().alpha(1).setStartDelay(2000).setDuration(1000);
        imageView.animate().alpha(1).setDuration(500);
        titleTextView.animate().alpha(1).setDuration(500);
        animationResetBulb();








    }
}


