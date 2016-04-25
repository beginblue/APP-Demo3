package layout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import exercises.blue.demoagain.DividerItemDecoration;
import exercises.blue.demoagain.R;
import exercises.blue.demoagain.recyclerAdapter;

/**
 * Created by getbl on 2016/4/18.
 */
public class fragmentFriends extends Fragment {

    static recyclerAdapter adapter;
    RecyclerView mRecyclerView;
    fragmentFriends mFragmentFriends;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public recyclerAdapter getAdapter() {
        return adapter;
    }

    public static fragmentFriends newInstance() {
        Bundle args = new Bundle();
        fragmentFriends fragment = new fragmentFriends();
        fragment.setArguments(args);
        if (adapter == null) adapter = new recyclerAdapter();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new recyclerAdapter();
        View view = inflater.inflate(R.layout.friends, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutManager.generateLayoutParams(params);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(5));
        //mRecyclerView.addOnItemTouchListener( new RecyclerView.SimpleOnItemTouchListener());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }


    public void updateView(int position) {
        //mRecyclerView.updateViewLayout();
        // mRecyclerView.getAdapter().notifyItemChanged(position);
    }


    /**
     * Abandoned. Maybe it will be rewrite in future.
     *
     * @param position the position of the item which you want to delete.
     */
    private void showDialog(final int position) {
        final int Position = position;
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        builder.setTitle("提示").setIcon(R.drawable.welcome)
                .setMessage("真的要删除吗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //friendsDataSet.newInstance().removeItem(Position);
                        adapter.remove(position);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }
}
