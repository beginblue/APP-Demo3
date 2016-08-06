package exercises.blue.demoagain.friendsFragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.interfaces.myOnItemClickListener;
import exercises.blue.demoagain.interfaces.myOnItemLongClickListener;
import exercises.blue.demoagain.userdata.responseBean;
import exercises.blue.demoagain.userdata.friendsDataSet;

/**
 * Created by getbl on 2016/4/22.
 * Rewrite the adapter
 */
public class friendsRecyclerAdapter extends RecyclerView.Adapter<friendsRecyclerAdapter.recyclerViewHolder> {

    private static final String TAG = "friendsRecyclerAdapter";
    myOnItemClickListener mClickListener;
    myOnItemLongClickListener mLongClickListener;
    String mCategory;
    //private  friendsDataSet mDataSet = friendsDataSet.newInstance();
    //TODO:换成操纵DataSet
    private friendsDataSet mDataSet = friendsDataSet.newInstance();
    //private ArrayList<responseBean.ResultsBean> mData;// = mDataSet.getList(mCategory);//mCategory is null


    //
    public friendsRecyclerAdapter(
            String category,
            @Nullable myOnItemClickListener clickListener,
            @Nullable myOnItemLongClickListener longClickListener) {
        mClickListener = clickListener;
        mLongClickListener = longClickListener;
        mCategory = category;
        //mData = mDataSet.getList(mCategory);
    }

    private List<responseBean.ResultsBean> getData(String category){
        return mDataSet.getList(category);
    }

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);

        return new recyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(recyclerViewHolder holder, int position) {
        //responseBean.ResultsBean datum = getData(mCategory).get(position);
        responseBean.ResultsBean datum = getData(mCategory).get(position);
        holder.title.setText(datum.getDesc());
        holder.content.setText(datum.getUrl());
        holder.author.setText(datum.getWho());
    }

    @Override
    public int getItemCount() {
        //Log.e(TAG, "getItemCount: " + mData.size() + " category: " + mCategory);
        ArrayList<responseBean.ResultsBean> mData = (ArrayList<responseBean.ResultsBean>) getData(mCategory);
        if (mData == null) {
            Log.e(TAG, "getItemCount: mData is null. category: " + mCategory);
            return 0;
        }
        return mData.size();

    }

    public void add(responseBean.ResultsBean datum) {
        notifyItemInserted(getData(mCategory).size());
        getData(mCategory).add(datum);
    }


    public void add(responseBean.ResultsBean datum, int position) {
        notifyItemInserted(position);
        if (position > getData(mCategory).size()) {
            getData(mCategory).add(datum);
            return;
        }
        if (position < 0) position = 0;
        getData(mCategory).add(position, datum);
    }

    public void clear() {
        notifyItemRangeRemoved(0,getData(mCategory).size());
        getData(mCategory).clear();
    }

    public void remove(int position) {
        if (getData(mCategory).size() == 0) return;
        if (position >= getData(mCategory).size()) return;
        if (position < 0) position = 0;

        notifyItemRemoved(position);

        getData(mCategory).remove(position);
    }

    public void addList(String category, List<responseBean.ResultsBean> list) {
        mCategory = category;//不负责任的做法

       // mDataSet.setList(mCategory, list);
        /**
         * 少年要开始做死了
         * 然而并没有什么特殊的效果
         */
        for (responseBean.ResultsBean datum : list) {
            add(datum, 0);
        }
        notifyDataSetChanged();
    }

    public String getItemUrl(int position) {
        return ((ArrayList<responseBean.ResultsBean>)mDataSet.getList(mCategory)).get(position).getUrl();
    }

    public String getItemTitle(int position){
        return ((ArrayList<responseBean.ResultsBean>)mDataSet.getList(mCategory)).get(position).getDesc();
    }

    public void addAll(String category, List<responseBean.ResultsBean> results) {
        mCategory = category;//不负责任的做法
        mDataSet.getList(category).addAll(results);
        notifyDataSetChanged();
    }

    /**
     * view holder
     */
    public class recyclerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        ;
        TextView title;
        TextView content;
        TextView author;
        //TextView goodCount;
        //ImageView icon;
        //SwipeRefreshLayout mSwipeRefreshLayout;

        public recyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            content = (TextView) view.findViewById(R.id.item_content);
            author = (TextView) view.findViewById(R.id.item_category);
            //mSwipeRefreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.refresh);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            //goodCount = (TextView) view.findViewById(R.id.item_good);
            //icon = (ImageView) view.findViewById(R.id.item_icon);
            //view.setLayoutParams();
        }

        @Override
        public void onClick(View v) {

            if (mClickListener != null) mClickListener.onItemClick(v, this.getLayoutPosition());
            else Toast.makeText(v.getContext(), "123321", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            //Toast.makeText(v.getContext(), "123321", Toast.LENGTH_SHORT).show();
            if (mLongClickListener != null)
                mLongClickListener.onItemLongClick(v, getLayoutPosition());
            else Toast.makeText(v.getContext(), "123321", Toast.LENGTH_SHORT).show();
            return true;
        }


    }
}
