package exercises.blue.demoagain.friendsFragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.interfaces.myOnItemClickListener;
import exercises.blue.demoagain.interfaces.myOnItemLongClickListener;
import exercises.blue.demoagain.userdata.friendsDataSet;
import exercises.blue.demoagain.userdata.friendsDatum;

/**
 * Created by getbl on 2016/4/22.
 * Rewrite the adapter
 */
public class friendsRecyclerAdapter extends RecyclerView.Adapter<friendsRecyclerAdapter.recyclerViewHolder> {

    myOnItemClickListener mClickListener;
    myOnItemLongClickListener mLongClickListener;


    public friendsRecyclerAdapter(myOnItemClickListener clickListener, myOnItemLongClickListener longClickListener) {
        mClickListener = clickListener;
        mLongClickListener = longClickListener;
    }

    public ArrayList<friendsDatum> mData = friendsDataSet.newInstance().getList();

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        recyclerViewHolder holder = new recyclerViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(recyclerViewHolder holder, int position) {
        friendsDatum datum = mData.get(position);
        holder.title.setText(datum.getTitle());
        holder.content.setText(datum.getContent());
        holder.isHot.setText((datum.getCategory() == 1) ? "热门" : " ");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(friendsDatum datum) {
        notifyItemInserted(mData.size());
        mData.add(datum);
    }

    public void add(friendsDatum datum, int position) {
        notifyItemInserted(position);
        if (position > mData.size()) {
            mData.add(datum);
            return;
        }
        if (position < 0) position = 0;
        mData.add(position, datum);
    }

    public void remove(int position) {
        if (mData.size() == 0) return;
        if (position >= mData.size()) return;
        if (position < 0) position = 0;

        notifyItemRemoved(position);

        mData.remove(position);
    }


    /**
     * view holder
     */
    public class recyclerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView title;
        TextView content;
        TextView isHot;
        //TextView goodCount;
        //ImageView icon;

        public recyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            content = (TextView) view.findViewById(R.id.item_content);
            isHot = (TextView) view.findViewById(R.id.item_category);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            //goodCount = (TextView) view.findViewById(R.id.item_good);
            //icon = (ImageView) view.findViewById(R.id.item_icon);
            //view.setLayoutParams();
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, this.getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            if (mLongClickListener != null)
                mLongClickListener.onItemLongClick(v, getLayoutPosition());
            return true;
        }


    }
}
