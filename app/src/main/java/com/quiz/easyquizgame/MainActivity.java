package com.quiz.easyquizgame;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;


    Button answer1, answer2, answer3, answer4;

    TextView score, question;

    private Questions mQuestions = new Questions();

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsLength = mQuestions.mQuestions.length;
    private InterstitialAd mInterstitialAd;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        random = new Random();

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        score = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);

        score.setText("Score: " + mScore);
        updateQuestion(random.nextInt(mQuestionsLength));

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(random.nextInt(mQuestionsLength));
                }else {
                    gameOver();
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "Ads wasn't ready yet.");
                    }
                }

            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(random.nextInt(mQuestionsLength));
                }else {
                    gameOver();
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "Ads wasn't ready yet.");
                    }
                }

            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer3.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(random.nextInt(mQuestionsLength));
                }else {
                    gameOver();
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "Ads wasn't ready yet.");
                    }
                }

            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer4.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(random.nextInt(mQuestionsLength));
                }else {
                    gameOver();
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "Ads wasn't ready yet.");
                    }
                }

            }
        });
//                                                       YOUR INTERSTITIAL GOES HERE
        InterstitialAd.load(this,"ca-app-pub-8598486160932873/5615258291", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            Log.d(TAG, loadAdError.toString());
                            mInterstitialAd = null;

                        }


                });


    }

    private void updateQuestion(int num){

        question.setText(mQuestions.getQuestion(num));
        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));

        mAnswer = mQuestions.getCorrectAnswer(num);

    }
    private  void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Game Over! Your score is" + mScore + "points.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }

                            });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}