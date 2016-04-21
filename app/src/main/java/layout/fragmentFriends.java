package layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import exercises.blue.demoagain.R;
import exercises.blue.userdata.dataSet;
import exercises.blue.userdata.userDatum;

/**
 * Created by getbl on 2016/4/18.
 */
public class fragmentFriends extends Fragment {
    //listAdapter adapter;
    ListView mListView;
    RecyclerView mRecyclerView;
    static friendRecViewAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private static final String TAG = "fragmentFriends";


    public friendRecViewAdapter getAdapter() {
        if(adapter == null) Log.e(TAG, "getAdapter: still null" );
        return adapter;
    }

    public static fragmentFriends newInstance() {
        Bundle args = new Bundle();
        fragmentFriends fragment = new fragmentFriends();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.friends, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recList);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        //set layout manager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        adapter = new friendRecViewAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //set adapter
        return view;

/**
 * ListView 的版本
 */
//        adapter = new listAdapter();
//        View view = inflater.inflate(R.layout.friends, container, false);
//        mListView = (ListView) view.findViewById(R.id.list);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                showDialog(position);
//                //Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                showDialog(position);
//
//
//                return false;
//            }
//        });
//        mListView.setAdapter(adapter);
//        return view;
    }

    public class friendRecViewAdapter extends RecyclerView.Adapter<friendRecViewAdapter.myViewHolder> {

        Context mContext;
        userDatum[] mUserData = dataSet.newInstance().getList();
        //List<userDatum> mUserData = (List<userDatum>) dataSet.newInstance();
        dataSet set = dataSet.newInstance();

        public friendRecViewAdapter(Context context){
            mContext= context;
        }


        public class myViewHolder extends RecyclerView.ViewHolder {

            //保存布局结构
            TextView title;
            TextView content;
            TextView isHot;
            TextView goodCount;
            ImageView icon;

            public myViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.item_title);
                content = (TextView) view.findViewById(R.id.item_content);
                isHot = (TextView) view.findViewById(R.id.item_category);
                goodCount = (TextView) view.findViewById(R.id.item_good);
                icon = (ImageView) view.findViewById(R.id.item_icon);
                //view.setLayoutParams();
            }
        }


        public void addItem(userDatum datum){
            notifyItemInserted(set.getList().length);
            set.addItem(datum);
            notifyItemRangeChanged(0,getItemCount());
            //notifyItemInserted(getItemCount());
            //notifyDataSetChanged();

        }

        public void addItem(int position,userDatum datum){
           // Log.e(TAG, "addItemAtSpecificPosition: do it in future" );

            if(position<0){
                position=0;
            }else if (position>set.getList().length){
                position=set.getList().length;
            }

            set.addItem(datum,position);
            notifyItemChanged(position);
        }

        public void removeItem(int position){
            notifyItemRemoved(position);
            set.removeItem(position);

        }

        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.listitem, parent,false);
            myViewHolder holder = new myViewHolder(view);
            return holder;
        }

        private static final String TAG = "friendRecViewAdapter";

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            userDatum datum = mUserData[position];
            if (datum == null) Log.e(TAG, "onBindViewHolder: datum is null");
            else if (holder == null) Log.e(TAG, "onBindViewHolder: holder is null");
            else {
                holder.title.setText(datum.getTitle());
                holder.content.setText(datum.getContent());
                holder.isHot.setText((datum.getCategory()==1)?"热门话题":"");

                //holder.goodCount.setText(datum.getGoodCount());
                //holder.icon.setImageBitmap(null);
            }
        }

        @Override
        public int getItemCount() {
            return mUserData.length;
        }
    }


//    private void showDialog(final int position) {
//        final int Position = position;
//        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
//        builder.setTitle("提示").setIcon(R.drawable.welcome)
//                .setMessage("真的要删除吗")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //dataSet.newInstance().removeItem(Position);
//                        adapter.remove(position);
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .create()
//                .show();
//    }
}
