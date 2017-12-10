package edu.fullerton.justin.mymoviereviews.util;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.fullerton.justin.mymoviereviews.MainActivity;
import edu.fullerton.justin.mymoviereviews.R;
import edu.fullerton.justin.mymoviereviews.model.Movie;
import edu.fullerton.justin.mymoviereviews.view.EditMovie;

/**
 * Created by justin on 12/7/17.
 */

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MoviesRecyclerAdapter() {
    }

    public MoviesRecyclerAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movielist_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        holder.movieName.setText(currentMovie.getMovieName());
        holder.movieDate.setText(getPrettyDate(currentMovie.getDate()));//currentMovie.getDate().toString());
        holder.movieRating.setRating(currentMovie.getRating());
    }

    private String getPrettyDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, YYYY");
        return sdf.format(date);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView movieName;
        TextView movieDate;
        RatingBar movieRating;
        ViewGroup container;

        public MovieViewHolder(View movieView) {
            super(movieView);
            this.movieName = (TextView) movieView.findViewById(R.id.movieName);
            movieDate = (TextView) movieView.findViewById(R.id.movieDate);;
            movieRating = (RatingBar) movieView.findViewById(R.id.ratingBar);;
            container = (ViewGroup) movieView.findViewById(R.id.movielist_item);

            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Movie movie = movies.get(this.getAdapterPosition());
            Context context = view.getContext();
            Intent intent = new Intent(context, EditMovie.class);
            intent.putExtra("MOVIE_NAME", movie.getMovieName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((MainActivity) context).getWindow().setEnterTransition(new Fade(Fade.IN));
                ((MainActivity) context).getWindow().setEnterTransition(new Fade(Fade.OUT));
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(((MainActivity) context),
                                new Pair<View, String>(view.findViewById(R.id.movieName), context.getString(R.string.transitionMovieName)),
                                new Pair<View, String>(view.findViewById(R.id.movieDate), context.getString(R.string.transitionMovieDate)),
                                new Pair<View, String>(view.findViewById(R.id.ratingBar), context.getString(R.string.transitionMovieRating))
                                );
                context.startActivity(intent, options.toBundle());
            } else {
                context.startActivity(intent);
            }

        }
    }
}
