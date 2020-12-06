package com.takhir.animetitlesjava;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.circularreveal.CircularRevealLinearLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.takhir.animetitlesjava.models.kitsu.Anime;
import com.takhir.animetitlesjava.models.kitsu.Genres;
import com.takhir.animetitlesjava.viewmodels.AnimeViewModel;

import java.util.List;

public class AnimeActivity extends BaseActivity {

    private static final String TAG = "AnimeActivity";

    //ui
    private ShapeableImageView mAnimeImage, mAnimeImageBackground;
    private MaterialTextView mAnimeTitle, mAnimeDescription, mAnimeRating;
    private ScrollView mScrollView;
    private CircularRevealLinearLayout mAnimeInformationContainer;
    private Button mExpandDescriptionButton;
    private RatingBar mAnimeRatingBar;

    //vars
    private AnimeViewModel mAnimeViewModel;
    private Anime mAnime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        mAnimeImage = findViewById(R.id.anime_image);
        mAnimeImageBackground = findViewById(R.id.anime_image_background);
        mAnimeTitle = findViewById(R.id.anime_title);
        mAnimeDescription = findViewById(R.id.anime_description);
        mAnimeRating = findViewById(R.id.anime_rating);
        mScrollView = findViewById(R.id.parent);
        mAnimeInformationContainer = findViewById(R.id.container);
        mExpandDescriptionButton = findViewById(R.id.anime_description_more);
        mAnimeRatingBar = findViewById(R.id.anime_rating_bar);

        mExpandDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimeDescription.setMaxLines(20);
            }
        });

        mAnimeViewModel = new ViewModelProvider(this).get(AnimeViewModel.class);

        showProgressBar(true);
        getIncomingIntent();
        subscribeObservers();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("anime")) {
            mAnime = (Anime) getIntent().getParcelableExtra("anime");
            mAnimeViewModel.searchAnimeGenresById(mAnime.getId());
            Log.d(TAG, "getIncomingIntent: " + mAnime);
        }
    }

    private void subscribeObservers() {
        mAnimeViewModel.getAnimeGenres().observe(this, new Observer<List<Genres>>() {
            @Override
            public void onChanged(List<Genres> genres) {
                if (genres != null) {
                    if (mAnimeViewModel.getAnimeId() == mAnime.getId()) {
                        Log.d(TAG, "onChanged: " + genres);
                        mAnime.setAnimeGenres(genres);
                        setAnimeProperties(mAnime);
                        mAnimeViewModel.setRetrievedGenres(true);
                    }
                }
            }
        });
        mAnimeViewModel.isGenresRequestTimedOut().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean && !mAnimeViewModel.didRetrieveGenres()) {
                    displayErrorScreen("Error retrieving data. Check network connection.");
                    Log.d(TAG, "onChanged: Request timed out.....");
                }
            }
        });
    }

    private void displayErrorScreen(String errorMessage) {
        MaterialTextView errorTextView = new MaterialTextView(this);
        errorTextView.setTextSize(50);
        if (!errorMessage.equals("")) {
            errorTextView.setText(errorMessage);
        } else {
            errorTextView.setText("Error");
        }
        mAnimeInformationContainer.addView(errorTextView);
        showParent();
        showProgressBar(false);
    }

    private void setAnimeProperties(Anime anime) {
        if (anime != null) {
            Log.e(TAG, "setAnimeProperties: ");
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(anime.getAttributes().getPosterImage().getOriginal())
                    .centerCrop()
                    .into(mAnimeImageBackground);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(anime.getAttributes().getPosterImage().getOriginal())
                    .centerCrop()
                    .into(mAnimeImage);

            mAnimeTitle.setText(anime.getAttributes().getCanonicalTitle());

            StringBuilder genres = new StringBuilder();
            if (anime.getAnimeGenres().size() > 0) {
                for (Genres genre : anime.getAnimeGenres()) {
                    genres.append(genre.getAttributes().getSlug()).append(", ");
                }
                genres = new StringBuilder(genres.substring(0, genres.length() - 2));
            }

            mAnimeInformationContainer.removeAllViews();
            String[] linearLayoutElements = new String[]{
                    "<b>Year:</b> " + anime.getAttributes().getStartDate(),
                    "<b>Studio:</b> value",
                    "<b>Producer:</b> value",
                    "<b>Author:</b> value",
                    "<b>Genre:</b> " + genres
            };
            for (String element : linearLayoutElements) {
                MaterialTextView textView = new MaterialTextView(this);
                textView.setText(Html.fromHtml(element, 0));
                mAnimeInformationContainer.addView(textView);
            }

            mAnimeDescription.setText(anime.getAttributes().getDescription());
            mExpandDescriptionButton.setText("More details..");

            mAnimeRating.setText(anime.getAttributes().getAverageRating());
            mAnimeRatingBar.setRating(Float.parseFloat(anime.getAttributes().getAverageRating()) / 20);

            showProgressBar(false);
            showParent();
        }
    }

    private void showParent() {
        mScrollView.setVisibility(View.VISIBLE);
    }
}
