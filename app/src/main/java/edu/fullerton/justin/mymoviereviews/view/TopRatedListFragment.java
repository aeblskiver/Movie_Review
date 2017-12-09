package edu.fullerton.justin.mymoviereviews.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.fullerton.justin.mymoviereviews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedListFragment extends Fragment {


    public TopRatedListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_list, container, false);
    }

}
