package com.example.volleystarter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView plotTextView;
    EditText searchBar;
    TextView yearTextView;
    TextView nameTextView;
    TextView runtimeTextView;
    TextView genreTextView;
    ImageView movieposterimageview;
    String nameText;
    String plotText;
    String yearText;
    String runtimeText;
    String movieposterurl;
    String url;
    String genreText;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plotTextView = findViewById(R.id.plot_tv);
        searchBar = findViewById(R.id.searchbar);
        searchButton = findViewById(R.id.search_button);
        yearTextView = findViewById(R.id.year_tv);
        nameTextView = findViewById(R.id.name_tv);
        runtimeTextView = findViewById(R.id.runtime_tv);
        movieposterimageview = findViewById(R.id.movieposter_iv);
        genreTextView = findViewById(R.id.genre);

    }

    public void fetchData(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        url = "http://www.omdbapi.com/?apikey=72f59ebd&t=" + searchBar.getText().toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //plotTextView.setText("Response is: " + response);
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            nameText = responseObject.getString("Title");
                            nameTextView.setText(nameText);
                            runtimeText = responseObject.getString("Runtime");
                            runtimeTextView.setText("Runtime: " + runtimeText);
                            plotText = responseObject.getString("Plot");
                            plotTextView.setText(plotText);
                            yearText = responseObject.getString("Year");
                            yearTextView.setText("Year: " + yearText);
                            movieposterurl = responseObject.getString("Poster");
                            Picasso.get().load(movieposterurl).into(movieposterimageview);
                            genreText = responseObject.getString("Genre");
                            genreTextView.setText("Genre: " + genreText);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                plotTextView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void addToFavorites(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String favorites = sharedPreferences.getString("favorites", "");

        if (favorites.length() > 0){
            editor.putString("favorites", favorites + "," + movieposterurl);
        }else {
            editor.putString("favorites", movieposterurl);
        }
        editor.apply();

    }

    public void openFavorites(View view){
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }

}
