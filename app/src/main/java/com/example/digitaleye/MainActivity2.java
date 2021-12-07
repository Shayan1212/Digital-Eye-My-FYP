package com.example.digitaleye;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.FloatArrayEvaluator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
// import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    Button capture_image, repeat_btn, total_btn , history_btn;//, predict_image;
    int i, total0 = 0, total1 = 0, notval = 0;
    ImageView imgview;
    ImageView voicecomandercurrency;
    String message;
    String note="";
    String noteVal="";
    //int notevalue=0;
    TextView ctextView, ttextView2,totextView3;
    TextToSpeech textToSpeech;
    Bitmap imageBitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String[] numNames =
            {
                    "",
                    " one",
                    " two",
                    " three",
                    " four",
                    " five",
                    " six",
                    " seven",
                    " eight",
                    " nine",
                    " ten",
                    " eleven",
                    " twelve",
                    " thirteen",
                    " fourteen",
                    " fifteen",
                    " sixteen",
                    " seventeen",
                    " Eighteen",
                    " nineteen"
            };
    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " fourty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety",
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode== 100)
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
        setContentView(R.layout.activity_main2);
        mDatabaseHelper = new DatabaseHelper(this);
        capture_image = findViewById(R.id.capture_image);
        repeat_btn = findViewById(R.id.repeat);
        total_btn = findViewById(R.id.total);
        history_btn =findViewById(R.id.history);
        voicecomandercurrency = findViewById(R.id.icon_currencyactivity);
      //  predict_image = findViewById(R.id.predict_image);
        imgview = findViewById(R.id.imageView);
        ctextView = findViewById(R.id.margin);
        ttextView2 = findViewById(R.id.margin2);
        totextView3 =findViewById(R.id.total2);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(MainActivity2.this, "Language not supported.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity2.this,
                    new String[]
                            {
                                    Manifest.permission.CAMERA
                            }, 100);
        }

        voicecomandercurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity1();
            }
        });
        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "Capture.";
                int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
                /*dispatchTakePictureIntent();*/
            }
        });
repeat_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       String c =  ctextView.getText().toString();
       if (c == null || c.isEmpty() ||c == "")
       {
           int speech = textToSpeech.speak("Click capture button to capture currency.", TextToSpeech.QUEUE_FLUSH, null);
       }else
           if (c == "10")
           {
               String message = "TEN RUPEES";
               int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
           }if (c == "20")
        {
            String message = "TWENTY RUPEES";
            int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }if (c == "50")
        {
            String message = "FIFTY RUPEES";
            int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }if (c == "100")
        {
            String message = "ONE HUNDRED RUPEES";
            int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }if (c == "500")
        {
            String message = "FIVE HUNDRED RUPEES";
            int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }if (c == "1000")
        {
            String message = "ONE THOUSAND RUPEES";
            int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }if (c == "5000")
        {
            String message = "FIVE THOUSAND RUPEES";
            int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
});

total_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String ab = totextView3.getText().toString();
        if (ab == null || ab.isEmpty() || ab == "")
        {            int speech = textToSpeech.speak("Pleas Capture Note.", TextToSpeech.QUEUE_FLUSH, null);}
        else {
            int dec = Integer.parseInt(totextView3.getText().toString());
            String text = convert(dec);
            int speech = textToSpeech.speak(" Your total is " + text + " Rupees.", TextToSpeech.QUEUE_FLUSH, null);
    }}
});

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 001)
        {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result.contains("capture") || result.contains("Capture")|| result.contains("see")|| result.contains("c")|| result.contains("CAPTURE")) {
                    String s = "Capture.";
                    int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 100);
                }else if (result.contains("repeat")||result.contains("Repeat")||result.contains("REPEAT"))
                {
                    String c =  ctextView.getText().toString();
                    if (c == null || c.isEmpty() ||c == "")
                    {
                        int speech = textToSpeech.speak("Click capture button to capture currency.", TextToSpeech.QUEUE_FLUSH, null);
                    }else
                    if (c == "10")
                    {
                        String message = "TEN RUPEES";
                        int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                    }if (c == "20")
                {
                    String message = "TWENTY RUPEES";
                    int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }if (c == "50")
                {
                    String message = "FIFTY RUPEES";
                    int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }if (c == "100")
                {
                    String message = "ONE HUNDRED RUPEES";
                    int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }if (c == "500")
                {
                    String message = "FIVE HUNDRED RUPEES";
                    int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }if (c == "1000")
                {
                    String message = "ONE THOUSAND RUPEES";
                    int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }if (c == "5000")
                {
                    String message = "FIVE THOUSAND RUPEES";
                    int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }
                }else if (result.contains("total")||result.contains("Total")||result.contains("TOTAL"))
                {
                    String ab = totextView3.getText().toString();
                    if (ab == null || ab.isEmpty() || ab == "")
                    {            int speech = textToSpeech.speak("Pleas Capture Note.", TextToSpeech.QUEUE_FLUSH, null);}
                    else {
                        int dec = Integer.parseInt(totextView3.getText().toString());
                        String text = convert(dec);
                        int speech = textToSpeech.speak(" Your total is " + text + " Rupees.", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                else {
                    speech("Sorry speak C for Capture and try again. Thank you.");
                }
            }

        }else
        if (requestCode == 100) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgview.setImageBitmap(imageBitmap);
            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
            FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
            detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                @Override
                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                    processText(firebaseVisionText);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }
    }

    private void processText(FirebaseVisionText text) {
        List<FirebaseVisionText.TextBlock> blocks = text.getTextBlocks();
        if (blocks.size()==0){
            Toast.makeText(MainActivity2.this,  "NO Text :(", LENGTH_LONG).show();
            message = "Unable to read text, please try again!";
            int speech = textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            return;
        }
        for (FirebaseVisionText.TextBlock block : text.getTextBlocks() ){
            String txt = block.getText();
          //  String x = txt.toString();
            if (txt.contains("TEN RUPEES")|| txt.contains("ten rupees")|| (txt.contains("ten") && txt.contains("rupees")) || (txt.contains("TEN") && txt.contains("RUPEES")))
            {
                note = "TEN RUPEES";
                noteVal = "10";
                notval = 10;
                ctextView.setText(noteVal);
                ttextView2.setText(getCurrentTimeStamp());
                int speech = textToSpeech.speak(note, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(MainActivity2.this,  "TEN RUPEES :)", LENGTH_LONG).show();
                String a = totextView3.getText().toString();
                String b = ctextView.getText().toString();
                String ts = ttextView2.getText().toString();
                AddData(noteVal , getCurrentTimeStamp().toString());
                if(b == null || b.isEmpty() || b == ""){
                    if (a == null || a.isEmpty() || a == "")
                    {
                        noteVal= "";
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            notval = 0;
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
                else{
                if (a == null || a.isEmpty() || a == "")
                {
                    totextView3.setText(noteVal);
                    return;
                }
                else
                {
                    try {
                        total0 = Integer.parseInt(a);
                        total1 = notval + total0;
                        String t = Integer.toString(total1);
                        totextView3.setText(t);
                    } catch(NumberFormatException nfe) {
                        Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                    }
                    return;
                }
                }
            }
            else
            if ( txt.contains("Twenty RUPEES")|| txt.contains("twenty rupees")|| txt.contains("TWENTY RUPEES")||(txt.contains("TWENTY") && txt.contains("RUPEES")) || (txt.contains("Twenty") && txt.contains("RUPEES")))
            {
                note = "Twenty RUPEES";
                noteVal = "20";
                ctextView.setText(noteVal);
                ttextView2.setText(getCurrentTimeStamp());
                int speech = textToSpeech.speak(note, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(MainActivity2.this,  "TWENTY RUPEES :)", LENGTH_LONG).show();
                notval= 20;
                String a = totextView3.getText().toString();
                String b = ctextView.getText().toString();
                String ts = ttextView2.getText().toString();
                AddData(noteVal , ts);
                if(b == null || b.isEmpty() || b == ""){
                    if (a == null || a.isEmpty() || a == "")
                    {
                        noteVal= "";
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            notval = 0;
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
                else{
                    if (a == null || a.isEmpty() || a == "")
                    {
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
            }
            else
            if ( txt.contains("FIFTY RUPEES")|| txt.contains("fifty rupees")|| (txt.contains("fifty") && txt.contains("rupees")) || (txt.contains("FIFTY") && txt.contains("RUPEES")))
            {
                note = "FIFTY RUPEES";
                noteVal = "50";
                ctextView.setText(noteVal);
                ttextView2.setText(getCurrentTimeStamp());
                int speech = textToSpeech.speak(note, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(MainActivity2.this,  "FIFTY RUPEES :)", LENGTH_LONG).show();
                notval= 50;
                String a = totextView3.getText().toString();
                String b = ctextView.getText().toString();
                String ts = ttextView2.getText().toString();
                AddData(noteVal , ts);
                if(b == null || b.isEmpty() || b == ""){
                    noteVal= "";
                    if (a == null || a.isEmpty() || a == "")
                    {
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        notval = 0;
                        try {
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
                else{
                if (a == null || a.isEmpty() || a == "")
                {
                    totextView3.setText(noteVal);
                    return;
                }
                else
                {
                    try {
                        total0 = Integer.parseInt(a);
                        total1 = notval + total0;
                        String t = Integer.toString(total1);
                        totextView3.setText(t);
                    } catch(NumberFormatException nfe) {
                        Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                    }
                    return;
                }
            }}
            else
            if (txt.contains("ONE HUNDRED RUPEES")||txt.contains("one hundred rupees")|| (txt.contains("one") && txt.contains("hundred")) || (txt.contains("ONE") && txt.contains("HUNDRED")))
            {
                note = "ONE HUNDRED RUPEES";
                noteVal = "100";
                ctextView.setText(noteVal);
                ttextView2.setText(getCurrentTimeStamp());
                int speech = textToSpeech.speak(note, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(MainActivity2.this,  "ONE HUNDRED RUPEES :)", LENGTH_LONG).show();
                notval= 100;
                String a = totextView3.getText().toString();
                String b = ctextView.getText().toString();
                String ts = ttextView2.getText().toString();
                AddData(noteVal , ts);
                if(b == null || b.isEmpty() || b == ""){
                    if (a == null || a.isEmpty() || a == "")
                    {
                        noteVal= "";
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            notval = 0;
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
                else{
                    if (a == null || a.isEmpty() || a == "")
                    {
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
            }
            else
            if (txt.contains("FIVE HUNDRED RUPEES")||txt.contains("five hundred rupees") || (txt.contains("five") && txt.contains("hundred")) || (txt.contains("FIVE") && txt.contains("HUNDRED")))
            {
                note = "FIVE HUNDRED RUPEES";
                noteVal = "500";
                ctextView.setText(noteVal);
                ttextView2.setText(getCurrentTimeStamp());
                int speech = textToSpeech.speak(note, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(MainActivity2.this,  "FIVE HUNDRED RUPEES :)", LENGTH_LONG).show();
                notval= 500;
                String a = totextView3.getText().toString();
                String b = ctextView.getText().toString();
                String ts = ttextView2.getText().toString();
                AddData(noteVal , ts);
                if(b == null || b.isEmpty() || b == ""){
                    if (a == null || a.isEmpty() || a == "")
                    {
                        noteVal= "";
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            notval = 0;
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
                else{
                    if (a == null || a.isEmpty() || a == "")
                    {
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
            }
            else
            if ( txt.contains("ONE THOUSAND RUPEES")||txt.contains("one thousand rupees")|| (txt.contains("one") && txt.contains("thousand")) || (txt.contains("ONE") && txt.contains("THOUSAND")))
            {
                note = "ONE THOUSAND RUPEES";
                noteVal = "1000";
                ctextView.setText(noteVal);
                ttextView2.setText(getCurrentTimeStamp());
                int speech = textToSpeech.speak(note, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(MainActivity2.this,  "ONE THOUSAND RUPEES :)", LENGTH_LONG).show();
                notval= 1000;
                String a = totextView3.getText().toString();
                String b = ctextView.getText().toString();
                String ts = ttextView2.getText().toString();
                AddData(noteVal , getCurrentTimeStamp());
                if(b == null || b.isEmpty() || b == ""){
                    if (a == null || a.isEmpty() || a == "")
                    {
                        noteVal= "";
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            notval = 0;
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
                else{
                    if (a == null || a.isEmpty() || a == "")
                    {
                        totextView3.setText(noteVal);
                        return;

                    }
                    else
                    {
                        try {
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
            }
            else
            if (txt.contains("FIVE THOUSAND RUPEES")||txt.contains("five thousand rupees")|| (txt.contains("five") && txt.contains("thousand")) || (txt.contains("FIVE") && txt.contains("THOUSAND")))
            {
                note = "FIVE THOUSAND RUPEES";
                noteVal = "5000";
                ctextView.setText(noteVal);
                ttextView2.setText(getCurrentTimeStamp());
                int speech = textToSpeech.speak(note, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(MainActivity2.this,  "FIVE THOUSAND RUPEES :)", LENGTH_LONG).show();
                notval= 5000;
                String a = totextView3.getText().toString();
                String b = ctextView.getText().toString();
                String ts = ttextView2.getText().toString();
                AddData(noteVal , ts);
                if(b == null || b.isEmpty() || b == ""){
                    if (a == null || a.isEmpty() || a == "")
                    {
                        noteVal= "";
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            notval = 0;
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
                else{
                    if (a == null || a.isEmpty() || a == "")
                    {
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
            }
            else
                {
                    note = "";
                    noteVal = "";
                    ctextView.setText(noteVal);
                    ttextView2.setText("");
                    int speech = textToSpeech.speak("Unable to read text pleas try again.", TextToSpeech.QUEUE_FLUSH, null);
                    notval= 0;
                    String a = totextView3.getText().toString();
                    if (a == null || a.isEmpty() || a == "")
                    {
                        totextView3.setText(noteVal);
                        return;
                    }
                    else
                    {
                        try {
                            total0 = Integer.parseInt(a);
                            total1 = notval + total0;
                            String t = Integer.toString(total1);
                            totextView3.setText(t);
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(MainActivity2.this,  "Could not parse " + nfe, LENGTH_LONG).show();
                        }
                        return;
                    }
                }
        }

    }

 private static String convertLessThanOneThousand(int number)
 {
            String soFar;
            if (number % 100 < 20)
            {
                soFar = numNames[number % 100];
                number /=100;
            }else
                {
                    soFar = numNames[number %10];
                    number /=10;

                    soFar = tensNames[number % 10] + soFar;
                    number /=10;
                }
            if (number==0)  return soFar;
            return numNames[number]+" hundred"+ soFar;
 }
    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "zero";
        }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(snumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9, 12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1 :
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(millions)
                        + " million ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions)
                        + " million ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "one thousand ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " thousand ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

 public static String getCurrentTimeStamp(){
     try {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String currentDateTime = dateFormat.format(new Date());
         return currentDateTime;

     } catch (Exception e) {
         e.printStackTrace();
         return "N/A";
     }
 }
 private void AddData(String item1 , String item2)
 {
     boolean insertData = mDatabaseHelper.addData(item1,item2);
     if (insertData == true)
     {
         toastMessage("Data Successfully Inserted");

     }else
         {
             toastMessage("Something went wrong while adding data.");
             speech("Something went wrong while adding data.");
         }
 }
 private void toastMessage(String message)
 {
     Toast.makeText(MainActivity2.this,message, LENGTH_LONG).show();
 }
 private void speech(String message)
 {
     textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
 }
 private void openactivity1()
 {
        Intent intent = new Intent(this, list_layout.class);
        startActivity(intent);
 }

    private void promptSpeechInput() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly speak Capture to click Capture button.");
        try {
            startActivityForResult(speachintent, 001);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    public void play(){

        speech("Kindly speak Capture to click Capture button.");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInput();
            }

        } while (speakingEnd);

    }


}