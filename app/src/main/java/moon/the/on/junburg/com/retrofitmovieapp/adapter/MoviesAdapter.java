package moon.the.on.junburg.com.retrofitmovieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import moon.the.on.junburg.com.retrofitmovieapp.DetailActivity;
import moon.the.on.junburg.com.retrofitmovieapp.R;
import moon.the.on.junburg.com.retrofitmovieapp.model.Movie;

/**
 * Created by Junburg on 2018. 5. 6..
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movieList;

    public MoviesAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;

    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_card, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.title.setText(movieList.get(position).getOriginalTitle());
        String vote = Double.toString(movieList.get(position).getVoteAverage());
        viewHolder.userRating.setText(vote);

        Glide.with(mContext)
                .load(movieList.get(position).getPosterPath())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, userRating;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            userRating = (TextView)view.findViewById(R.id.user_rating);
            thumbnail = (ImageView)view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Movie clickedDataItem = movieList.get(position);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("original_title", movieList.get(position).getOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(position).getPosterPath());
                        intent.putExtra("overview", movieList.get(position).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(position).getVoteAverage()));
                        intent.putExtra("release_date", movieList.get(position).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "You clicked" + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
