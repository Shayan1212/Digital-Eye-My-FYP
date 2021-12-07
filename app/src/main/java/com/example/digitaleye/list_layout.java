package com.example.digitaleye;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class list_layout extends AppCompatActivity {

    private static final String TAG = "list_layout_activity";
    DatabaseHelper mDatabaseHelper;
    TextToSpeech textToSpeech;
    ArrayList<User> userlist;
    User user;
    ListView listView;
    Button delet_btn;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout2);
        listView = findViewById(R.id.listView);
        delet_btn = findViewById(R.id.clear);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (i == TextToSpeech.SUCCESS) {
                            int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                        }if (status == textToSpeech.LANG_MISSING_DATA || status == textToSpeech.LANG_NOT_SUPPORTED) {
                            speech("Language not supported.");
                        }
                    }
                });
        Populatelistview();
    }

    private void Populatelistview() {
        Log.d(TAG, "Populatelistview: Displaying Data in the ListView");
        mDatabaseHelper = new DatabaseHelper(this);
        userlist = new ArrayList<>();
        Cursor data = mDatabaseHelper.getData();
        int numofrows = data.getCount();
        if (numofrows == 0)
        {

            textToSpeech.speak("There is nothing in database!", TextToSpeech.QUEUE_FLUSH, null);
             toastMessage("There is nothing in database!");
        }
        else {
         while (data.moveToNext())
         {
             user = new User(data.getString(1),data.getString(2)) ;
             userlist.add(user);
         }

         Twocol_list_adapter adapter = new   Twocol_list_adapter(this, R.layout.list_adapter_layout, userlist);
         listView.setAdapter(adapter);

        }
        delet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleardata();
            }
        });
    }
    private void cleardata()
    {
        boolean clear = mDatabaseHelper.cleardata();
        if (clear == true)
        {
            toastMessage("data successfully deleted.");
            speech("data successfully deleted.");
            listView.setVisibility(View.GONE);
        }else
        {
            toastMessage("Something went wrong while deleting the data.");
            speech("Something went wrong while deleting the data.");
        }
    }
    private void toastMessage(String message)
     {
         Toast.makeText(list_layout.this,message, LENGTH_LONG).show();
     }
     private void speech(String message)
     {
       textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);

     }
}
