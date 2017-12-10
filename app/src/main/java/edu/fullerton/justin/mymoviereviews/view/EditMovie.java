package edu.fullerton.justin.mymoviereviews.view;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.fullerton.justin.mymoviereviews.MovieApplication;
import edu.fullerton.justin.mymoviereviews.R;
import edu.fullerton.justin.mymoviereviews.model.Movie;
import edu.fullerton.justin.mymoviereviews.viewmodel.EditViewModel;

public class EditMovie extends AppCompatActivity {
    public static final String TAG = "Edit Activity";
    @BindView(R.id.movieNameInputLayout)
    TextInputLayout mNameInputLayout;
    @BindView(R.id.movieDateInputLayout)
    TextInputLayout mDateInputLayout;
    @BindView(R.id.movie_date)
    EditText mMovieDate;
    @BindView(R.id.movie_name)
    EditText mMovieName;
    @BindView(R.id.movie_rating)
    RatingBar mMovieRating;
    Movie movie;
    boolean editing = false;

    GregorianCalendar mCalendar = new GregorianCalendar();
    Date mDate = new Date();

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private EditViewModel editViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        //Dependency injectin and Butterknife binding
        ((MovieApplication) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        setToolbar();

        editViewModel = ViewModelProviders.of(this, viewModelFactory).get(EditViewModel.class);
        Intent intent = getIntent();
        updateDateField();
        if (intent.hasExtra("MOVIE_NAME")) handleIntent(intent);

    }

    private void handleIntent(Intent intent) {
        editing = true;
        String movieName = intent.getStringExtra("MOVIE_NAME");
        editViewModel.getMovie(movieName).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                if (EditMovie.this.movie == null) {
                    EditMovie.this.movie = movie;
                    updateAllFields();
                }
            }
        });
    }

    private void updateAllFields() {
        mMovieName.setText(movie.getMovieName());
        mDate.setTime(movie.getDate().getTime());
        updateDateField();
        mMovieRating.setRating(movie.getRating());
    }

    private void setToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_edit_save):
                saveMovie();
                return true;
            case (R.id.menu_edit_delete):
                Toast.makeText(this,"Clicky", Toast.LENGTH_SHORT).show();
                if (editing) deleteMovie();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveMovie() {
        if (!isNameSet()) {
            mNameInputLayout.setError("You must enter a name!");
        } else {
            mNameInputLayout.setErrorEnabled(false);
            viewModelSave();
            finish();
        }

    }

    private void deleteMovie() {
        Toast.makeText(this,"I made it!", Toast.LENGTH_SHORT).show();
        editViewModel.deleteMovie(movie);
    }

    private void viewModelSave() {
        editViewModel.saveMovie(new Movie(
                mMovieName.getText().toString(),
                new Date(mCalendar.getTimeInMillis()),
                mMovieRating.getRating())
        );
    }

    private boolean isNameSet() {
        return isValidMovieName(mNameInputLayout.getEditText().getText().toString());
    }


    private boolean isValidMovieName(String name) {
        return name.length() > 0;
    }

    @OnClick(R.id.movie_date)
    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = getOnDateSetListener();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener getOnDateSetListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mCalendar.set(Calendar.YEAR, i);
                mCalendar.set(Calendar.MONTH, i1);
                mCalendar.set(Calendar.DAY_OF_MONTH, i2);
                mDate = mCalendar.getTime();
                updateDateField();
            }
        };
    }

    private void updateDateField() {
        mMovieDate.setText(getPrettyDateFormat());
    }


    @NonNull
    private String getPrettyDateFormat() {
        final SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, YYYY");
        return sdf.format(mDate);
    }
}