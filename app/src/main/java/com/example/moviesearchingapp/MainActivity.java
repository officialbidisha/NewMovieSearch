package com.example.moviesearchingapp;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviesearchingapp.RecyclerVewAdapter;
import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView,recyclerView1;
    private RecyclerView.Adapter adapter,adapter1;
    private RecyclerView.LayoutManager layoutManager,layoutManager1;
    private ArrayList<String> ar= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.search);
        recyclerView=findViewById(R.id.recycler);
        recyclerView1=findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this, HORIZONTAL,false);
        layoutManager1=new LinearLayoutManager(this, HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        for(int i=0;i<10;i++)
            ar.add(i+"");
        recyclerView1.setLayoutManager(layoutManager1);
        adapter= new RecyclerVewAdapter(ar,this);
        adapter1= new RecyclerVewAdapter(ar,this);
        recyclerView.setAdapter(adapter);
        recyclerView1.setAdapter(adapter1);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView1.setNestedScrollingEnabled(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("Query", s);
                return false;
            }
        });
    }
}
