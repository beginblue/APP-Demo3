package exercises.blue.demoagain.CollectionActivity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import exercises.blue.demoagain.R;

public class Collection extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ActionBar bar1 = getSupportActionBar();
        if(null!=bar1) bar1.hide();
        else Toast.makeText(Collection.this, "??@#!@#$#!", Toast.LENGTH_SHORT).show();
        Toolbar bar = (Toolbar) findViewById(R.id.fav_toolbar);
        assert bar != null;
        bar.setTitle("My Collections");
        setSupportActionBar(bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.fav_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new collectionRecyclerAdapter(getApplicationContext()));


    }
}
