package com.example.minorpro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class group extends AppCompatActivity {

    ListView listView;
    String name[];// Array for names
    String contact[];//Array for contact numbers
    TextView nametxt,contacttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        nametxt = findViewById(R.id.listtxt1);
        contacttxt = findViewById(R.id.listtxt2);
        listView =findViewById(R.id.list1);

        MyAdapter adapter = new MyAdapter(this, name, contact);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //loop for group selection
                //use "position' variable ef: if(position == 0){}
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String mname[];
        String mcontact[];

        MyAdapter(Context c, String name[], String contact[]){
            super(c, R.layout.row, R.id.listtxt1, name);
            this.context = c;
            this.mname = name;
            this.mcontact = contact;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView name = row.findViewById(R.id.listtxt1);
            TextView contact = row.findViewById(R.id.listtxt2);

            name.setText(mname[position]);
            contact.setText(mcontact[position]);

            return super.getView(position, convertView, parent);
        }
    }
}
