package com.example.digitaleye;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class pizza_hut2 extends AppCompatActivity {

    int count = 0;
    int x=0,p=0;
    TextView visiabletag1 , visiabletag2;
    HorizontalScrollView  visiablehorscrview1,visiablehorscrview2;
    ImageView visiablemenu;
    LinearLayout ll_pizzas;
    Button repeat_btn , stop_btn , special_deals, menu_items;
    ArrayList<String> Pizzahut_Branches;
    ImageView cityicon, cityicon1, branchicon,branchicon1, menuicon,menuicon1;
    TextToSpeech textToSpeech;
    int i;
    TextView citytv,menutv,branchtv;
    String placeisb1 = "Centaurus Mall Islamabad ";
    String placeisb2 = "DHA Phase-2 Islamabad ";
    String placeisb3 = "F-8 Markaz Islamabad ";
    String placeisb4 = "F-11 Markaz Islamabad ";
    String placeisb5 = "F-6 Markaz Islamabad ";
    String placeisb6 = "Giga Mall Islamabad ";
    String placeisb7 = "I-8 Markaz Islamabad ";
    String placeisb8 = "Naval Golf Course Islamabad ";
    String placeisb9 = "PWD Society Islamabad ";

    String placerwl1 = "Adamjee Rd Rawalpindi ";
    String placerwl2 = "Bahria Town Rawalpindi ";
    String placerwl3 = "Commercial Market Rawalpindi ";
    String placerwl4 = "Intellectual Village Rawalpindi ";
    String placerwl5 = "School Rd Rawalpindi  ";

    String avacities1 = "Islamabad";
    String avacities2 = "Rawalpindi";

    String pizzaflavour1 = "AFGHANI TIKKA";
    String pizzaflavour2 = "CHICKEN FAJITA";
    String pizzaflavour3 = "CHICKEN SUPREME";
    String pizzaflavour4 = "BEHARI CHICKEN";
    String pizzaflavour5 = "SPICY RANCH";
    String pizzaflavour6 = "SUPER SICILIAN";
    String pizzaflavour7 = "BBQ BUZZ";
    String pizzaflavour8 = "SEEKH KEBAB OVERLOADED";
    String small = "Rupees four hundred seventy nine";
    String medium = "Rupees twelve hundred seventy nine";
    String larg = "Rupees seventeen hundred seventy nine";
    String xl = "Rupees twenty four hundred seventy nine";
    String xxl = "Rupees twenty nine hundred seventy nine";

    String side1item1 = "Garlic Mushrooms";
    String priceitem1 = "Rupees Three hundred twenty five";
    String side1item2 = "Garlic Bread";
    String priceitem2 = "Rupees Three hundred sixty five";
    String side1item3 = "Garlic Bread Supreme";
    String priceitem3 = "Rupees Four hundred thirty five";
    String side1item4 = "Potato Skins";
    String priceitem4 = "Rupees Three hundred sixty five";
    String side1item5 = "Potato Wedges";
    String priceitem5 = "Rupees Three hundred ninety five";

    String side2item1 = "Chicken Wings";
    String s2priceitem1 = "Rupees Seven hundred fifty";
    String side2item2 = "BBQ Spin Rolls";
    String s2priceitem2 = "Rupees Five hundred";
    String side2item3 = "Behari Spin Rolls";
    String s2priceitem3 = "Rupees Five hundred";
    String side2item4 = "Flaming Wings";
    String s2priceitem4 = "Rupees Eight hundred fifty";

    String side3item1 = "Mexican chicken";
    String s3priceitem1 = "Rupees Five hundred sixty five";
    String side3item2 = "FootLong Lasagna";
    String s3priceitem2 = "Rupees Six hundred fifteen";
    String side3item3 = "Pizza pocket";
    String s3priceitem3mini = "Rupees Two hundred";
    String s3priceitem3regular = "Rupees Three hundred twenty five";

    String dessertitem1 = "Chocolicious brownie";
    String dessertpriceitem1 = "Rupees Three hundred twenty five";
    String dessertitem2 = "Chocolate lava cake";
    String dessertpriceitem2 = "Rupees Three hundred";

    String meatyeidfeastitem1 = "Two regular pizzas with plus Garlic Bread Supreme";
    String meatyeidfeastpriceitem1 = "Rupees Fourteen hundred ninety nine";
    String meatyeidfeastitem2 = "Buy one large pizza get one medium free";
    String meatyeidfeastpriceitem2 = "Rupees Seventeen hundred seventy nine";
    String meatyeidfeastitem3 = "Two large pizzas";
    String meatyeidfeastpriceitem3 = "Rupees Two thousand four hundred ninety nine ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_hut2);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
                if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(pizza_hut2.this, "Language not supported.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cityicon1 = findViewById(R.id.pizza_main_imgview);
        citytv = findViewById(R.id.pizza_tv_1);
        cityicon = findViewById(R.id.pizza_icon_22);
        branchicon1 = findViewById(R.id.pizza_main_imgview1);
        branchtv = findViewById(R.id.pizza_tv_2);
        branchicon = findViewById(R.id.pizza_icon_33);
        menuicon1 = findViewById(R.id.pizza_main_imgview2);
        menutv = findViewById(R.id.pizza_tv_3);
        menuicon = findViewById(R.id.pizza_icon_44);
        menu_items = findViewById(R.id.menu_list);
        stop_btn = findViewById(R.id.stop1);
        special_deals = findViewById(R.id.so);
        repeat_btn = findViewById(R.id.repeat_menu_btn);
        ll_pizzas  = findViewById(R.id.LL_pizzas);

        visiablemenu = findViewById(R.id.visible_Menu);

        visiabletag1 = findViewById(R.id.tag1);
        visiabletag2 = findViewById(R.id.tag2);
        visiablehorscrview1 = findViewById(R.id.pizza_deals);
        visiablehorscrview2 = findViewById(R.id.pizzamenuitem_SS_view);

        Pizzahut_Branches = new ArrayList<>();
        Pizzahut_Branches.add("Centaurus Mall Isb Branch");
        Pizzahut_Branches.add("DHA Phase-2 Isb Branch");
        Pizzahut_Branches.add("F-8 Markaz Isb Branch");
        Pizzahut_Branches.add("F-11 Markaz Isb Branch");
        Pizzahut_Branches.add("F-6 Markaz Isb Branch");
        Pizzahut_Branches.add("Giga Mall Isb Branch");
        Pizzahut_Branches.add("I-8 Markaz Isb Branch");
        Pizzahut_Branches.add("Naval Golf Course Isb Branch");
        Pizzahut_Branches.add("PWD Society Isb Branch");

stop_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        textToSpeech.stop();
    }
});
special_deals.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String offer1= "Meaty Eid Feast";
        String items= "Currently we have " + offer1 +" deal is on. if you want select and listen this deal, deal code is one.";
        speech(items);
    }
});
menu_items.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String menuitem1 = "Pizzas";
        String menuitem2 = "Side 1";
        String menuitem3 = "Side 2";
        String menuitem4 = "Side 3";
        String menuitem5 = "Desserts";
        String menuitem6 = "Special deals";
        String items = "Currently we have different menu items. we have " + menuitem1 + " , "+ menuitem2 + " , "+ menuitem3 + " , "+ menuitem4 + " and "+ menuitem5 + " in our menu list. we also have some " + menuitem6 + " if you want to listen click on special deal button placed left bottom side. Thank you " ;
        speech(items);
    }
});
repeat_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String menutvv = menutv.getText().toString();
        if (menutvv.contains("Pizzas")){
            String items = "Currently we have different flavours in our pizzas menu. we have got "+ pizzaflavour1 + ", " + pizzaflavour2 + ", " + pizzaflavour3 + ", " + pizzaflavour4 + ", " + pizzaflavour5 + ", " + pizzaflavour6 + ", " + pizzaflavour7 + " , and " + pizzaflavour8 + ", " +"with different prices according to size." + " Small " + small + ", " + " medium " + medium + ", " + " large " + larg + ", " + " XL " + xl + ", " + " and double XL in " + ", " + xxl+"." ;
            speech(items);
        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                playcall();
            }

        } while (speakingEnd);
    }else
        if (menutvv.contains("Side:1"))
        {
            String items = "Currently we have different items in our side one menu. we have got "+ side1item1 + " in " + priceitem1 + ", " + side1item2 + " in " + priceitem2 + ", " + side1item3 + " in " + priceitem3 + ", " + side1item4 + " in " + priceitem4 + " and " + side1item5 + " in " + priceitem5 + ".";
            speech(items);
            boolean speakingEnd = textToSpeech.isSpeaking();
            do{
                speakingEnd = textToSpeech.isSpeaking();

                if(!speakingEnd){
                    playcall();
                }

            } while (speakingEnd);

        }else
        if (menutvv.contains("Side:2"))
        {
            String items = "Currently we have different items in our side two menu. we have got "+ side2item1 + " in " + s2priceitem1 + ", " + side2item2 + " in " + s2priceitem2 + ", " + side2item3 + " in " + s2priceitem3 + " and " + side2item4 + " in " + s2priceitem4 + ".";
            speech(items);
            boolean speakingEnd = textToSpeech.isSpeaking();
            do{
                speakingEnd = textToSpeech.isSpeaking();

                if(!speakingEnd){
                    playcall();
                }

            } while (speakingEnd);

        }else
        if (menutvv.contains("Side:3"))
        {
            String items = "Currently we have different items in our side three menu. we have got "+ side3item1 + " in " + s3priceitem1 + ", " + side3item2 + " in " + s3priceitem2 + " and " + side3item3 + " mini size in " + s3priceitem3mini + " and regular size in " + s3priceitem3regular +".";
            speech(items);
            boolean speakingEnd = textToSpeech.isSpeaking();
            do{
                speakingEnd = textToSpeech.isSpeaking();

                if(!speakingEnd){
                    playcall();
                }

            } while (speakingEnd);

        }else
        if (menutvv.contains("Desserts"))
        {
            String items = "Currently we have different items in our dessert menu. we have got "+ dessertitem1 + " in " + dessertpriceitem1 + " and " + dessertitem2 + " in " + dessertpriceitem2 +".";
            speech(items);
            boolean speakingEnd = textToSpeech.isSpeaking();
            do{
                speakingEnd = textToSpeech.isSpeaking();

                if(!speakingEnd){
                    playcall();
                }

            } while (speakingEnd);

        }else
        if (menutvv.contains("Meaty Eid Feast"))
        {
            String items = "Currently we have different items in our special deal meaty eid feast menu. we have got "+ meatyeidfeastitem1 + " in " + meatyeidfeastpriceitem1 + " , " + meatyeidfeastitem2 + " in " + meatyeidfeastpriceitem2 + " and " + meatyeidfeastitem3 + " in " + meatyeidfeastpriceitem3 +".";
            speech(items);
            boolean speakingEnd = textToSpeech.isSpeaking();
            do{
                speakingEnd = textToSpeech.isSpeaking();

                if(!speakingEnd){
                    playcall();
                }

            } while (speakingEnd);
        }
        else
        if (menutvv.contains("Choose Menu"))
            {
                speech("Kindly select menu item for your order.");
            }else
                {
                    speech("Sorry no item found.");
                }

    }
});
ll_pizzas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    }
});
       /* SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);*/
        cityicon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String citytvv = citytv.getText().toString();
                if (citytvv.contains("City")) {
                    String cities = "Currently we are providing our services in " + avacities1 + " and " + avacities2 + "."+ " Kindly select city. Thank you";
                    speech(cities);
                } else {
                    String cities = citytvv;
                    speech(cities);

                }

            }
        });
        citytv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String citytvv = citytv.getText().toString();
                if (citytvv.contains("City")) {
                    String cities = "Currently we are providing our services in " + avacities1 + " and " + avacities2 + "."+ " Kindly select city. Thank you";
                    speech(cities);
                } else {
                    String cities = citytvv;
                    speech(cities);

                }
            }
        });

        cityicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    playcity();
                branchtv.setText("Branch-/");
            }
        });
        branchicon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String branchtvv = branchtv.getText().toString();

                if (branchtvv.contains("Branch-/")) {

                    String citytvv = citytv.getText().toString();
                    if (citytvv.contains("City")) {
                        speech("Kindly select city.");
                    } else if (citytvv.contains("Islamabad")) {
                        String list = " Currently we have got " + " branch one at " + placeisb1 + " branch two at " + placeisb2 + " branch three at " + placeisb3 + " branch four at " + placeisb4 + " branch five at " + placeisb5 + " branch six at " + placeisb6 + " branch seven at " + placeisb7 + " branch eight at " + placeisb8 + " and branch nine at " + placeisb9 + "." + " Kindly select branch by saying branch number for your order. Thank you. ";
                        speech(list);
                    } else if (citytvv.contains("Rawalpindi")) {
                        String list = " Currently we have got " + " branch one at " + placerwl1 + " branch two at " + placerwl2 + " branch three at " + placerwl3 + " branch four at " + placerwl4 + " and branch five at " + placerwl5 + "." + " Kindly select branch by saying branch number for your order. Thank you. ";
                        speech(list);
                    }else
                    {
                        speech("Sorry no branch found in this city. ");
                    }

                }else
                    {
                        String branch11 = " You have selected " + " " + branchtvv;

                        speech(branch11);

                    }

            }
        });
        branchtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String branchtvv = branchtv.getText().toString();

                if (branchtvv.contains("Branch-/")) {

                    String citytvv = citytv.getText().toString();
                    if (citytvv.contains("City")) {
                        speech("Kindly select city.");
                    } else if (citytvv.contains("Islamabad")) {
                        String list = " Currently we have got " + " branch one at " + placeisb1 + " branch two at " + placeisb2 + " branch three at " + placeisb3 + " branch four at " + placeisb4 + " branch five at " + placeisb5 + " branch six at " + placeisb6 + " branch seven at " + placeisb7 + " branch eight at " + placeisb8 + " and branch nine at " + placeisb9 + "." + " Kindly select branch by saying branch number for your order. Thank you. ";
                        speech(list);
                    } else if (citytvv.contains("Rawalpindi")) {
                        String list = " Currently we have got " + " branch one at " + placerwl1 + " branch two at " + placerwl2 + " branch three at " + placerwl3 + " branch four at " + placerwl4 + " and branch five at " + placerwl5 + "." + " Kindly select branch by saying branch number for your order. Thank you. ";
                        speech(list);
                    }else
                        {
                            speech("Sorry no branch found in this city. ");
                        }

                }else
                {
                    String branch11 = " You have selected " + " " + branchtvv;

                    speech(branch11);

                }

            }
        });
        branchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branchtvv = branchtv.getText().toString();
                String citytvv = citytv.getText().toString();
                if (citytvv.contains("City"))
                {
                    speech("Kindly select city.");
                }
                else if (branchtvv.contains("Branch-/")){
                    playbranch();
                }else
                {
                    branchtv.setText("Branch-/");
                    playbranch();
                }

            }
        });

        menuicon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String branchtvv = branchtv.getText().toString();
                String citytvv = citytv.getText().toString();
                String menutvv = menutv.getText().toString();
                if (menutvv.contains("Choose Menu"))
                {
                    if (citytvv.contains("City"))
                    {
                        speech("Kindly select city.");
                    }else
                    if (branchtvv.contains("Branch-/"))
                    {

                        speech("Kindly select branch for your order.");
                    }else
                    {
                        speech("Kindly select menu item for your order.  If you want to listen our menu items click on menu item button placed at center bottom side. And if you want to listen our special deals click special deals button placed at right side of menu items button. Thank you");
                    }
                }else
                {
                    String menu11 = "You have selected " + menutvv + " for your order.";
                    speech(menu11);
                    if (menutvv.contains("Pizzas"))
                    {
                        p=0;
                        x=0;
                        playpizzamenu();
                    }
                    else
                    if (menutvv.contains("Side:1"))
                    {
                        playpizzaside1();
                    }
                    else
                    if (menutvv.contains("Side:2"))
                    {
                        playpizzaside2();
                    }
                    else
                    if (menutvv.contains("Side:3"))
                    {
                        playpizzaside3();
                    }
                    else
                    if (menutvv.contains("Desserts"))
                    {
                        playpizzadessert();
                    } else
                    if (menutvv.contains("Meaty Eid Feast"))
                    {
                        playpizzaeidfeast();
                    }
                    else
                    {
                        speech("Sorry no item matches with your selected item.");
                    }

                }


            }
        });
        menutv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String branchtvv = branchtv.getText().toString();
                String citytvv = citytv.getText().toString();
                String menutvv = menutv.getText().toString();
                if (menutvv.contains("Choose Menu"))
                {
                    if (citytvv.contains("City"))
                    {
                        speech("Kindly select city.");
                    }else
                    if (branchtvv.contains("Branch-/"))
                    {

                        speech("Kindly select branch for your order.");
                    }else
                    {
                        speech("Kindly select menu item for your order.  If you want to listen our menu items click on menu item button placed at center bottom side. And if you want to listen our special deals click special deals button placed at right side of menu items button. Thank you");
                    }
                }else
                {
                    String menu11 = "You have selected " + menutvv + " for your order.";
                    speech(menu11);
                    if (menutvv.contains("Pizzas"))
                    {
                        p=0;
                        x=0;
                        playpizzamenu();
                    }
                    else
                    if (menutvv.contains("Side:1"))
                    {
                        playpizzaside1();
                    }
                    else
                    if (menutvv.contains("Side:2"))
                    {
                        playpizzaside2();
                    }
                    else
                    if (menutvv.contains("Side:3"))
                    {
                        playpizzaside3();
                    }
                    else
                    if (menutvv.contains("Desserts"))
                    {
                        playpizzadessert();
                    }else
                    if (menutvv.contains("Meaty Eid Feast"))
                    {
                        playpizzaeidfeast();
                    }
                    else
                    {
                        speech("Sorry no item matches with your selected item.");
                    }

                }
            }
        });
        menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String branchtvv = branchtv.getText().toString();
                String citytvv = citytv.getText().toString();
                String menutvv = menutv.getText().toString();
                if (citytvv.contains("City"))
                {
                    speech("Kindly select city.");
                }
                else
                    if (branchtvv.contains("Branch-/"))
                {
                    speech("Kindly select branch for your order.");
                }
                else
                    if (menutvv.contains("Choose Menu"))
                    {
                        playmenu();
                    }else
                        {
                            refresh();
                            playmenu();
                        }
            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 001: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.contains("islamabad")||result.contains("Islamabad")|| result.contains("Islamabad") ||result.contains("Iombard") || result.contains("lombard") || result.contains("ISLAMABAD") || result.contains("ISB") || result.contains("isb")) {
                        speech("Islamabad.");
                        citytv.setText("Islamabad");

                    }else
                    if (result.contains("rawalpindi") || result.contains("Rawalpindi") || result.contains("RAWALPINDI")) {
                        speech("Rawalpindi.");
                        citytv.setText("Rawalpindi");

                    }
                    else
                    if (result.contains("karachi") || result.contains("Karachi") || result.contains("KARACHI")) {
                        speech("karachi.");
                        citytv.setText("karachi");

                    }else
                    if (result.contains("peshawar") || result.contains("Peshawar") || result.contains("PESHAWAR")) {
                        speech("Peshawar.");
                        citytv.setText("Peshawar");
                    }
                    else {
                        citytv.setText("City");
                        speech("Sorry service unavailable in this city kindly listen available cities by clicking on city field. Thank you.");

                    }
                }
                break;
            }
            case 002:
                {
                    if (resultCode == RESULT_OK && null != data) {
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        String citytvv = citytv.getText().toString();
                        if (citytvv.contains("Islamabad"))
                        {

                    if (result.contains("1") || result.contains("one") || result.contains("One") || result.contains("ONE"))
                    {
                        speech("Centaurus Mall Islamabad");
                        branchtv.setText("Centaurus Mall Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

                    }else   if (result.contains("2") || result.contains("two") || result.contains("Two") || result.contains("TWO"))
                    {
                        speech("DHA Phase-2 Islamabad");
                        branchtv.setText("DHA Phase-2 Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                    }else   if (result.contains("3") || result.contains("three") || result.contains("Three") || result.contains("THREE"))
                    {
                        speech("F-8 Markaz Islamabad");
                        branchtv.setText("F-8 Markaz Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                    }else   if (result.contains("4") || result.contains("four") || result.contains("Four") || result.contains("FOUR"))
                    {
                        speech("F-11 Markaz Islamabad");
                        branchtv.setText("F-11 Markaz Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                    }else   if (result.contains("5") || result.contains("five") || result.contains("Five") || result.contains("FIVE"))
                    {
                        speech("F-6 Markaz Islamabad");
                        branchtv.setText("F-6 Markaz Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                    }else   if (result.contains("6") || result.contains("six") || result.contains("Six") || result.contains("SIX"))
                    {
                        speech("Giga Mall Islamabad");
                        branchtv.setText("Giga Mall Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                    }else   if (result.contains("7") || result.contains("seven") || result.contains("Seven") || result.contains("SEVEN"))
                    {
                        speech("I-8 Markaz Islamabad");
                        branchtv.setText("I-8 Markaz Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                    }else   if (result.contains("8") || result.contains("eight") || result.contains("Eight") || result.contains("EIGHT"))
                    {
                        speech("Naval Golf Course Islamabad");
                        branchtv.setText("Naval Golf Course Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

                    }else   if (result.contains("9") || result.contains("nine") || result.contains("Nine") || result.contains("NINE"))
                    {
                        speech("PWD Society Islamabad");
                        branchtv.setText("PWD Society Islamabad");
                        branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                    }else
                        {
                            speech("Sorry no branch found in this city. Click on branch field to listen available branches");
                        }
                        }
                        else
                            if (citytvv.contains("Rawalpindi"))
                        {
                            if (result.contains("1") || result.contains("one") || result.contains("One") || result.contains("ONE"))
                            {
                                speech("Adamjee Rd Rawalpindi");
                                branchtv.setText("Adamjee Rd Rawalpindi");
                                branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                            }else   if (result.contains("2") || result.contains("two") || result.contains("Two") || result.contains("TWO"))
                            {
                                speech("Bahria Town Rawalpindi");
                                branchtv.setText("Bahria Town Rawalpindi");
                                branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                            }else   if (result.contains("3") || result.contains("three") || result.contains("Three") || result.contains("THREE"))
                            {
                                speech("Commercial Market Rawalpindi");
                                branchtv.setText("Commercial Market Rawalpindi");
                                branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

                            }else   if (result.contains("4") || result.contains("four") || result.contains("Four") || result.contains("FOUR"))
                            {
                                speech("Intellectual Village Rawalpindi");
                                branchtv.setText("Intellectual Village Rawalpindi");
                                branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

                            }else   if (result.contains("5") || result.contains("five") || result.contains("Five") || result.contains("FIVE"))
                            {
                                speech("School Rd Rawalpindi Rawalpindi");
                                branchtv.setText("School Rd Rawalpindi Rawalpindi");
                                branchtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

                            }
                            else
                                {
                                    speech("Sorry no branch found in this city. Click on branch field to listen available branches");
                                }
                        }
                            else if (citytvv.contains("City"))
                            {
                                speech("Kindly select city.");
                            }
                        else

                        {
                            speech("Sorry no branch found . If you want to listen our branches kindly click on branch field.");
                        }

                    }
                    break;
                }
            case 003:
            {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.contains("pizza")|| result.contains("visa")|| result.contains("visas") || result.contains("Pizzas") || result.contains("pizzas") || result.contains("PIZZA") || result.contains("Pizza")||result.contains("pizzas")||result.contains("Pizzas")||result.contains("PIZZAS")) {
                        speech("Pizzas.");
                        menutv.setText("Pizzas");
                        menuicon1.setImageResource(R.drawable.pizzahutpizzas);
                    }else    if (result.contains("side1")|| result.contains("se1")|| result.contains("say 1")|| result.contains("s 1") || result.contains("side 1") || result.contains("side one") || result.contains("Side 1") || result.contains("SIDE 1")||result.contains("sideone")||result.contains("side 1 one")||result.contains("SIDE ONE")) {
                        speech("Side one.");
                        menutv.setText("Side:1");
                        menuicon1.setImageResource(R.drawable.pizzahutside1);
                    }else    if (result.contains("side2")|| result.contains("se2")|| result.contains("sodo")|| result.contains("say 2")|| result.contains("s 2")|| result.contains("so do")|| result.contains("sodo") || result.contains("side 2") || result.contains("side two") || result.contains("Side 2") || result.contains("SIDE 2")||result.contains("sidetwo")||result.contains("side 2 two")||result.contains("SIDE TWO")) {
                        speech("Side two.");
                        menutv.setText("Side:2");
                        menuicon1.setImageResource(R.drawable.pizzahutside2);
                    }else    if (result.contains("side3") || result.contains("se3")|| result.contains("say 3") || result.contains("s 3") || result.contains("side 3") || result.contains("side three") || result.contains("Side 3") || result.contains("SIDE 3")||result.contains("sidethree")||result.contains("side 3 three")||result.contains("SIDE THREE")) {
                        speech("Side three.");
                        menutv.setText("Side:3");
                        menuicon1.setImageResource(R.drawable.pizzahutside3);
                    } else    if (result.contains("desert") || result.contains("DESERT") || result.contains("desserts") || result.contains("dessert") || result.contains("deserve")||result.contains("deserts")||result.contains("Dessert")||result.contains("Desserts")) {
                        speech("Desserts.");
                        menutv.setText("Desserts");
                        menuicon1.setImageResource(R.drawable.dessert);
                    }else if (result.contains("1") || result.contains("one") || result.contains("One") || result.contains("ONE"))
                    {
                        speech("Meaty Eid Feast.");
                        menutv.setText("Meaty Eid Feast");
                        menuicon1.setImageResource(R.drawable.meatyeidfeastoffer);
                    }
                    else
                    {
                        String itemss = "No Item found. If you want to listen our menu items click on menu item button placed at center bottom side. Thank you";
                        speech(itemss);
                    }

                }
                break;
            }
            case 004:
            {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.contains("Call") || result.contains("call") || result.contains("CALL")) {
                      //  String contactnumber = getnumber();
                        String branchtvv = branchtv.getText().toString();
                        if (branchtvv.contains("I-8 Markaz Islamabad"))
                        {
                            String contactnumber = "tel:03315040027";
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(contactnumber));
                            startActivity(intent);
                            return;

                        }else  if (branchtvv.contains("Bahria Town Rawalpindi"))
                        {
                            String contactnumber = "tel:03315040027";
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(contactnumber));
                            startActivity(intent);
                            return;

                        }
                        else {

                            speech("number not found to make a call");

                         }
                    }
                }
                break;
            }
        }
    }

   /* private String getnumber() {

        String menutvv = menutv.getText().toString();
        if (menutvv.contains("I-8 Markaz Islamabad"))
        {
            String connumber = "tel:03315040027";
            return connumber;
        }else
            return "number not found to make a call";
    }*/


    private void promptSpeechInputcity() {
       // SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly Select City for your Order.");
        try {
            startActivityForResult(speachintent, 001);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    public void playcity(){

        speech("Kindly Select the city for your order.");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInputcity();
            }

        } while (speakingEnd);

    }

    private void promptSpeechInputbranch() {

       // SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly Select Branch Number to Select the Branch.");
        try {
            startActivityForResult(speachintent, 002);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    public void playbranch(){

        speech("Kindly Select the Branch Number.");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInputbranch();
            }

        } while (speakingEnd);

    }


    private void promptSpeechInputmenu() {
      //  SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kindly Select Menu item for your Order.");
        try {
            startActivityForResult(speachintent, 003);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }
    public void playmenu(){

        speech("Kindly Select the menu item for your order.");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInputmenu();
            }

        } while (speakingEnd);

    }

    private void promptSpeechInputcall() {
        String branchtvv = branchtv.getText().toString();
        //  SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speachintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speachintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speachintent.putExtra(RecognizerIntent.EXTRA_PROMPT, " now say the word call if you want to make a call to " + branchtvv + " branch. ");
        try {
            startActivityForResult(speachintent, 004);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }

    public void playcall(){

        String branchtvv = branchtv.getText().toString();
        speech(" now say the word call if you want to make a call to " + branchtvv + " branch. ");

        boolean speakingEnd = textToSpeech.isSpeaking();
        do{
            speakingEnd = textToSpeech.isSpeaking();

            if(!speakingEnd){
                promptSpeechInputcall();
            }

        } while (speakingEnd);

    }

  public  void playpizzamenu()
    {
        visiabletag1.setVisibility(View.GONE);
        visiabletag2.setVisibility(View.GONE);
        visiablehorscrview2.setVisibility(View.GONE);
        visiablehorscrview1.setVisibility(View.GONE);
        visiablemenu.setImageResource(R.drawable.pizzahutpizzas);
        stop_btn.setVisibility(View.GONE);
        menu_items.setVisibility(View.GONE);
        special_deals.setVisibility(View.GONE);
        visiablemenu.setVisibility(View.VISIBLE);
        repeat_btn.setVisibility(View.VISIBLE);
        String items = "Currently we have different flavours in our pizzas menu. we have got "+ pizzaflavour1 + ", " + pizzaflavour2 + ", " + pizzaflavour3 + ", " + pizzaflavour4 + ", " + pizzaflavour5 + ", " + pizzaflavour6 + ", " + pizzaflavour7 + " , and " + pizzaflavour8 + ", " +"with different prices according to size." + " Small " + small + ", " + " medium " + medium + ", " + " large " + larg + ", " + " XL " + xl + ", " + " and double XL in " + ", " + xxl+"." ;
        speech(items);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playcall();
            }
        }, 29000);
    }


    public  void playpizzaside1()
    {
        visiabletag1.setVisibility(View.GONE);
        visiabletag2.setVisibility(View.GONE);
        visiablehorscrview2.setVisibility(View.GONE);
        visiablehorscrview1.setVisibility(View.GONE);
        visiablemenu.setImageResource(R.drawable.pizzahutside1);
        stop_btn.setVisibility(View.GONE);
        menu_items.setVisibility(View.GONE);
        special_deals.setVisibility(View.GONE);
        visiablemenu.setVisibility(View.VISIBLE);
        repeat_btn.setVisibility(View.VISIBLE);
        String items = "Currently we have different items in our side one menu. we have got "+ side1item1 + " in " + priceitem1 + ", " + side1item2 + " in " + priceitem2 + ", " + side1item3 + " in " + priceitem3 + ", " + side1item4 + " in " + priceitem4 + " and " + side1item5 + " in " + priceitem5 + ".";
        speech(items);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playcall();
            }
        }, 18000);
    }
    public  void playpizzaside2()
    {
        visiabletag1.setVisibility(View.GONE);
        visiabletag2.setVisibility(View.GONE);
        visiablehorscrview2.setVisibility(View.GONE);
        visiablehorscrview1.setVisibility(View.GONE);
        visiablemenu.setImageResource(R.drawable.pizzahutside2);
        stop_btn.setVisibility(View.GONE);
        menu_items.setVisibility(View.GONE);
        special_deals.setVisibility(View.GONE);
        visiablemenu.setVisibility(View.VISIBLE);
        repeat_btn.setVisibility(View.VISIBLE);
        String items = "Currently we have different items in our side two menu. we have got "+ side2item1 + " in " + s2priceitem1 + ", " + side2item2 + " in " + s2priceitem2 + ", " + side2item3 + " in " + s2priceitem3 + " and " + side2item4 + " in " + s2priceitem4 + ".";
        speech(items);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playcall();
            }
        }, 18000);
    }
    public  void playpizzaside3()
    {
        visiabletag1.setVisibility(View.GONE);
        visiabletag2.setVisibility(View.GONE);
        visiablehorscrview2.setVisibility(View.GONE);
        visiablehorscrview1.setVisibility(View.GONE);
        visiablemenu.setImageResource(R.drawable.pizzahutside3);
        stop_btn.setVisibility(View.GONE);
        menu_items.setVisibility(View.GONE);
        special_deals.setVisibility(View.GONE);
        visiablemenu.setVisibility(View.VISIBLE);
        repeat_btn.setVisibility(View.VISIBLE);
        String items = "Currently we have different items in our side three menu. we have got "+ side3item1 + " in " + s3priceitem1 + ", " + side3item2 + " in " + s3priceitem2 + " and " + side3item3 + " mini size in " + s3priceitem3mini + " and regular size in " + s3priceitem3regular +".";
        speech(items);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playcall();
            }
        }, 18000);
    }
    public  void playpizzadessert()
    {
        visiabletag1.setVisibility(View.GONE);
        visiabletag2.setVisibility(View.GONE);
        visiablehorscrview2.setVisibility(View.GONE);
        visiablehorscrview1.setVisibility(View.GONE);
        visiablemenu.setImageResource(R.drawable.dessert);
        stop_btn.setVisibility(View.GONE);
        menu_items.setVisibility(View.GONE);
        special_deals.setVisibility(View.GONE);
        visiablemenu.setVisibility(View.VISIBLE);
        repeat_btn.setVisibility(View.VISIBLE);
        String items = "Currently we have different items in our dessert menu. we have got "+ dessertitem1 + " in " + dessertpriceitem1 + " and " + dessertitem2 + " in " + dessertpriceitem2 +".";
        speech(items);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playcall();
            }
        }, 10000);
    }
    public void playpizzaeidfeast()
    {
        visiabletag1.setVisibility(View.GONE);
        visiabletag2.setVisibility(View.GONE);
        visiablehorscrview2.setVisibility(View.GONE);
        visiablehorscrview1.setVisibility(View.GONE);
        visiablemenu.setImageResource(R.drawable.meatyeidfeastoffer);
        stop_btn.setVisibility(View.GONE);
        menu_items.setVisibility(View.GONE);
        special_deals.setVisibility(View.GONE);
        visiablemenu.setVisibility(View.VISIBLE);
        repeat_btn.setVisibility(View.VISIBLE);
        String items = "Currently we have different items in our special deal meaty eid feast menu. we have got "+ meatyeidfeastitem1 + " in " + meatyeidfeastpriceitem1 + " , " + meatyeidfeastitem2 + " in " + meatyeidfeastpriceitem2 + " and " + meatyeidfeastitem3 + " in " + meatyeidfeastpriceitem3 +".";
        speech(items);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playcall();
            }
        }, 20000);
    }
    public void refresh()
    {
        visiabletag1.setVisibility(View.VISIBLE);
        visiabletag2.setVisibility(View.VISIBLE);
        visiablehorscrview2.setVisibility(View.VISIBLE);
        visiablehorscrview1.setVisibility(View.VISIBLE);
        visiablemenu.setImageResource(R.drawable.rs3);
        stop_btn.setVisibility(View.VISIBLE);
        menu_items.setVisibility(View.VISIBLE);
        special_deals.setVisibility(View.VISIBLE);
        visiablemenu.setVisibility(View.GONE);
        repeat_btn.setVisibility(View.GONE);
        menutv.setText("Choose Menu");
        menuicon1.setImageResource(R.drawable.rs3);
    }
    private void toastMessage(String message)
    {
        Toast.makeText(pizza_hut2.this,message, LENGTH_LONG).show();
    }
    private void speech(String message)
    {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
}