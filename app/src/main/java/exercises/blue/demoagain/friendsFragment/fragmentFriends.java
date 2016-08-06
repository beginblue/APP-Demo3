package exercises.blue.demoagain.friendsFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.Retrofit.retrofitBody;
import exercises.blue.demoagain.interfaces.myOnItemClickListener;
import exercises.blue.demoagain.interfaces.myOnItemLongClickListener;
import exercises.blue.demoagain.userdata.responseBean;
import rx.Subscriber;

/**
 * so many problems - -
 * i have to fix them one by one
 * <p/>
 * Created by getbl on 2016/4/18.
 */
public class fragmentFriends extends Fragment
        implements myOnItemClickListener, myOnItemLongClickListener {


    private static final String TAG = "bluelog";
    friendsRecyclerAdapter adapter;
    // fragmentFriends master;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RequestQueue mRequestQueue;
    myOnItemClickListener mItemClickListener;
    myOnItemLongClickListener mItemLongClickListener;
    String mCategory;

//    myOnItemClickListener mItemClickListener = new myOnItemClickListener() {
//        @Override
//        public void onItemClick(View v, int position) {
//            String url = adapter.getItemUrl(position);  //"http://cn.bing.com"; // web address
//
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//
//            intent.setData(Uri.parse(url));
//
//            startActivity(intent);
//        }
//    };
//
//    myOnItemLongClickListener mItemLongClickListener = new myOnItemLongClickListener() {
//        @Override
//        public void onItemLongClick(View v, int position) {
//            Toast.makeText(getContext(), "long clicked", Toast.LENGTH_SHORT).show();
//        }
//    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public friendsRecyclerAdapter getAdapter() {
        return adapter;
    }

    public static fragmentFriends newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("category", category);
        fragmentFriends fragment = new fragmentFriends();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        mCategory = args.getString("category");
    }

//    public void setClickListeners(@Nullable myOnItemClickListener clickListener, @Nullable myOnItemLongClickListener longClickListener) {
//        mItemClickListener = clickListener;
//        mItemLongClickListener = longClickListener;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View mView = inflater.inflate(R.layout.friends, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.list);

        adapter = new friendsRecyclerAdapter(mCategory, this, this);
        mRecyclerView.setAdapter(adapter);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutManager.generateLayoutParams(params);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(new fDividerItemDecoration(5));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new retrofitBody().beautyRequest(mCategory,
                                10,
                                adapter.getItemCount() / 10 + 1,
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
                                        adapter.addAll(mCategory,data.getResults());
                                    }
                                });
                    }
                });
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mRequestQueue = Volley.newRequestQueue(mView.getContext());
//
//                final int page = (adapter.getItemCount() / 10) + 1;
//                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
//                        "http://gank.io/api/data/" + mCategory + "/10/" + (page),
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    Log.i(TAG, "onResponse: current page " + page);
//                                    ArrayList<friendsDatum> fList = new ArrayList<friendsDatum>();
//
//                                    JSONArray jsonArray = response.getJSONArray("results");
//                                    for (int count = 0; count < jsonArray.length(); count++) {
//                                        JSONObject jsonObject = jsonArray.getJSONObject(count);
//                                        Log.i(TAG, "onResponse:" + jsonObject.getString("desc") + ":"
//                                                + jsonObject.getString("url"));
//                                        fList.add(new friendsDatum(
//                                                jsonObject.getString("desc"),
//                                                jsonObject.getString("url"),
//                                                jsonObject.getString("who")));
//                                    }
//                                    adapter.addList(mCategory, fList);
//                                } catch (JSONException e) {
//
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
//                    }
//                });
//                mRequestQueue.add(request);
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });
        return mView;
    }


    @Override
    public void onItemLongClick(View v, int position) {
        String title = getAdapter().getItemTitle(position);
        String url = getAdapter().getItemUrl(position);
        if (setPref(title, url)) {

            Snackbar.make(mSwipeRefreshLayout, "收藏好了哟 \\(≧▽≦)/", Snackbar.LENGTH_LONG)
                    .setAction("哦", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
        } else Snackbar.make(mSwipeRefreshLayout, "已经存在了哟", Snackbar.LENGTH_LONG)
                .setAction("哦", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }

    private boolean setPref(String title, String url) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getContext().getString(R.string.sharedPreferenceName), Context.MODE_PRIVATE);
        if (sharedPreferences.contains(title)) {
            Log.e(TAG, "setPref: true");
            return false;
        } else {
            Log.e(TAG, "setPref: false");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(title, url);
            editor.commit();
            return true;
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        String url = adapter.getItemUrl(position);  //"http://cn.bing.com"; // web address

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse(url));

        startActivity(intent);
    }

}
