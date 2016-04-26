package layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import exercises.blue.demoagain.fDividerItemDecoration;
import exercises.blue.demoagain.R;
import exercises.blue.demoagain.friendsRecyclerAdapter;

/**
 * Created by getbl on 2016/4/18.
 */
public class fragmentFriends extends Fragment {

    static friendsRecyclerAdapter adapter;
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public friendsRecyclerAdapter getAdapter() {
        return adapter;
    }

    public static fragmentFriends newInstance() {
        Bundle args = new Bundle();
        fragmentFriends fragment = new fragmentFriends();
        fragment.setArguments(args);
        if (adapter == null) adapter = new friendsRecyclerAdapter();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new friendsRecyclerAdapter();
        View view = inflater.inflate(R.layout.friends, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutManager.generateLayoutParams(params);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new fDividerItemDecoration(5));
        //mRecyclerView.addOnItemTouchListener( new RecyclerView.SimpleOnItemTouchListener());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }


}
