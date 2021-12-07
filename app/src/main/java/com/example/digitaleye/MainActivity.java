package com.example.digitaleye;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    //Initializing Variables
    Button capture_currency,capture_Menu,help_btn;
    int i;
    TextToSpeech textToSpeech;
    ImageView voicecomander , helpicon;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode== 100)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                speech("Permission Granted.");

            }else
            {
                speech("Kindly allow digital eye to Record your Voice.");
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Assingn variable
        capture_currency = findViewById(R.id.capture_currency);
        capture_Menu = findViewById(R.id.capture_Menu);
        help_btn = findViewById(R.id.help_btn1);
        voicecomander = findViewById(R.id.icon_main);
        helpicon = findViewById(R.id.help_icon);
        helpicon.setVisibility(View.GONE);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (i == TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                    String s = " Welcome to Digital Eye. If you want to understand our application. Pleas press help button or speak help using our voice commander placed at bottom of the screen.Thank you.";
                    textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                }if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(MainActivity.this, "Language not supported.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]
                            {
                                    Manifest.permission.RECORD_AUDIO
                            }, 100);
        }
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openactivity4();
            }
        });
        capture_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "You have clicked currency button.click capture to capture currency";
                int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                openactivity2();
            }
        });
        capture_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "You have clicked menu button.click capture to capture menu or click Menus button to check our default menus.";
                int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                openactivity3();
            }
        });
        voicecomander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
    }

    private void promptSpeechInput() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly speak Currency to click Currency button or speak Menu to click Menu button or speak help to click help button.");
        try {
            startActivityForResult(speachintent, 001);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 001: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.contains("currency") || result.contains("Currency")|| result.contains("see")|| result.contains("CURRENCY")|| result.contains("c")) {
                        String s = "You have clicked Currency button.click capture to capture currency or use our voice commander and speak capture.";
                        int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                        openactivity2();
                    }else
                    if (result.contains("menu") || result.contains("Menu")|| result.contains("MENU")|| result.contains("m")) {
                        String s = "You have clicked menu button.click capture to capture menu or click Menus button to check our default menus or use our voice commander and speak capture or menu.";
                        int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                        openactivity3();
                    }else
                    if (result.contains("help") || result.contains("Help")|| result.contains("HELP")|| result.contains("h")) {
                        openactivity4();
                    }
                    else {
                        speech("Sorry speak C for currency, M for menu or H for help and try again. Thank you.");
                    }
                }
                break;
            }
        }
    }

    public void play(){

        speech("Kindly speak Currency to click Currency button or speak Menu to click Menu button or speak help to click help button.");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInput();
            }

        } while (speakingEnd);

    }


    private void openactivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    private void openactivity3() {
        Intent intent = new Intent(this, MainMenu3Activity.class);
        startActivity(intent);
    }
    private void openactivity4() {
        Intent intent = new Intent(this, helpActivity3.class);
        startActivity(intent);
    }
    private void toastMessage(String message)
    {
        Toast.makeText(MainActivity.this,message, LENGTH_LONG).show();
    }
    private void speech(String message)
    {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
}