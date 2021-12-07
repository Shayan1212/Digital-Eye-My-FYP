package com.example.digitaleye;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class Twocol_list_adapter extends ArrayAdapter<User> {
    private static final String TAG = "Twocol_list_adapter";
    LayoutInflater inflater;
    private ArrayList<User> users;
    private Context mContext;
    int mResource;

    public Twocol_list_adapter(@NonNull Context context, int resource, @NonNull ArrayList<User> users) {
        super(context, resource, users);
        mContext=context;
        mResource = resource;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        String currency_worth = getItem(position).getCurrency_worth();
        String timestamp = getItem(position).getTimestamp();
        User user = new User(currency_worth, timestamp);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        if(user != null) {
            TextView tvcurr = (TextView) convertView.findViewById(R.id.currency_worth);
            TextView tvtime = (TextView) convertView.findViewById(R.id.timestamp1);
            if (tvcurr != null)
            {
                tvcurr.setText(currency_worth);
            }
            if (tvtime != null)
            {
                tvtime.setText(timestamp);
            }
        }
        return convertView;
    }

   /* public Twocol_list_adapter(@NonNull Context context, int resource, @NonNull ArrayList<User> users) {
        super(context, resource, users);
        this.users = users;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String currency_worth = getItem(position).getCurrency_worth();
        String timestamp = getItem(position).getTimestamp();
        User user = new User(currency_worth, timestamp);
        convertView = inflater.inflate(mResource,parent,false);
       // User user = users.get(position);
        if(user != null) {
            TextView tvcurr = convertView.findViewById(R.id.currency_worth);
            TextView tvtime = convertView.findViewById(R.id.time_stamp);
            if (tvcurr != null)
            {
                tvcurr.setText(user.getCurrency_worth());
            }
            if (tvtime != null)
            {
                tvtime.setText(user.getTimestamp());
            }
        }
        return convertView;
    }*/
}
