package exercises.blue.demoagain.BeautyGallery;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.Retrofit.retrofitBody;
import exercises.blue.demoagain.friendsFragment.fDividerItemDecoration;
import exercises.blue.demoagain.userdata.responseBean;
import rx.Subscriber;

public class BeautyCards extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "bluelog";
    // friendsRecyclerAdapter adapter;
    // fragmentFriends master;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String mCategory = "福利";
    beautyAdapter mBeautyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_cards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setTitle("Beauties");
        setSupportActionBar(toolbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.bRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.bRecycler);
        if (null != mRecyclerView) {
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mBeautyAdapter = new beautyAdapter();
            mRecyclerView.setAdapter(mBeautyAdapter);
            mRecyclerView.addItemDecoration(new fDividerItemDecoration(5));
        }

        mSwipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();

    }

    @Override
    public void onRefresh() {
        new retrofitBody().beautyRequest(mCategory,
                10,
                mBeautyAdapter.getItemCount() / 10 + 1,
                new Subscriber<responseBean>() {
                    @Override
                    public void onCompleted() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println(e.getCause());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(responseBean data) {

                        mBeautyAdapter.addAll(data.getResults());
                    }
                });
    }
}
