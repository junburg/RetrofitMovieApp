package moon.the.on.junburg.com.retrofitmovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by Junburg on 2018. 5. 6..
 */

public class DetailActivity extends AppCompatActivity{
    TextView movieNameTxt, plotSynopsisTxt, userRatingTxt, releaseDateTxt;
    ImageView thumnailImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        thumnailImg = (ImageView)findViewById(R.id.thumbnail_image_header);
        movieNameTxt = (TextView)findViewById(R.id.title);
        plotSynopsisTxt = (TextView) findViewById(R.id.plot_synopsis);
        userRatingTxt = (TextView)findViewById(R.id.user_rating);
        releaseDateTxt = (TextView)findViewById(R.id.release_date);

        Intent fromMainActivityIntent = getIntent();
        if(fromMainActivityIntent.hasExtra("original_title")) {
            String thumnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String releaseDate = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumnail)
                    .placeholder(R.drawable.load)
                    .into(thumnailImg);

            movieNameTxt.setText(movieName);
            plotSynopsisTxt.setText(synopsis);
            userRatingTxt.setText(rating);
            releaseDateTxt.setText(releaseDate);

        } else {
            Toast.makeText(this, "NO API Data", Toast.LENGTH_SHORT).show();
        }

    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
