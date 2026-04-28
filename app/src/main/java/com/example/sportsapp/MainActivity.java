package com.example.sportsapp;

import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private CustomAdapter myAdapter;
    private List<Sport> sportList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        sportList = new ArrayList<>();

        Sport s1 = new Sport("Football", R.drawable.football);
        Sport s2 = new Sport("Basketball", R.drawable.basketball);
        Sport s3 = new Sport("VolleyBall", R.drawable.volley);
        Sport s4 = new Sport("Tennis", R.drawable.tennis);
        Sport s5 = new Sport("Ping Pong", R.drawable.ping);

        sportList.add(s1);
        sportList.add(s2);
        sportList.add(s3);
        sportList.add(s4);
        sportList.add(s5);

        myAdapter = new CustomAdapter(sportList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        myAdapter.setClickListener(this);

        }
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You chose: " + sportList.get(position).getSportName(), Toast.LENGTH_SHORT).show();
    }
}