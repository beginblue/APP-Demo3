package exercises.blue.demoagain.friendsFragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.userdata.friendsDataSet;
import exercises.blue.demoagain.userdata.friendsDatum;

/**
 * Created by getbl on 2016/4/22.
 * Rewrite the adapter
 */
public class friendsRecyclerAdapter extends RecyclerView.Adapter<friendsRecyclerAdapter.recyclerViewHolder> {

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
        holder.isHot.setText((datum.getCategory()==1)?"热门":" ");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(friendsDatum datum){
        notifyItemInserted(mData.size());
        mData.add(datum);
    }

    public void add(friendsDatum datum, int position){
        notifyItemInserted(position);
        if(position>mData.size()) mData.add(datum);
        if(position<0) position=0;
        mData.add(position,datum);
    }

    public void remove(int position){
        if(position>=mData.size()) position = mData.size()-1;
        if(position<0) position=0;

        notifyItemRemoved(position);

        mData.remove(position);
    }


    /**
     * view holder
     */
    public class recyclerViewHolder extends RecyclerView.ViewHolder {

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
            //goodCount = (TextView) view.findViewById(R.id.item_good);
            //icon = (ImageView) view.findViewById(R.id.item_icon);
            //view.setLayoutParams();
        }

    }
}
