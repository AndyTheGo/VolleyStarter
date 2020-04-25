package com.example.volleystarter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class FavoritesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        String defaultUrl = "https://via.placeholder.com/150";
        String favorites = sharedPreferences.getString("favorites", defaultUrl);

        ListView favoritesListView = findViewById(R.id.favs_list_view);
        String[] favArray = favorites.split(",");
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(this, favArray);
        favoritesListView.setAdapter(favoritesAdapter);



    }

}
