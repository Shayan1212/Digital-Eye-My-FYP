package com.example.digitaleye;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.digitaleye.Model.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainMenu3Activity extends AppCompatActivity {
    ImageView imgview , voicecomandermenu;
    Bitmap bitmap;
    Button repeat , capture , menu;
    TextToSpeech textToSpeech;
    int i;

    Item item1 = new Item();
    Item item2 = new Item();
    Item item3 = new Item();
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode== 001)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                speech("Permission Granted.");
                if (ContextCompat.checkSelfPermission(MainMenu3Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainMenu3Activity.this,
                            new String[]
                                    {
                                            Manifest.permission.CAMERA
                                    }, 100);
                }
            }else
            {
                speech("Kindly allow digital eye to use call manager.");
            }
        }
        else if (requestCode== 100)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                speech("Permission Granted.");
            }else
            {
                speech("Kindly allow digital eye to use camera feature.");
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu3);
        voicecomandermenu = findViewById(R.id.icon_menuactivity);
        imgview = findViewById(R.id.imageView_1);
        repeat = findViewById(R.id.repeat);
        capture = findViewById(R.id.capture_image);
        menu = findViewById(R.id.menu_image);
        repeat.setVisibility(View.GONE);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(MainMenu3Activity.this, "Language not supported.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (ContextCompat.checkSelfPermission(MainMenu3Activity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainMenu3Activity.this,
                    new String[]
                            {
                                    Manifest.permission.CALL_PHONE
                            }, 001);
        }
        voicecomandermenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playcommander();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech("You have clicked Menus button. Choose your favorite restaurant for an Order Thank you.");
                openactivity();
            }
        });
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "Capture.";
                int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 002);
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    if (result.contains("call")) {

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:900..."));
                            startActivity(intent);
                            return;
                    }
                }
                break;
            }

            case 002: {
                if (resultCode == RESULT_OK && null != data) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    imgview.setImageBitmap(bitmap);
                    runTextRecognition(bitmap);
                }
                break;
            }
            case 003:
                {
                    if (resultCode == RESULT_OK && null != data) {
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (result.contains("capture") || result.contains("Capture")|| result.contains("see")|| result.contains("c")|| result.contains("CAPTURE")) {
                            String s = "Capture.";
                            int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 002);
                        }else
                        if (result.contains("menu") || result.contains("Menu")|| result.contains("MENU")|| result.contains("m"))
                            {
                                speech("You have clicked Menus button. Choose your favorite restaurant for an Order Thank you.");
                                openactivity();
                        }
                        else {
                            speech("Sorry speak C for Capture or speak M for Menu and try again. Thank you.");
                        }
                    }
                    break;
                }
        }
    }
    private void runTextRecognition(Bitmap currencyImage) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(currencyImage);
        FirebaseVisionTextRecognizer recognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

        recognizer.processImage(image)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText texts) {
                                Log.e("task", "success");
                                processTextRecognitionResult(texts);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("task", "failed");
                                e.printStackTrace();
                                Toast.makeText(MainMenu3Activity.this, "failure", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void processTextRecognitionResult(FirebaseVisionText texts) {
        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();

        String message = "";
        if (blocks.size() == 0) {
            message = "Unable to read text, please try again!";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(message, textToSpeech.QUEUE_FLUSH, null, null);

            } else {
                textToSpeech.speak(message, textToSpeech.QUEUE_FLUSH, null);
            }
            onBackPressed();

        }else {
            Log.e("blocks size", String.valueOf(blocks.size()));

            List<String> textRead = new ArrayList<>();

            List<FirebaseVisionText.Line> lines;
            List<FirebaseVisionText.Element> elements;

            // first loop till blocks size
            for (int i = 0; i < blocks.size(); i++) {
                // get lines from each block
                lines = blocks.get(i).getLines();
                // second loop till lines size
                for (int j = 0; j < lines.size(); j++) {
                    // get element from each line
                    elements = lines.get(j).getElements();
                    // third loop till elements size
                    for (int k = 0; k < elements.size(); k++) {
                        // get text from each element
                        String text = elements.get(k).getText();
                        Log.e("text", text);
                        // get text length
                        int size = text.length();
                        // add text to list
                        textRead.add(text);
                    }
                }
            }

            if(textRead.size()>0) {

                for(int i=0; i<textRead.size(); i++){
                    Log.e("textRead", textRead.get(i));
                    if(i==0){
                        item1.setPrice(textRead.get(i));

                    }else if(i==1){
                        item2.setPrice(textRead.get(i));

                    }else if(i==2){
                        item3.setPrice(textRead.get(i));

                    }else if(i==3){
                        item1.setfName(textRead.get(i));

                    }else if(i==4){
                        item1.setlName(textRead.get(i));

                    }else if(i==5){
                        item2.setfName(textRead.get(i));

                    }else if(i==6){
                        item2.setlName(textRead.get(i));

                    }else if(i==7){
                        item3.setfName(textRead.get(i));

                    }else if(i==8){
                        item3.setlName(textRead.get(i));
                    }
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    play();
                    repeat.setVisibility(View.VISIBLE);

                } else {
                    textToSpeech.speak(message, textToSpeech.QUEUE_FLUSH, null);
                }


            }else{
                message = "Some error occurred while processing, please try again!";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(message, textToSpeech.QUEUE_FLUSH, null, null);

                } else {
                    textToSpeech.speak(message, textToSpeech.QUEUE_FLUSH, null);
                }

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        }

    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speak the word call in order to make a call");
        try {
            startActivityForResult(speachintent, 001);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }


    public void play(){
        textToSpeech.speak("Item 1 name is "+item1.getfName()+item1.getlName()+" and price is "+item1.getPrice()+
                        " Item 2 name is " + item2.getfName()+item2.getlName()+" and price is "+item2.getPrice()+
                        " and Item 3 name is " + item3.getfName()+item3.getlName()+" and price is "+item3.getPrice()+
                        " now please speak the word call in order to make a call",
                textToSpeech.QUEUE_FLUSH, null, null);

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInput();
            }

        } while (speakingEnd);

    }
    private void speech(String message)
    {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void openactivity() {
        Intent intent = new Intent(this, Menulist.class);
        startActivity(intent);
    }

    private void promptSpeechInputcommander() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly speak Capture to click Capture button or speak Menu to click Menu Button..");
        try {
            startActivityForResult(speachintent, 003);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    public void playcommander(){

        speech("Kindly speak Capture to click Capture button or speak Menu to click Menu Button.");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInputcommander();
            }

        } while (speakingEnd);

    }


}