package exercises.blue.demoagain.BeautyGallery;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.Retrofit.retrofitBody;
import exercises.blue.demoagain.friendsFragment.fDividerItemDecoration;
import exercises.blue.demoagain.userdata.beautyData;
import exercises.blue.demoagain.userdata.friendsDataSet;
import rx.Subscriber;

public class BeautyCards extends AppCompatActivity {


    private static final String TAG = "bluelog";
    // friendsRecyclerAdapter adapter;

    friendsDataSet mDataSet = friendsDataSet.newInstance();
    // fragmentFriends master;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RequestQueue mRequestQueue;
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

        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new retrofitBody().beautyRequest(mCategory,
                                10,
                                mBeautyAdapter.getItemCount() / 10 + 1,
                                new Subscriber<beautyData>() {
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
                                    public void onNext(beautyData data) {
                                        mBeautyAdapter.addAll(data.getResults());
                                    }
                                });
                    }
                });

//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mRequestQueue = Volley.newRequestQueue(BeautyCards.this);
//
//                final int page = (mDataSet.getDataCount(mCategory) / 10) + 1;//脑残X2(╯‵□′)╯︵┻━┻
//                Log.e(TAG, "onRefresh: page number " + page + "--adapter" + mDataSet.getDataCount(mCategory));
//                /**
//                 * Creates a new request.
//                 * @param method the HTTP method to use
//                 * @param url URL to fetch the JSON from
//                 * @param listener Listener to receive the JSON response
//                 * @param errorListener Error listener, or null to ignore errors.
//                 */
//                JsonObjectRequest bbb = new JsonObjectRequest(Request.Method.GET,
//                        "http://gank.io/api/data/" + mCategory + "/10/" + (page),
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    Log.i(TAG, "onResponse: current page " + page);
//                                    ArrayList<beautyData.ResultsBean> fList = new ArrayList<>();
//                                    Log.i(TAG, "onResponse: ???");
//                                    //fList.add(0,new friendsDatum("--------------","222222"));
//                                    JSONArray jsonArray = response.getJSONArray("results");
//                                    for (int count = 0; count < jsonArray.length(); count++) {
//                                        JSONObject jsonObject = jsonArray.getJSONObject(count);
//                                        beautyData.ResultsBean bean = new beautyData.ResultsBean();
//                                        bean.setDesc(jsonObject.getString("desc"));
//                                        bean.setUrl(jsonObject.getString("url"));
//                                        Log.i(TAG, "onResponse:"+jsonObject.getString("desc"));
//                                        fList.add(bean);
//                                    }
//                                    //脑残!(╯‵□′)╯︵┻━┻
//                                    mBeautyAdapter.addAll(fList);
//                                    mSwipeRefreshLayout.setRefreshing(false);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        mSwipeRefreshLayout.setRefreshing(false);
//                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
//                    }
//                });
//                mRequestQueue.add(bbb);
//
//            }
//        });


    }
}
