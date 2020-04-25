package com.example.volleystarter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class FavoritesAdapter extends ArrayAdapter<String> {
    public FavoritesAdapter(Context context, String[] favoritesArray) {
        super(context, 0, favoritesArray);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
        String currentMoviePosterUrl = getItem(position);
        ImageView moviePosterImageView = convertView.findViewById(R.id.fav_movie_iv);
        Picasso.get().load(currentMoviePosterUrl).into(moviePosterImageView);
        return convertView;
    }

}
