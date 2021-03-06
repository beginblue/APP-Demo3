package exercises.blue.demoagain.friendsFragment;

import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import exercises.blue.demoagain.MainActvity.MainActivity;
import exercises.blue.demoagain.R;
import exercises.blue.demoagain.interfaces.myOnItemClickListener;
import exercises.blue.demoagain.interfaces.myOnItemLongClickListener;
import exercises.blue.demoagain.userdata.friendsDatum;

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
//            String url = adapter.getItemString(position);  //"http://cn.bing.com"; // web address
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
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRequestQueue = Volley.newRequestQueue(mView.getContext());

                final int page = (adapter.getItemCount() / 10) + 1;//脑残X2(╯‵□′)╯︵┻━┻
                Log.e(TAG, "onRefresh: page number " + page + "--adapter" + adapter.getItemCount());
                /**
                 * Creates a new request.
                 * @param method the HTTP method to use
                 * @param url URL to fetch the JSON from
                 * @param listener Listener to receive the JSON response
                 * @param errorListener Error listener, or null to ignore errors.
                 */
                final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                        "http://gank.io/api/data/" + mCategory + "/10/" + (page),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.i(TAG, "onResponse: current page " + page);
                                    ArrayList<friendsDatum> fList = new ArrayList<friendsDatum>();
                                    //fList.add(0,new friendsDatum("--------------","222222"));
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    for (int count = 0; count < jsonArray.length(); count++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(count);
                                        Log.i(TAG, "onResponse:" + jsonObject.getString("desc") + ":" + jsonObject.getString("url"));
                                        fList.add(new friendsDatum(jsonObject.getString("desc"), jsonObject.getString("url"), jsonObject.getString("who")));
                                        //Log.i(TAG, "onResponse: current size"+ fList.size());
                                    }
                                    //脑残!(╯‵□′)╯︵┻━┻
                                    adapter.addList(mCategory, fList);
                                } catch (JSONException e) {

                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    }
                });
                mRequestQueue.add(request);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return mView;
    }


    @Override
    public void onItemLongClick(View v, int position) {
        Snackbar.make(mSwipeRefreshLayout, "收藏功能还在制作当中n(*≧▽≦*)n", Snackbar.LENGTH_LONG)
                .setAction("哦", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }


    @Override
    public void onItemClick(View v, int position) {
        String url = adapter.getItemString(position);  //"http://cn.bing.com"; // web address

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse(url));

        startActivity(intent);
    }

}
