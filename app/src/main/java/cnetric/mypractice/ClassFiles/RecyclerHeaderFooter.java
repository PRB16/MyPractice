package cnetric.mypractice.ClassFiles;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cnetric.mypractice.Adapter.RecyclerAdapter;
import cnetric.mypractice.PojoClass.Movies;
import cnetric.mypractice.R;

public class RecyclerHeaderFooter extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Movies> movieList = new ArrayList<>();
    private RecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_header_footer);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mAdapter = new RecyclerAdapter(this,movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }
    private void prepareMovieData() {
        Movies movie = new Movies("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movies("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movies("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movies("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movies("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movies("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movies("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movies("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movies("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movies("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Movies("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Movies("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Movies("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Movies("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Movies("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Movies("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}
