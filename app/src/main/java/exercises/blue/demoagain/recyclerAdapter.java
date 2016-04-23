package exercises.blue.demoagain;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import exercises.blue.userdata.dataSet;
import exercises.blue.userdata.userDatum;

/**
 * Created by getbl on 2016/4/22.
 * Rewrite the adapter
 */
public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.recyclerViewHolder> {

    public ArrayList<userDatum> mData = dataSet.newInstance().getList();

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        recyclerViewHolder holder = new recyclerViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(recyclerViewHolder holder, int position) {
        userDatum datum = mData.get(position);
        holder.title.setText(datum.getTitle());
        holder.content.setText(datum.getContent());
        holder.isHot.setText((datum.getCategory()==1)?"热门":" ");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(userDatum datum){
        notifyItemInserted(mData.size());
        mData.add(datum);
    }

    public void add(userDatum datum,int position){
        notifyItemInserted(position);
        mData.add(position,datum);
    }

    public void remove(int position){
        if(position>mData.size()) position = mData.size();
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