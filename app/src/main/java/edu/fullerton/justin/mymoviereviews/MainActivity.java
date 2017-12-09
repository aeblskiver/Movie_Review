package edu.fullerton.justin.mymoviereviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fullerton.justin.mymoviereviews.view.EditMovie;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Main Activity";
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpTab();
    }

    private void setUpTab() {
        tabLayout = new TabLayout(this);
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_new_movie) {
            startIntentForEditMovie();
            return true;
        }
            return true;
    }

    private void startIntentForEditMovie() {
        Intent intent = new Intent(this, EditMovie.class);
        startActivity(intent);
    }


}
