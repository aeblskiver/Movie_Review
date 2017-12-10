package edu.fullerton.justin.mymoviereviews.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import edu.fullerton.justin.mymoviereviews.MovieApplication;
import edu.fullerton.justin.mymoviereviews.R;
import edu.fullerton.justin.mymoviereviews.model.Movie;
import edu.fullerton.justin.mymoviereviews.util.MovieDateComparator;
import edu.fullerton.justin.mymoviereviews.util.MovieRatingComparator;
import edu.fullerton.justin.mymoviereviews.util.MoviesRecyclerAdapter;
import edu.fullerton.justin.mymoviereviews.viewmodel.RecentViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedListFragment extends Fragment {
    private static final String TAG = "TopRatedListFragment";

    private List<Movie> movies;

    private LayoutInflater layoutInflater;
    private MoviesRecyclerAdapter recyclerAdapter;
    public RecyclerView recyclerView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private RecentViewModel recentViewModel;
    private List<Movie> listData;

    public TopRatedListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Dependency injection
        ((MovieApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_rated_list, container, false);
        recyclerView = v.findViewById(R.id.topRatedRecyclerView);
        layoutInflater = getActivity().getLayoutInflater();
        recentViewModel = ViewModelProviders.of(this, viewModelFactory).get(RecentViewModel.class);
        recentViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (TopRatedListFragment.this.movies == null) {
                    setListData(movies);
                } else {
                    setListData(movies);
                }
            }
        });
        return v;
    }

    public void setListData(List<Movie> listOfMovies) {
        //Set up Recycler view
        movies = listOfMovies;
        Collections.sort(movies, new MovieRatingComparator());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MoviesRecyclerAdapter recyclerAdapter = new MoviesRecyclerAdapter();
        recyclerAdapter.setMovies(movies);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
