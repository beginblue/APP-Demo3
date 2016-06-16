package exercises.blue.demoagain.BeautyGallery;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import exercises.blue.demoagain.BeautyGallery.imageloader.imageLoader;
import exercises.blue.demoagain.R;
import exercises.blue.demoagain.userdata.beautyData;
import exercises.blue.demoagain.userdata.friendsDataSet;

/**
 * Created by getbl on 2016/6/15.
 */
public class beautyAdapter extends RecyclerView.Adapter<beautyAdapter.beautyHolder> {


    private static final String TAG = "beautyAdapter";
    private LruCache<String, Bitmap> cache;
    private ArrayList<beautyData.ResultsBean> mData;


    public beautyAdapter() {
        cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory() / 8196));

        mData = (ArrayList<beautyData.ResultsBean>) friendsDataSet.newInstance().getList("福利");
        //if(mData==null) mData= new ArrayList<beautyData.ResultsBean>();
    }

    public void addBeauty(beautyData deltaData) {
        mData.addAll(deltaData.getResults());
    }


    @Override
    public beautyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beauty_item, parent, false);
        return new beautyHolder(view);
    }

    @Override
    public void onBindViewHolder(beautyHolder holder, int position) {
        beautyData.ResultsBean bean = mData.get(position);
        new imageLoader(cache).from(bean.getUrl()).into(holder.mImageView).execute();
        holder.mTextView.setText(bean.getDesc());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void addAll(ArrayList<beautyData.ResultsBean> beans) {
        //if(mData==null) mData=new ArrayList<>();
        for (beautyData.ResultsBean bean : beans) {
            mData.add(0, bean);
        }

        notifyDataSetChanged();
    }

    public class beautyHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public beautyHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.bPhoto);
            mTextView = (TextView) itemView.findViewById(R.id.bDesc);

        }
    }

}
