package com.example.moviesearchingapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class SearchActivity extends Activity {
    private final String SEARCH_URL = "http://www.omdbapi.com/?apikey=d9448030&s=";
    private final String POSTER_URL = "http://img.omdbapi.com/?apikey=d9448030&i=";
    private final String DESC_URL = "http://www.omdbapi.com/?apikey=d9448030&i=";
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MovieShortDetails> movie= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlayout);
        searchView = findViewById(R.id.searchView);
        recyclerView=findViewById(R.id.recycler2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new SearchAdapter(movie,this);
        recyclerView.setAdapter(adapter);

        final RequestQueue queue = Volley.newRequestQueue(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, SEARCH_URL+s, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray search_results = response.getJSONArray("Search");

                                    for(int i=0;i<search_results.length();i++){
                                        JSONObject imovie = search_results.getJSONObject(i);
                                        final String title = imovie.get("Title").toString();
                                        String id = imovie.get("imdbID").toString();
                                        String plot = "";
                                        final MovieShortDetails _movie = new MovieShortDetails();
                                        _movie.setTitle(title);
                                        JsonObjectRequest descJsonRequest = new JsonObjectRequest(Request.Method.GET, DESC_URL + id, null,
                                                new Response.Listener<JSONObject>() {
                                                    @Override
                                                    public void onResponse(JSONObject response) {
                                                        try {

                                                            _movie.setDescription(response.getString("Plot"));
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                                        queue.add(descJsonRequest);
                                        ImageRequest imageRequest = new ImageRequest(POSTER_URL + id, new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap response) {
                                                _movie.setPoster(response);
                                            }
                                        }, 150, 204, ImageView.ScaleType.CENTER_CROP,
                                                Bitmap.Config.RGB_565,
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        _movie.setPoster(
                                                                BitmapFactory.decodeResource(getResources(),R.drawable.image_pic)
                                                        );

                                                    }
                                                });


                                        queue.add(imageRequest);
                                        movie.add(_movie);


                                    }
                                    //Log.d("Returned", response.get("Search").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });
                queue.add(jsonObjectRequest);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

    }
}
