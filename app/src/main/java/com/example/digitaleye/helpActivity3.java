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

public class helpActivity3 extends AppCompatActivity {
    Button stop_sp , abt_menu , abt_currency;
    TextView contact , privacy;
    TextToSpeech textToSpeech;
    ImageView voicecomander;
    int i;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode== 100)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                speech("Permission Granted.");
                if (ContextCompat.checkSelfPermission(helpActivity3.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    speech("Kindly allow digital eye to use call manager.");
                    ActivityCompat.requestPermissions(helpActivity3.this,
                            new String[]
                                    {
                                            Manifest.permission.CALL_PHONE
                                    }, 001);
                }
            }else
            {
                speech("Kindly allow digital eye to Record your Voice.");
            }
        }else if (requestCode== 001)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                speech("Permission Granted.");
            }else
            {
                speech("Kindly allow digital eye to use call manager.");
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help3);
        stop_sp = findViewById(R.id.stop);
        abt_currency = findViewById(R.id.about_currency);
        abt_menu =  findViewById(R.id.about_menu);
        contact =   findViewById(R.id.contact_us);
        privacy =   findViewById(R.id.pri_con);
        voicecomander = findViewById(R.id.icon_helpactivity);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                    String mm ="You have clicked the help button. Thanks for choosing digital eye as your currency and menu recognizer. There are two major modules handled by our application. 1st one is  currency recognition and 2nd one is  menu recognition. Pleas speak about currency or speak about menu using our voice commander. You can also contact us by speaking the word call through our voice commander.You can listen our privacy terms and conditions by speaking the word privacy through our voice commander. Thank you";
                    int speech = textToSpeech.speak(mm,TextToSpeech.QUEUE_FLUSH,null);
                }if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(helpActivity3.this, "Language not supported.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (ContextCompat.checkSelfPermission(helpActivity3.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(helpActivity3.this,
                    new String[]
                            {
                                    Manifest.permission.RECORD_AUDIO
                            }, 100);
        }

        stop_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
            }
        });
        abt_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "About currency. In our currency module, we are providing facility of perceiving currency through text recognition from currency note. There is a voice commander in currency module placed at same center bottom position. you can use it and say capture to open camera. Then you have to click image from text side, if there is no text perceived by our application, our application will tell you through voice and then flip the note and capture again. If picture is clicked and perceived correctly, our application will speak the currency worth through voice. You can also repeat or listen total amount you have perceived in single go. Your perception will be adding in the local device database, you can also verify by showing your perceptions to some one you know or believe.";
                speech(message);

            }
        });
        abt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "About menu. In our menu module, we are providing facility of perceiving menu through text recognition from menu. There is a voice commander in menu module placed at same center bottom position. you can use it and say capture to open camera. Then you have to click image from text side, if there is no text perceived by our application, our application will tell you through voice and then flip the menu and capture again. If picture is clicked and perceived correctly, our application will speak the menu items through voice. You can also repeat the results by clicking repeat button placed at bottom of screen. You can also listen predefined restaurants menus by saying menu through our voice commander, restaurants list activity will open you can select restaurant using voice commander placed on screen ask some one to help you find that voice recognizer button. After selecting restaurant you have to select city , branch and menu item which you have to perceive, After perceiving you can place call to your selected branch by saying word call.";
                speech(message);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            play();
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity();
            }
        });
        voicecomander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playhelp();
            }
        });
    }
    private void promptSpeechInput() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak the word Call. If you want to contact digital eye customer service. Thank you.");
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
                    if (result.contains("Call") || result.contains("call") || result.contains("CALL")) {

                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:03315040027"));
                        startActivity(intent);
                        return;
                    }
                }
                break;
            }
            case 002:
                {
                    if (resultCode == RESULT_OK && null != data) {
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (result.contains("currency")||result.contains("About currency")|| result.contains("About Currency") || result.contains("about currency") || result.contains("Currency")|| result.contains("see")|| result.contains("CURRENCY")|| result.contains("c")) {
                            String message = "About currency. In our currency module, we are providing facility of perceiving currency through text recognition from currency note. There is a voice commander placed at same center bottom position you can use it and say capture to open camera. Then you have to click image from text side, if there is no text perceived by our application, our application will tell you through voice and then flip the note and capture again. If picture is clicked and perceived correctly, our application will speak the currency worth through voice. You can also repeat or listen total amount you have perceived in single go. Your perception is adding in the local device database, you can also verify by showing your perceptions to some one you know or believe.";
                            int speech = textToSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null);
                            return;
                        }else
                        if (result.contains("menu")||result.contains("About menu")||result.contains("about menu")||result.contains("About Menu") || result.contains("Menu")|| result.contains("MENU")|| result.contains("m")) {
                            String message = "About menu. In our menu module, we are providing facility of perceiving menu through text recognition from menu. There is a voice commander placed at same center bottom position you can use it and say capture to open camera. Then you have to click image from text side, if there is no text perceived by our application, our application will tell you through voice and then flip the menu and capture again. If picture is clicked and perceived correctly, our application will speak the menu items through voice. You can also repeat the results by clicking repeat button placed at bottom of screen. You can also listen predefined restaurants menus by saying menu through our voice commander, restaurants list activity will open you can select restaurant using voice commander placed on screen ask some one to help you find that voice recognizer button. After selecting restaurant you have to select city , branch and menu item which you have to perceive, After perceiving you can place call to your selected branch by saying word call.";
                            int speech = textToSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null);
                            return;

                        }else
                        if (result.contains("privacy") || result.contains("p")|| result.contains("pee")|| result.contains("P")||result.contains("Privacy")) {
                            openactivity();
                            return;
                        }else if (result.contains("Call") || result.contains("call") || result.contains("CALL"))
                        {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:03315040027"));
                            startActivity(intent);
                            return;
                        }
                        else {
                            speech("Sorry speak C for currency, M for menu, P for privacy or call to make call and try again. Thank you.");
                        }
                    }
                    break;
                }
        }
    }
    private void promptSpeechInputhelp() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly speak about Currency or speak about Menu or speak privacy or speak the word call. Thank you");
        try {
            startActivityForResult(speachintent, 002);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    public void playhelp(){

        speech("Kindly speak Currency to click Currency button or speak Menu to click Menu button and speak help to click help button.");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInputhelp();
            }

        } while (speakingEnd);

    }
    public void play(){

        speech("Kindly speak about Currency to click about Currency button or speak about Menu to click about Menu button or speak privacy to click privacy terms and condition button button or speak the word call if you want to contact digital eye customer service. Thank you");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInput();
            }

        } while (speakingEnd);

    }

    private void openactivity() {
        Intent intent = new Intent(this, privacyActivity3.class);
        startActivity(intent);
    }
    private void speech(String message)
    {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
}