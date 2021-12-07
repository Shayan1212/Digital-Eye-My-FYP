package com.example.digitaleye;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class privacyActivity3 extends AppCompatActivity {
    Button stop22,play,resume;
    TextToSpeech textToSpeech;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy3);
        stop22 = findViewById(R.id.stop2);
        play = findViewById(R.id.play);
        resume = findViewById(R.id.resume);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (i == TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(privacyActivity3.this, "Language not supported.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        stop22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
            }
        });
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 001: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result.contains("1") || result.contains("one") || result.contains("One") || result.contains("ONE")) {
                    String item = "The Digital Eye is an android application that helps the Blind peoples in confronting everyday life issues. This application will be utilized for cash acknowledgment, menu items acknowledgment and furthermore place request/call and can place an order and also handling with the data of the user. After perceiving text, the application will deliver the result of that perception in voice by means of text to discourse.";
                    speech(item);
                    return;
                }else  if (result.contains("2") || result.contains("two") || result.contains("Two") || result.contains("TWO")) {
                    String item = "Digital Eye is an easy to use application requires no user personal information to be entered in order to use Digital eye. So this proves that our application is fully safe and secure for use as concern with the privacy of the user.";
                    speech(item);
                    return;
                }else  if (result.contains("3") || result.contains("three") || result.contains("Three") || result.contains("THREE")) {
                    String item = "Digital Eye requires Internet permission, Camera Feature permission, Record Audio permission and Call permission. All these permissions were related with the functional requirements of our application making sure the privacy and protection of our user and application.";
                    speech(item);
                    return;
                }else
                    {
                        speech(" Sorry you have selected wrong number.");
                    }
                break;
            }
            }
        }
    }
    private void promptSpeechInput() {
        // SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly Select the Number.");
        try {
            startActivityForResult(speachintent, 001);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    public void play(){

        speech("If you want to listen About Digital Eye say one. If you want to listen our privacy terms say two. And if you want to listen user permissions say three. Thank yo");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInput();
            }

        } while (speakingEnd);

    }
    private void toastMessage(String message)
    {
        Toast.makeText(privacyActivity3.this,message, LENGTH_LONG).show();
    }
    private void speech(String message)
    {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
}