package com.example.digitaleye;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class Menulist extends AppCompatActivity {
    Button stop,rest_list1;
    ImageView voicecomand, main_img,getPizza_1,getkfc_1;
    TextToSpeech textToSpeech;
    int i;
    TextView textv1,pizza_1,kfc_1;

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
        setContentView(R.layout.activity_menulist);
        stop = findViewById(R.id.stop1);
        rest_list1 = findViewById(R.id.rest_list);
        voicecomand = findViewById(R.id.icon_22);
        main_img = findViewById(R.id.main_imgview);
        textv1 = findViewById(R.id.tv_1);
        getPizza_1 = findViewById(R.id.pizza);
        pizza_1 = findViewById(R.id.pizzaa);
        getkfc_1 = findViewById(R.id.kfc);
        kfc_1 = findViewById(R.id.kfcc);
        textv1.setText("Restaurant Name");
        main_img.setImageResource(R.drawable.rs3);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (i == TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(Menulist.this, "Language not supported.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (ContextCompat.checkSelfPermission(Menulist.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Menulist.this,
                    new String[]
                            {
                                    Manifest.permission.RECORD_AUDIO
                            }, 100);
        }


        rest_list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item1,item2;
                item1="Pizza Hut";
                item2 = "KFC";
                String list =" Currently we have got menus of " + item1 + " and " + item2 + " in our restaurants list . Thank you. ";
                speech(list);
            }
        });
        String tv1_text = textv1.getText().toString();
        textv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tv1_text = textv1.getText().toString();
                if (tv1_text.contains("Pizza Hut")){
                    speech("You have selected pizza hut for your order.");
                openactivity_pizzahut();
                    main_img.setImageResource(R.drawable.rs3);
                    textv1.setText("Restaurant Name");
            }
                else
                    if (tv1_text.contains("KFC"))
                    {
                        speech("You have selected KFC for your order.");
                        openactivity_kfc();
                        main_img.setImageResource(R.drawable.rs3);
                        textv1.setText("Restaurant Name");
                    }else
                        {
                            speech("Kindly Select Restaurant for your Order.");
                        }

            }
        });
        main_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tv1_text = textv1.getText().toString();
                if (tv1_text.contains("Pizza Hut")){
                    speech("You have selected pizza hut for your order.");
                openactivity_pizzahut();
                    main_img.setImageResource(R.drawable.rs3);
                    textv1.setText("Restaurant Name");
                }
                else
                    if (tv1_text.contains("KFC"))
                    {
                        speech("You have selected KFC for your order.");
                     openactivity_kfc();
                        main_img.setImageResource(R.drawable.rs3);
                        textv1.setText("Restaurant Name");
                    }else
                    {
                        speech("Kindly Select Restaurant for your Order.");
                    }

            }
        });
        pizza_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speech("You have selected pizza hut for your order.");
                openactivity_pizzahut();
                main_img.setImageResource(R.drawable.rs3);
                textv1.setText("Restaurant Name");
            }
        });
        getPizza_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speech("You have selected pizza hut for your order.");
                openactivity_pizzahut();
                main_img.setImageResource(R.drawable.rs3);
                textv1.setText("Restaurant Name");
            }
        });
        kfc_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                speech("You have selected KFC for your order.");
                openactivity_kfc();
                main_img.setImageResource(R.drawable.rs3);
                textv1.setText("Restaurant Name");
            }
        });
        getkfc_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech("You have selected KFC for your order.");
                openactivity_kfc();
                main_img.setImageResource(R.drawable.rs3);
                textv1.setText("Restaurant Name");
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
            }
        });
        voicecomand.setOnClickListener(new View.OnClickListener() {
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
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly Select Restaurant for your Order.");
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
                    if (result.contains("Pizza Hut") || result.contains("PIZZA HUT") || result.contains("Pizza hut") || result.contains("pizza hut")) {
                        speech("Item Found successfully.");
                        main_img.setImageResource( R.drawable.pizza_hut);
                        textv1.setText("Pizza Hut");
                    }else
                    if (result.contains("kfc") || result.contains("KFC") || result.contains("Kfc")) {
                        speech("Item Found successfully.");
                        main_img.setImageResource( R.drawable.kfc);
                        textv1.setText("KFC");
                    }
                    else {
                        textv1.setText("Restaurant Name");
                        main_img.setImageResource(R.drawable.rs3);
                        speech("Sorry No item Found.Pleas try again. Thank you.");
                    }
                }
                break;
            }
        }
    }
    public void play(){

        speech("Kindly Select restaurant for your order.");

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
        Toast.makeText(Menulist.this,message, LENGTH_LONG).show();
    }
    private void speech(String message)
    {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void openactivity_pizzahut() {
        Intent intent = new Intent(this, pizza_hut2.class);
        startActivity(intent);
    }
    private void openactivity_kfc() {
        Intent intent = new Intent(this, KFC.class);
        startActivity(intent);
    }
}